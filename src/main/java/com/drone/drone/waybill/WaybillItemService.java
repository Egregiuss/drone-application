package com.drone.drone.waybill;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WaybillItemService {

    private final WaybillItemRepo waybillItemRepo;

    public WaybillItemService(WaybillItemRepo waybillItemRepo) {
        this.waybillItemRepo = waybillItemRepo;
    }

    public List<WaybillItem> getWaybillItemById(Long orderId) {
        List<WaybillItem> waybillItem = waybillItemRepo.findByOrderId(orderId);
        if (!waybillItem.isEmpty()) {
            return waybillItem;
        } else {
            throw new NoSuchElementException("WaybillItem with ID " + orderId + " not found");
        }
    }

}

