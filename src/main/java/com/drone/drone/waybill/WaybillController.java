package com.drone.drone.waybill;



import com.drone.drone.purchaseOrder.PurchaseOrderRepo;
import com.drone.drone.purchaseOrder.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class WaybillController {
    private final WaybillService waybillService;
    private final PurchaseOrderRepo purchaseOrderRepo;
    private final PurchaseOrderService purchaseOrderService;
    private final WaybillItemService waybillItemService;

    public WaybillController(WaybillService waybillService, PurchaseOrderRepo purchaseOrderRepo, PurchaseOrderService purchaseOrderService, WaybillItemService waybillItemService) {
        this.waybillService = waybillService;
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.purchaseOrderService = purchaseOrderService;
        this.waybillItemService = waybillItemService;
    }


    @PostMapping("/waybill/{orderId}")
    public ResponseEntity<String> processWaybill(@PathVariable Long orderId) {
        boolean isProcessed = waybillService.retrieveAndStoreData(orderId);
        if (isProcessed) {
            return ResponseEntity.ok("Waybill is ready");
        } else {
            return ResponseEntity.badRequest().body("Order not found or already processed");
        }
    }

    @GetMapping("/waybill/{orderId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getWaybillById(@PathVariable Long orderId) {
        Waybill waybill = waybillService.getWaybillById(orderId);
       List<WaybillItem> waybillItem = waybillItemService.getWaybillItemById(orderId);

        if (waybill != null && waybillItem != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("waybill", waybill);
            response.put("waybillItem", waybillItem);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }




