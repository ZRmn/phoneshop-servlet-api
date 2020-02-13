package com.es.phoneshop.model.product;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListProductDao implements ProductDao {

    private static ArrayListProductDao productDao;

    private ArrayListProductDao() {
        products = new ArrayList<>();
    }

    public static synchronized ArrayListProductDao getInstance() {
        if (productDao == null) {
            productDao = new ArrayListProductDao();
        }

        return productDao;
    }

    private List<Product> products;

    @Override
    public synchronized Product getProduct(Long id) throws NoSuchElementException {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }

    @Override
    public synchronized List<Product> findProducts() {
        return products.stream()
                .filter(product -> product.getCurrentPriceData() != null)
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized List<Product> findProducts(String query) {
        if (query == null) {
            return findProducts();
        }

        class Pair<T, U> {
            private T first;
            private U second;

            private Pair(T first, U second) {
                this.first = first;
                this.second = second;
            }
        }

        String[] words = query.toLowerCase().split(" ");

        return products.stream()
                .filter(product -> product.getCurrentPriceData() != null)
                .filter(product -> product.getStock() > 0)
                .map(product -> {
                    long numberOfOccurrences = Stream.of(words)
                            .filter(word -> product.getDescription().toLowerCase().contains(word))
                            .count();

                    return new Pair<>(product, numberOfOccurrences);
                })
                .filter(productWithOccurrences -> productWithOccurrences.second > 0)
                .sorted(Comparator.comparing(productWithOccurrences -> productWithOccurrences.second, Comparator.reverseOrder()))
                .map(productWithOccurrences -> productWithOccurrences.first)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProducts(String query, String sortBy, String orderBy) {
        List<Product> products = findProducts(query);

        if (sortBy == null || orderBy == null) {
            return products;
        }

        Comparator<Product> comparator;

        switch (sortBy) {
            case "description":
                comparator = Comparator.comparing(product -> product.getDescription().toLowerCase());
                break;
            case "price":
                comparator = Comparator.comparing(product -> product.getCurrentPriceData().getPrice());
                break;
            default:
                comparator = Comparator.comparing(Product::getId);
        }

        products.sort(orderBy.equals("asc") ? comparator : comparator.reversed());

        return products;
    }

    @Override
    public synchronized void save(Product product) {
        product.setId(findId());
        products.add(product);
    }

    @Override
    public synchronized void delete(Long id) throws NoSuchElementException {
        Product productForDelete = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        products.remove(productForDelete);
    }

    private Long findId() {
        if (products.size() == 0) {
            return 1L;
        }

        Long[] ids = products.stream()
                .map(Product::getId)
                .sorted()
                .toArray(Long[]::new);

        for (int i = 0; i < ids.length - 1; i++) {
            if (ids[i + 1] - ids[i] > 1) {
                return ids[i] + 1;
            }
        }

        return ids[ids.length - 1] + 1;
    }
}
