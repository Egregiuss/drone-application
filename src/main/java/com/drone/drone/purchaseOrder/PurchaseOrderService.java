package com.drone.drone.purchaseOrder;

import com.drone.drone.orders.OrderItem;
import com.drone.drone.orders.OrderItemRepo;
import com.drone.drone.orders.OrderObject;
import com.drone.drone.orders.OrdersRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    private final OrdersRepo ordersRepo;
    private final OrderItemRepo orderItemRepo;
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final PurchaseOrderItemRepo purchaseOrderItemRepo;

    public PurchaseOrderService(OrdersRepo ordersRepo, OrderItemRepo orderItemRepo, PurchaseOrderRepo purchaseOrderRepo, PurchaseOrderItemRepo purchaseOrderItemRepo) {
        this.ordersRepo = ordersRepo;
        this.orderItemRepo = orderItemRepo;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.purchaseOrderItemRepo = purchaseOrderItemRepo;
    }


    public PurchaseOrder getOrderById(Long orderId) {
        Optional<PurchaseOrder> productOptional = purchaseOrderRepo.findById(orderId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new NoSuchElementException("Product with ID " + orderId + " not found");
        }

    }
    public boolean retrieveAndStoreData(Long orderId) {
        OrderObject orderObject = ordersRepo.findById(orderId).orElse(null);

        if (orderObject == null || orderObject.isProcessed()) {
            return false; // Order not found or already processed
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCustomerName(orderObject.getCustomerName());
        purchaseOrder.setAddress(orderObject.getAddress());
        purchaseOrder.setPhoneNumber(orderObject.getPhoneNumber());
        purchaseOrder.setEmail(orderObject.getEmail());
        purchaseOrder.setOrderId(orderObject.getId());

        BigDecimal totalWeight = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItem> orderItems = orderItemRepo.findByOrderObjectId(orderObject.getId());
        for (OrderItem orderItem : orderItems) {
            totalWeight = totalWeight.add(orderItem.getWeight());
            totalPrice = totalPrice.add(orderItem.getPrice());

            PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
            purchaseOrderItem.setProductName(orderItem.getProductName());
            purchaseOrderItem.setPrice(orderItem.getPrice());
            purchaseOrderItem.setWeight(orderItem.getWeight());
            purchaseOrderItem.setOrderId(orderObject.getId());
            purchaseOrderItemRepo.save(purchaseOrderItem);
        }

        purchaseOrder.setTotalWeight(totalWeight);
        purchaseOrder.setTotalPrice(totalPrice);
        purchaseOrderRepo.save(purchaseOrder);

        orderObject.setProcessed(true); // Mark the order as processed
        ordersRepo.save(orderObject);

        return true; // Order processed successfully
    }
}
