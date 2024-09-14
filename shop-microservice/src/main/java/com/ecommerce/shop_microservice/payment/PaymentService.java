package com.ecommerce.shop_microservice.payment;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WalletServiceFeign walletServiceFeign;

    public boolean paymentProcess(Long userId, Long walletId,BigDecimal amount) {
        try {
            walletServiceFeign.deduct(userId, walletId, amount);
            return true;
        } catch (Exception e) {
            System.err.println("Payment processing failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
