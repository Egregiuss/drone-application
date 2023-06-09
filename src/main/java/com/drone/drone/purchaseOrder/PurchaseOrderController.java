package com.drone.drone.purchaseOrder;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/purchaseOrder/{orderId}")
    public ResponseEntity<String> processOrder(@PathVariable Long orderId) {
        boolean isProcessed = purchaseOrderService.retrieveAndStoreData(orderId);
        if (isProcessed) {
            return ResponseEntity.ok("Order processed successfully");
        } else {
            return ResponseEntity.badRequest().body("Order not found or already processed");
        }
    }


    @GetMapping("/purchaseOrder/{orderId}")
    @ResponseBody
    public ResponseEntity<PurchaseOrder> getWaybillById(@PathVariable Long orderId) {
        PurchaseOrder waybill = purchaseOrderService.getOrderById(orderId);
        if (waybill != null) {
            return ResponseEntity.ok(waybill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


