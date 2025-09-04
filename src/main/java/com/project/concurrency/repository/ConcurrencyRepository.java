package com.project.concurrency.repository;

import com.project.concurrency.entity.Concurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConcurrencyRepository extends JpaRepository<Concurrency, UUID> {
}
