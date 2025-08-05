package com.stokmanager.backend.service;

import com.stokmanager.backend.entity.Product;
import com.stokmanager.backend.entity.StockMovement;
import com.stokmanager.backend.repository.ProductRepository;
import com.stokmanager.backend.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository movementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository movementRepository, ProductRepository productRepository) {
        this.movementRepository = movementRepository;
        this.productRepository = productRepository;
    }

    public StockMovement addMovement(StockMovement movement) {
        Product product = productRepository.findById(movement.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        // Stoğu güncelle
        if (movement.getType().equals("IN")) {
            product.setQuantity(product.getQuantity() + movement.getAmount());
        } else if (movement.getType().equals("OUT")) {
            if (product.getQuantity() < movement.getAmount()) {
                throw new RuntimeException("Yetersiz stok!");
            }
            product.setQuantity(product.getQuantity() - movement.getAmount());
        }

        productRepository.save(product); // stoğu güncelle
        return movementRepository.save(movement);
    }

    public List<StockMovement> getAllMovements() {
        return movementRepository.findAll();
    }
}
