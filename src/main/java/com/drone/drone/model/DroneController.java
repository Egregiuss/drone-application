package com.drone.drone.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private final DroneService droneService;
    private final DroneRepo droneRepo;


    public DroneController (DroneService droneService, DroneRepo droneRepo){
        this.droneService = droneService;
        this.droneRepo = droneRepo;
    }

    @GetMapping
    public List<DroneSpecs> getAllDrones() {
        return droneService.getAllDrones();
    }
    @PostMapping("/add-drones")
    public ResponseEntity<DroneSpecs> addDrone(@RequestBody DroneSpecs drone) {
        DroneSpecs newDrone = droneRepo.save(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
    }
}

