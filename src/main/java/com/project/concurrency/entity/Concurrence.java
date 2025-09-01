package com.project.concurrency.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "concurrences")
@Data
public class Concurrence {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    private String name;

    private String status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
