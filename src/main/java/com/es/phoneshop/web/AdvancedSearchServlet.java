package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@WebServlet("/advancedSearch")
public class AdvancedSearchServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameterMap().containsKey("show")) {
            String description = request.getParameter("description");
            String descriptionSearchMode = request.getParameter("descriptionSearchMode");
            String minPriceAsString = request.getParameter("minPrice");
            String maxPriceAsString = request.getParameter("maxPrice");
            NumberFormat format = NumberFormat.getInstance(request.getLocale());

            BigDecimal minPrice = null;
            BigDecimal maxPrice = null;

            boolean hasErrors = false;

            try {
                minPrice = parsePrice(minPriceAsString, format);
            } catch (ParseException e) {
                request.setAttribute("minPriceError", "Should be a number");
                hasErrors = true;
            } catch (IllegalStateException e) {
                request.setAttribute("minPriceError", "Should be greater than 0");
                hasErrors = true;
            }

            try {
                maxPrice = parsePrice(maxPriceAsString, format);
            } catch (ParseException e) {
                request.setAttribute("maxPriceError", "Should be a number");
                hasErrors = true;
            } catch (IllegalStateException e) {
                request.setAttribute("maxPriceError", "Should be greater than 0");
                hasErrors = true;
            }

            if (!hasErrors) {
                List<Product> products = getProducts(description, descriptionSearchMode, minPrice, maxPrice);
                request.setAttribute("products", products);
            }
        }

        request.getRequestDispatcher("/WEB-INF/pages/advancedSearch.jsp").forward(request, response);
    }

    private BigDecimal parsePrice(String priceAsString, NumberFormat numberFormat) throws ParseException, IllegalStateException {
        BigDecimal price = new BigDecimal(numberFormat.parse(priceAsString).toString());

        if (price.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalStateException();
        }

        return price;
    }

    private List<Product> getProducts(String description, String descriptionSearchMode, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Predicate<Product>> predicates = new ArrayList<>();

        if (minPrice != null) {
            predicates.add(product -> product.getCurrentPriceData().getPrice().compareTo(minPrice) >= 0);
        }

        if (maxPrice != null) {
            predicates.add(product -> product.getCurrentPriceData().getPrice().compareTo(maxPrice) <= 0);
        }

        Predicate<Product> compositePredicate = predicates.stream()
                .reduce(predicate -> true, Predicate::and);

        List<Product> products;
        String[] words = description.toLowerCase().split(" ");

        switch (descriptionSearchMode) {
            case "all":
                products = productDao.findProductsByDescriptionWithAllWords(words);
                break;
            case "any":
                products = productDao.findProductsByDescriptionWithAnyWord(words);
                break;
            default:
                products = productDao.findProducts();
        }

        return productDao.filterProducts(products, predicates);
    }
}
