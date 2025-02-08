package com.vortechgroup.queen.skies.repository;


import com.vortechgroup.queen.skies.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
