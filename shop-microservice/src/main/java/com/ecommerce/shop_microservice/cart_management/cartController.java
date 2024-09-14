package com.ecommerce.shop_microservice.cart_management;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class cartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create/{userId}")
    public Cart createCart(@PathVariable Long userId) {
        return cartService.creatCart(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addItemToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addItemToCart(userId, productId, quantity);

    }

    @GetMapping("/{userId}")
    public Optional<Cart> showCart(@PathVariable Long userId) {
        return cartService.showCart(userId);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
