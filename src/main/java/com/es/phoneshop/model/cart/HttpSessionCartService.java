package com.es.phoneshop.model.cart;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class HttpSessionCartService implements CartService {

    private static CartService instance;
    private final String CART_ATTRIBUTE = "cart";
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
        Cart cart = (Cart) session.getAttribute(CART_ATTRIBUTE);

        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_ATTRIBUTE, cart);
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
}
