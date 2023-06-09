package com.drone.drone.orders;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_object")
public class OrderObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String address;
    private String phoneNumber;
    private  String email;
    private boolean processed;

    @OneToMany(mappedBy = "orderObject", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

}


