package com.drone.drone.purchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo  extends JpaRepository<PurchaseOrder, Long> {
}
