package com.project.concurrency.concurrent;

import com.project.concurrency.services.ConcurrencyService;

import java.util.UUID;

public class FindByIdWithRunnable implements Runnable{
    private final ConcurrencyService concurrencyService;
    private final UUID id;

    public FindByIdWithRunnable(ConcurrencyService concurrencyService, UUID id) {
        this.concurrencyService = concurrencyService;
        this.id = id;
    }

    @Override
    public void run() {
        concurrencyService.findById(id);
        System.out.println("Buscando por id: " + id);
    }
}
