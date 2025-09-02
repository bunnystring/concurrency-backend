package com.project.concurrency.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad base estandar para heredar en el modelo de datos.
 * Contiene campos comunes de auditoria, control y estado.
 */
@Data
@MappedSuperclass
public class StandardEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    @Version
    private Long version;

    @Column(nullable = false, columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    private String createdBy;
    private String updatedBy;

    private Boolean active = true;
    private Boolean deleted = false;

    /**
     * Asigna automáticamente las fechas de creación y actualización antes de persistir la entidad.
     */
    @PrePersist
    protected void onCreate() {
        this.dateCreate = LocalDateTime.now();
        this.dateUpdate = this.dateCreate;
    }

    /**
     * Actualiza la fecha de modificación antes de actualizar la entidad.
     */
    @PreUpdate
    protected void onUpdate() {
        this.dateUpdate = LocalDateTime.now();
    }
}
