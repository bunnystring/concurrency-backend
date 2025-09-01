package com.project.concurrency.concurrent;

import com.project.concurrency.services.ConcurrenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindAllWithRunnable implements Runnable{

    private final ConcurrenceService concurrenceService;

    public FindAllWithRunnable(ConcurrenceService concurrenceService) {
        this.concurrenceService = concurrenceService;
    }

    @Override
    public void run(){
        var result = concurrenceService.findAll();
        log.info("Find All con Runnable, {}", result.getItems());
    }
}
