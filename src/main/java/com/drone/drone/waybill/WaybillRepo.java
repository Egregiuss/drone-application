package com.drone.drone.waybill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaybillRepo extends JpaRepository<Waybill, Long> {
    Optional<Waybill> findByOrderId(Long orderId);
}
