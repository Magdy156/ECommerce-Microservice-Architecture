package com.ecommerce.wallet_service.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findByWalletId(Long walletId);
}
