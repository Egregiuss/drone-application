package com.drone.drone.purchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderItemService {

    private PurchaseOrderItemRepo purchaseOrderItemRepo;

    @Autowired
    public PurchaseOrderItemService(PurchaseOrderItemRepo purchaseOrderItemRepo) {
        this.purchaseOrderItemRepo = purchaseOrderItemRepo;
    }

    public PurchaseOrderItem savePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemRepo.save(purchaseOrderItem);
    }

    public List<PurchaseOrderItem> getAllPurchaseOrderItems() {
        return purchaseOrderItemRepo.findAll();
    }



}
