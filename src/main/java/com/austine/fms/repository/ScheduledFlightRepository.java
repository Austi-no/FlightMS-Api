package com.austine.fms.repository;

import com.austine.fms.model.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, Long> {
}
