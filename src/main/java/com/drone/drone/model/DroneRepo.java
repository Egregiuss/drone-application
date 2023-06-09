package com.drone.drone.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DroneRepo extends JpaRepository<DroneSpecs, Long> {

    }
