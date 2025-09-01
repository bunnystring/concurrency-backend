package com.project.concurrency.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConcurrenceListDto {

    private List<ConcurrenceDto> items;
}
