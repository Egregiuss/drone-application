package com.drone.drone.waybill;


import com.drone.drone.orders.OrderItem;
import com.drone.drone.orders.OrderItemRepo;
import com.drone.drone.orders.OrderObject;
import com.drone.drone.orders.OrdersRepo;
import com.drone.drone.product.Product;
import com.drone.drone.purchaseOrder.PurchaseOrder;
import com.drone.drone.purchaseOrder.PurchaseOrderItem;
import com.drone.drone.purchaseOrder.PurchaseOrderItemRepo;
import com.drone.drone.purchaseOrder.PurchaseOrderRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WaybillService {
    private final OrdersRepo ordersRepo;
    private final OrderItemRepo orderItemRepo;
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final PurchaseOrderItemRepo purchaseOrderItemRepo;
    private final WaybillItemRepo waybillItemRepo;
    private final WaybillRepo waybillRepo;

    public WaybillService(OrdersRepo ordersRepo, OrderItemRepo orderItemRepo, PurchaseOrderRepo purchaseOrderRepo, PurchaseOrderItemRepo purchaseOrderItemRepo, WaybillItemRepo waybillItemRepo, WaybillRepo waybillRepo) {
        this.ordersRepo = ordersRepo;
        this.orderItemRepo = orderItemRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.purchaseOrderItemRepo = purchaseOrderItemRepo;
        this.waybillItemRepo = waybillItemRepo;
        this.waybillRepo = waybillRepo;
    }

    public Waybill getWaybillById(Long orderId) {
        Optional<Waybill> waybillOptional = waybillRepo.findByOrderId(orderId);
        if (waybillOptional.isPresent()) {
            return waybillOptional.get();
        } else {
            throw new NoSuchElementException("Waybill with ID " + orderId + " not found");
        }

    }


    public String calculateDroneCategory(BigDecimal totalWeight) {
        if (totalWeight.compareTo(BigDecimal.valueOf(10)) <= 0) {
            return "Alpha";
        } else if (totalWeight.compareTo(BigDecimal.valueOf(20)) <= 0) {
            return "Beta";
        } else if (totalWeight.compareTo(BigDecimal.valueOf(30)) <= 0) {
            return "Gamma";
        } else if (totalWeight.compareTo(BigDecimal.valueOf(40)) <= 0) {
            return "Delta";
        } else if (totalWeight.compareTo(BigDecimal.valueOf(50))<=0) {
            return "Epsilon";
        } else {
            return "Can't Assign: You've exceeded the weight limit";
        }
    }

    public boolean retrieveAndStoreData(Long orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(orderId).orElse(null);

        if (purchaseOrder == null || purchaseOrder.isProcessed()) {
            return false; // Order not found or already processed
        }

        Waybill waybill = new Waybill();
        waybill.setCustomerName(purchaseOrder.getCustomerName());
        waybill.setAddress(purchaseOrder.getAddress());
        waybill.setPhoneNumber(purchaseOrder.getPhoneNumber());
        waybill.setEmail(purchaseOrder.getEmail());
        waybill.setOrderId(purchaseOrder.getOrderId());


        BigDecimal totalWeight = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;


        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemRepo.findByOrderId(purchaseOrder.getOrderId());
        for (PurchaseOrderItem purchaseOrderItem : purchaseOrderItems) {
            totalWeight = totalWeight.add(purchaseOrderItem.getWeight());
            totalPrice = totalPrice.add(purchaseOrderItem.getPrice());

            WaybillItem waybillItem = new WaybillItem();
            waybillItem.setProductName(purchaseOrderItem.getProductName());
            waybillItem.setPrice(purchaseOrderItem.getPrice());
            waybillItem.setWeight(purchaseOrderItem.getWeight());
            waybillItem.setOrderId(purchaseOrder.getOrderId());
            waybillItemRepo.save(waybillItem);
        }

        waybill.setTotalWeight(totalWeight);
        waybill.setTotalPrice(totalPrice);
        waybill.setDrone_name(calculateDroneCategory(totalWeight));
        waybillRepo.save(waybill);

        purchaseOrder.setProcessed(true); // Mark the order as processed
        purchaseOrderRepo.save(purchaseOrder);

        return true; // Order processed successfully
    }
}
