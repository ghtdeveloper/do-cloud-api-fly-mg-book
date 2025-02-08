package com.vortechgroup.queen.skies.repository;

import com.vortechgroup.queen.skies.domain.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
}
