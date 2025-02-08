package com.vortechgroup.queen.skies.repository;

import com.vortechgroup.queen.skies.domain.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
