package com.ecommerce.shop_microservice.cart_management;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUserId(Long userId);
}
