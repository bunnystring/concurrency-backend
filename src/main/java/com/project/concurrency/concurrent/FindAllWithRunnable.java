package com.project.concurrency.concurrent;

import com.project.concurrency.services.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindAllWithRunnable implements Runnable{

    private final ConcurrencyService concurrencyService;

    public FindAllWithRunnable(ConcurrencyService concurrencyService) {
        this.concurrencyService = concurrencyService;
    }

    @Override
    public void run(){
        var result = concurrencyService.findAll();
        log.info("Find All con Runnable, {}", result.getItems());
    }
}
