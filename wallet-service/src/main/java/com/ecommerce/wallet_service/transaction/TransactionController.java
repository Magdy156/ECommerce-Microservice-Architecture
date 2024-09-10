package com.ecommerce.wallet_service.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.wallet_service.wallet.Wallet;
import java.util.List;


@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/cashout/{walletId}")
    public Wallet cashOut(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        return transactionService.cashOut(walletId, amount);
    }

    @PostMapping("/cashin/{walletId}")
    public Wallet cashIn(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        return transactionService.cashIn(walletId, amount);
    }

    @GetMapping("/{walletId}")
    public List<Transaction> getTransactionHistory(@PathVariable Long walletId) {
        return transactionService.getTransactionsHistory(walletId);
    }
}
