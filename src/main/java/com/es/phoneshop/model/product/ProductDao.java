package com.es.phoneshop.model.product;

import java.util.List;
import java.util.function.Predicate;

public interface ProductDao {

    Product getProduct(Long id);

    List<Product> findProducts();

    List<Product> findProducts(String query);

    List<Product> findProducts(String query, String sortBy, String orderBy);

    public List<Product> findProductsByDescriptionWithAnyWord(String[] words);

    public List<Product> findProductsByDescriptionWithAllWords(String[] words);

    List<Product> filterProducts(List<Product> products, List<Predicate<Product>> predicates);

    void save(Product product);

    void delete(Long id);
}
