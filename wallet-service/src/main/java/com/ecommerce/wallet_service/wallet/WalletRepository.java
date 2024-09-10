package com.ecommerce.wallet_service.wallet;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
    Optional<Wallet> findByUserIdAndId(Long userId, Long walletId);
    List<Wallet> findByUserId(Long userId);
}
