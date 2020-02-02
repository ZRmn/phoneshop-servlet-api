package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArrayListProductDaoTest {

    @Mock
    private Product productWithoutPrice;

    @Mock
    private Product productOutOfStock;

    @Mock
    private Product rightProduct;

    @Mock
    private Product rightProduct2;

    @Mock
    private Product productForAdding;

    @Mock
    private Product productForDelete;

    @Spy
    private List<Product> products = new ArrayList<>();

    @InjectMocks
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Before
    public void setup() {
        products.addAll(Arrays.asList(productWithoutPrice, productOutOfStock, rightProduct, rightProduct2));

        when(rightProduct.getDescription()).thenReturn("Samsung");
        when(rightProduct2.getDescription()).thenReturn("Nokia");
        when(productWithoutPrice.getId()).thenReturn(1L);
        when(productOutOfStock.getId()).thenReturn(2L);
        when(productOutOfStock.getCurrentPriceData()).thenReturn(new Product.PriceData(new BigDecimal(1000), null, null));
        when(rightProduct.getId()).thenReturn(3L);
        when(rightProduct.getCurrentPriceData()).thenReturn(new Product.PriceData(new BigDecimal(1000), null, null));
        when(rightProduct.getStock()).thenReturn(1000);
        when(rightProduct2.getCurrentPriceData()).thenReturn(new Product.PriceData(new BigDecimal(200), null, null));
        when(rightProduct2.getStock()).thenReturn(1000);
        when(productForDelete.getId()).thenReturn(5L);
    }

    @Test
    public void testGetProduct() {
        assertEquals(productWithoutPrice, productDao.getProduct(1L));
    }

    @Test
    public void testFindProducts() {
        assertEquals(2, productDao.findProducts().size());
    }

    @Test
    public void testSaveProduct() {
        productDao.save(productForAdding);
        assertEquals(5, products.size());

        products.remove(productForAdding);
    }

    @Test
    public void testDeleteProduct() {
        products.add(productForDelete);

        productDao.delete(5L);
        assertEquals(4, products.size());
    }

    @Test
    public void testFindProductsWithQuery() {
        List<Product> resultProducts = productDao.findProducts("nokia");

        assertTrue(resultProducts.contains(rightProduct2));
    }

    @Test
    public void testFindProductsWithSorting() {
        List<Product> resultProducts = productDao.findProducts("", "price", "desc");

        assertEquals(BigDecimal.valueOf(1000L), resultProducts.get(0).getCurrentPriceData().getPrice());
    }
}
