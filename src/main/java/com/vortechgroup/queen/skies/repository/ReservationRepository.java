package com.vortechgroup.queen.skies.repository;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import com.vortechgroup.queen.skies.domain.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByReservationCode(String reservationCode);
    List<ReservationEntity> findByFlight(FlightEntity flight);
}
