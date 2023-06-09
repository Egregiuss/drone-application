package com.drone.drone.waybill;

import com.drone.drone.orders.OrderItem;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "waybill")
public class Waybill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;

    private BigDecimal totalWeight;


    private BigDecimal totalPrice;
    private Long orderId;
    private String drone_name;



}





