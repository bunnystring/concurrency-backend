package com.project.concurrency.repository;

import com.project.concurrency.entity.Concurrence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConcurrenceRepository extends JpaRepository<Concurrence, UUID> {
}
