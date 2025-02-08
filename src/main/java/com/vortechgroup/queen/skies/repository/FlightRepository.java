package com.vortechgroup.queen.skies.repository;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    Optional<FlightEntity> findByFlightNumber(String flightNumber);
}
