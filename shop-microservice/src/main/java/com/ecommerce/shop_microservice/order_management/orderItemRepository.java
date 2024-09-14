package com.ecommerce.shop_microservice.order_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
