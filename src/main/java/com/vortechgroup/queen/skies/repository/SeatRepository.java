package com.vortechgroup.queen.skies.repository;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import com.vortechgroup.queen.skies.domain.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    Optional<SeatEntity> findBySeatNumberAndFlight(String seatNumber, FlightEntity flight);

    Optional<SeatEntity> findByFlight_FlightNumber(String flightNumber);

}
