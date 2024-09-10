package com.ecommerce.wallet_service.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.wallet_service.wallet.Wallet;
import com.ecommerce.wallet_service.wallet.WalletRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;


    public Wallet cashOut(Long walletId, BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletId)
        .orElseThrow(() -> new RuntimeException("Wallet is not found"));

        if (wallet.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Balance is not enough");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType("Cash Out");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return wallet;
    }


    public Wallet cashIn(Long walletId, BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletId)
        .orElseThrow(() -> new RuntimeException("Wallet is not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType("Cash In");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return wallet;
    }

    public  List<Transaction> getTransactionsHistory(Long walletId){
        return transactionRepository.findByWalletId(walletId);
    }
}
