package com.ecommerce.shop_microservice.order_management;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.shop_microservice.cart_management.Cart;
import com.ecommerce.shop_microservice.cart_management.CartRepository;
import com.ecommerce.shop_microservice.cart_management.CartService;
import com.ecommerce.shop_microservice.payment.PaymentService;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentService paymentService;


    @Transactional
    public Order createOrder(Long userId, Long walletId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        if (!paymentService.paymentProcess(userId, walletId, cart.getTotal())) {
            throw new RuntimeException("Payment processing failed");
        }
        

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            return orderItem;
        }).collect(Collectors.toList());

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderItems);
        order.setTotal(cart.getTotal());
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        order = orderRepository.save(order);

        // Clear the cart
        cartService.clearCart(userId);
        return order;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
