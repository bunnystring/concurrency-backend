package com.project.concurrency.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa un proceso de concurrencia en el sistema.
 * Hereda los campos comunes de StandardEntity.
 */
import java.time.LocalDateTime;
@Entity
@Table(name = "concurrences")
@Data
public class Concurrence extends StandardEntity{

    private String name;

    private String status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
