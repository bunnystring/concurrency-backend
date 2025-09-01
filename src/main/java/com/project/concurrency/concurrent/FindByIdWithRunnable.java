package com.project.concurrency.concurrent;

import com.project.concurrency.services.ConcurrenceService;

import java.util.UUID;

public class FindByIdWithRunnable implements Runnable{
    private final ConcurrenceService concurrenceService;
    private final UUID id;

    public FindByIdWithRunnable(ConcurrenceService concurrenceService, UUID id) {
        this.concurrenceService = concurrenceService;
        this.id = id;
    }

    @Override
    public void run() {
        concurrenceService.findById(id);
        System.out.println("Buscando por id: " + id);
    }
}
