package com.project.concurrency.concurrent;

import com.project.concurrency.model.dto.ConcurrencyDto;
import com.project.concurrency.services.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveWithThread extends Thread{

    private final ConcurrencyService concurrencyService;

    private final ConcurrencyDto concurrencyDto;

    public SaveWithThread(ConcurrencyService concurrencyService, ConcurrencyDto concurrencyDto) {
        this.concurrencyService = concurrencyService;
        this.concurrencyDto = concurrencyDto;
    }

    @Override
    public void run() {
        try {
            // Simula tarea lenta (por ejemplo, 5 segundos)
            Thread.sleep(10000);
            concurrencyService.save(concurrencyDto);
            System.out.println("Guardado terminado");
        } catch (InterruptedException e) {
            System.out.println("Guardado interrumpido");
            Thread.currentThread().interrupt();
        }
    }
}
