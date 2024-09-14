package com.ecommerce.shop_microservice.cart_management;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.shop_microservice.product.Product;
import com.ecommerce.shop_microservice.product.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart creatCart(Long userId){
        if (cartRepository.findByUserId(userId).isPresent()){
            throw new RuntimeException("Cart already exists for user");
        }
        Cart cart = new Cart();

        cart.setTotal(BigDecimal.ZERO);
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }


    public Cart addItemToCart(Long userId, Long productId, int quantity) {
    Cart cart = cartRepository.findByUserId(userId)
            .orElseGet(() -> creatCart(userId));

    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

    // Check if the product is already in the cart
    Optional<CartItem> existingItem = cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst();

    if (existingItem.isPresent()) {
        // If the item already exists in the cart, update the quantity
        existingItem.get().setQuantity(quantity);
    } else {
        // If the item is not in the cart, create a new cart item
        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        newItem.setCart(cart);
        cart.getItems().add(newItem);
    }

    // Recalculate the total price of the cart
    BigDecimal total = cart.getItems().stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    cart.setTotal(total);

    return cartRepository.save(cart);
}

    public Optional<Cart> showCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotal(BigDecimal.ZERO);
        cartRepository.save(cart);
    }
}
