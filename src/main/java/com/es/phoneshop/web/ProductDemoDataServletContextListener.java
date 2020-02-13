package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class ProductDemoDataServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ProductDao productDao = ArrayListProductDao.getInstance();
        getSampleProducts().forEach(productDao::save);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        Currency usd = Currency.getInstance("USD");
        
        LocalDate date1 = LocalDate.of(2019, 7, 9);
        LocalDate date2 = LocalDate.of(2015, 11, 12);
        LocalDate date3 = LocalDate.of(2016, 8, 5);

        Product.PriceData currentPrice = new Product.PriceData(new BigDecimal(100), usd, date1);
        Product.PriceData changedPrice1 = new Product.PriceData(new BigDecimal(120), usd, date2);
        Product.PriceData changedPrice2 = new Product.PriceData(new BigDecimal(130), usd, date3);
        products.add(new Product(null, "sgs", "Samsung Galaxy S", 100,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(200), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(250), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(230), usd, date3);
        products.add(new Product(null, "sgs2", "Samsung Galaxy S II", 10,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(250), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(300), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(280), usd, date3);
        products.add(new Product(null, "sgs3", "Samsung Galaxy S III", 5,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(200), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(290), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(250), usd, date3);
        products.add(new Product(null, "iphone", "Apple iPhone", 10,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(1000), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(1100), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(1050), usd, date3);
        products.add(new Product(null, "iphone6", "Apple iPhone 6", 30,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(320), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(340), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(350), usd, date3);
        products.add(new Product(null, "htces4g", "HTC EVO Shift 4G", 3,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(420), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(440), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(450), usd, date3);
        products.add(new Product(null, "sec901", "Sony Ericsson C901", 30,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(120), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(140), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(150), usd, date3);
        products.add(new Product(null, "xperiaxz", "Sony Xperia XZ", 100,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(70), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(80), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(90), usd, date3);
        products.add(new Product(null, "nokia3310", "Nokia 3310", 100,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(170), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(180), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(190), usd, date3);
        products.add(new Product(null, "palmp", "Palm Pixi", 30,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(75), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(85), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(95), usd, date3);
        products.add(new Product(null, "simc56", "Siemens C56", 20,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(80), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(90), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(95), usd, date3);
        products.add(new Product(null, "simc61", "Siemens C61", 30,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        currentPrice = new Product.PriceData(new BigDecimal(150), usd, date1);
        changedPrice1 = new Product.PriceData(new BigDecimal(160), usd, date2);
        changedPrice2 = new Product.PriceData(new BigDecimal(170), usd, date3);
        products.add(new Product(null, "simsxg75", "Siemens SXG75", 40,
                "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg", currentPrice, new ArrayList<>(Arrays.asList(changedPrice1, changedPrice2))));

        return products;
    }
}
