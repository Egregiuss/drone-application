package com.drone.drone.waybill;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "waybill_item")
public class WaybillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private BigDecimal price;
    private BigDecimal weight;
    private Long orderId;






}

