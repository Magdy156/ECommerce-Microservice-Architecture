package com.ecommerce.wallet_service.wallet;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;


    @PostMapping("/create/{userId}")
    public Wallet creteWallet(@PathVariable Long userId) {        
        return walletService.createWallet(userId);
    }
    

    @GetMapping("/{userId}")
    public List<Wallet> getWallet(@PathVariable Long userId) {
        return walletService.getWallet(userId);
    }

    @PostMapping("/deduct/{userId}/{walletId}")
    public ResponseEntity<String> deductAmount(@PathVariable Long userId, @PathVariable Long walletId, @RequestBody BigDecimal amount) {
        boolean success = walletService.deduct(userId, walletId, amount);
        if (success) {
            return ResponseEntity.ok("Amount deducted successfully");
        } else {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }
    }

}
