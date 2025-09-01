package com.project.concurrency.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ConcurrenceDto {
    private UUID id;
    private String name;
    private String status;
}
