package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class HttpSessionCartService implements CartService {

    private static CartService instance;
    private ProductDao productDao = ArrayListProductDao.getInstance();

    private HttpSessionCartService() {

    }

    public static synchronized CartService getInstance() {
        if (instance == null) {
            instance = new HttpSessionCartService();
        }

        return instance;
    }

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    private void throwIfOutOfStock(Product product, int requiredQuantity) throws OutOfStockException {
        if (product.getStock() < requiredQuantity) {
            throw new OutOfStockException(product, requiredQuantity);
        }
    }

    @Override
    public void add(HttpSession session, Long productId, int quantity) throws OutOfStockException {
        Product product = productDao.getProduct(productId);
        Cart cart = getCart(session);

        Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            int requiredQuantity = cartItem.getQuantity() + quantity;

            throwIfOutOfStock(product, requiredQuantity);
            cartItem.setQuantity(requiredQuantity);
        } else {
            throwIfOutOfStock(product, quantity);
            cart.getCartItems().add(new CartItem(product, quantity));
        }
    }

    @Override
    public void update(HttpSession session, Long productId, int quantity) throws OutOfStockException {
        Product product = productDao.getProduct(productId);
        Cart cart = getCart(session);

        if (product.getStock() < quantity) {
            throw new OutOfStockException(product, quantity);
        }

        cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().ifPresent(item -> item.setQuantity(quantity));
    }

    @Override
    public void delete(HttpSession session, Long productId) {
        Product product = productDao.getProduct(productId);
        Cart cart = getCart(session);

        cart.getCartItems().removeIf(item -> item.getProduct().equals(product));
    }

    @Override
    public int calculateTotalQuantity(HttpSession session) {
        Cart cart = getCart(session);

        return cart.getCartItems().stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    private BigDecimal getItemPrice(CartItem cartItem) {
        BigDecimal productPrice = cartItem.getProduct().getCurrentPriceData().getPrice();

        return productPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    @Override
    public BigDecimal calculateTotalPrice(HttpSession session) {
        Cart cart = getCart(session);

        return cart.getCartItems().stream()
                .map(this::getItemPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }
}
