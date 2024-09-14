package com.ecommerce.shop_microservice.payment;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "wallet-service")
public interface WalletServiceFeign {
    
    @PostMapping("/api/v1/wallet/deduct/{userId}/{walletId}")
    void deduct(@PathVariable Long userId, @PathVariable Long walletId,@RequestBody BigDecimal amount);
}
