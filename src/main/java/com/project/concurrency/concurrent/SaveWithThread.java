package com.project.concurrency.concurrent;

import com.project.concurrency.model.dto.ConcurrenceDto;
import com.project.concurrency.services.ConcurrenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveWithThread extends Thread{

    private final ConcurrenceService concurrenceService;

    private final ConcurrenceDto concurrenceDto;

    public SaveWithThread(ConcurrenceService concurrenceService, ConcurrenceDto concurrenceDto) {
        this.concurrenceService = concurrenceService;
        this.concurrenceDto = concurrenceDto;
    }

    @Override
    public void run() {
        try {
            // Simula tarea lenta (por ejemplo, 5 segundos)
            Thread.sleep(10000);
            concurrenceService.save(concurrenceDto);
            System.out.println("Guardado terminado");
        } catch (InterruptedException e) {
            System.out.println("Guardado interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}
