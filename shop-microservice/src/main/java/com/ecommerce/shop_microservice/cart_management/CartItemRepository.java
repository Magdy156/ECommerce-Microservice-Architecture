package com.ecommerce.shop_microservice.cart_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
