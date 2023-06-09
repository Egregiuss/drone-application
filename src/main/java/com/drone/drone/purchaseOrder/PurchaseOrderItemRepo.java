package com.drone.drone.purchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PurchaseOrderItemRepo extends JpaRepository<PurchaseOrderItem, Long> {
    List<PurchaseOrderItem> findByOrderId(Long orderId);
}
