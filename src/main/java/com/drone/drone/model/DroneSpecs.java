package com.drone.drone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "delivery_drone")
public class DroneSpecs {
    @Id
    private Long id;
    private String drone_name;
    private String description;

}
