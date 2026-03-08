package com.grappling.grapplingcoach.repository;

import com.grappling.grapplingcoach.domain.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Long> {
}