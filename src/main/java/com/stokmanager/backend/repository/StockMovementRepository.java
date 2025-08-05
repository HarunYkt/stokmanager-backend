package com.stokmanager.backend.repository;

import com.stokmanager.backend.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductId(Long productId);
}
// StockMovementRepository interface, extending JpaRepository to provide CRUD operations for StockMovement entities.