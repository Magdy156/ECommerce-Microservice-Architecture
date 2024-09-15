package com.ecommerce.inventory_microservice.inventory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getInventoryByProductId(Long productId) {
        return Optional.ofNullable(inventoryRepository.findByProductId(productId));
    }

    public void updateInventory(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null) {
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        }
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
