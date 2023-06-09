package com.drone.drone.model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {
    private final DroneRepo droneRepo;

    public DroneService(DroneRepo droneRepo) {
        this.droneRepo = droneRepo;
    }

    public List<DroneSpecs> getAllDrones() {
        return droneRepo.findAll();
    }
}

