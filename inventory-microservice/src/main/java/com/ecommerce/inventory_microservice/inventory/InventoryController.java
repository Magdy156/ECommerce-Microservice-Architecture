package com.ecommerce.inventory_microservice.inventory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductFeign productFeign;

    @PostMapping("/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        // Ensure the product exists in the Shop microservice
        Product product = productFeign.getProductById(inventory.getProductId());
        if (product == null) {
            throw new RuntimeException("Product not found in Shop microservice");
        }
        return inventoryService.createInventory(inventory);
    }

    @GetMapping("/product/{productId}")
    public Optional<Inventory> getInventoryByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("product/{productId}")
    public void updateInventory(@PathVariable Long productId, @RequestParam int quantity) {
        inventoryService.updateInventory(productId, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}
