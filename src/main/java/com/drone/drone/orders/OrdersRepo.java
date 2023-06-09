package com.drone.drone.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OrdersRepo extends JpaRepository<OrderObject, Long> {


    Optional<OrderObject> findById(Long orderId);

}
