package com.ecommerce.inventory_microservice.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shop-microservice")
public interface ProductFeign {
    
    @GetMapping("/api/v1/products/{id}")
    Product getProductById(@PathVariable Long id);
}
