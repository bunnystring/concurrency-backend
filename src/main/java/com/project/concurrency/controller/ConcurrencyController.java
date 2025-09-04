package com.project.concurrency.controller;

import com.project.concurrency.concurrent.SaveWithThread;
import com.project.concurrency.model.dto.ConcurrencyDto;
import com.project.concurrency.model.dto.ConcurrencyListDto;
import com.project.concurrency.services.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/concurrency")
public class ConcurrencyController {

    private final ConcurrencyService concurrencyService;


    public ConcurrencyController(ConcurrencyService concurrencyService) {
        this.concurrencyService = concurrencyService;
    }

    @GetMapping
    public ResponseEntity<ConcurrencyListDto> getAll(){

        ConcurrencyListDto list = concurrencyService.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcurrencyDto> getById(@PathVariable UUID id){

        ConcurrencyDto rs = concurrencyService.findById(id);

        return ResponseEntity.ok(rs);
    }

    @PostMapping
    public ResponseEntity<ConcurrencyDto> create(@RequestBody ConcurrencyDto dto){

        ConcurrencyDto response = concurrencyService.save(dto);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcurrencyDto> update(
            @PathVariable UUID id,
            @RequestBody ConcurrencyDto dto) {

        ConcurrencyDto responseDto = concurrencyService.update(id, dto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        concurrencyService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("threads")
    public ResponseEntity<String> threads(@RequestBody ConcurrencyDto dto) {
        // Inicia el hilo de guardado
        final SaveWithThread hiloGuardar = new SaveWithThread(concurrencyService, dto);
        hiloGuardar.start();

        // Espera máximo 2 segundos por el guardado
        if (!joinThread(hiloGuardar, 2000)) {
            return ResponseEntity.status(500).body("Error en la ejecución concurrente de hiloGuardar.");
        }

        // Ejecuta hilo búsqueda por id si el campo viene
        if (dto.getId() != null) {
            final Thread hiloFindById = new Thread(() -> {
                concurrencyService.findById(dto.getId());
                log.info("Buscando por id {}", dto.getId());
            });
            hiloFindById.start();

            if (!joinThread(hiloFindById, 0)) {
                return ResponseEntity.status(500).body("Error en la ejecución concurrente de hiloFindById.");
            }
        }

        // Ejecuta búsqueda de todos
        final Thread hiloFindAll = new Thread(() -> {
            ConcurrencyListDto concurrencyListDto = concurrencyService.findAll();
            log.info("Buscando todos {}", concurrencyListDto);
        });
        hiloFindAll.start();

        if (!joinThread(hiloFindAll, 0)) {
            return ResponseEntity.status(500).body("Error en la ejecución concurrente de hiloFindAll.");
        }

        final String estadoGuardar = hiloGuardar.isAlive()
                ? "Guardado aún en proceso después de 2 segundos."
                : "Guardado terminó antes de los 2 segundos.";

        return ResponseEntity.ok(
                estadoGuardar + " Ejecución de búsqueda realizada. Revisa la consola/logs."
        );
    }

    /**
     * Espera a que un hilo termine, con timeout opcional.
     * @param thread Hilo a esperar.
     * @param timeoutMillis Timeout en milisegundos (0 para ilimitado).
     * @return true si termina correctamente, false si hay interrupción.
     */
    private boolean joinThread(Thread thread, long timeoutMillis) {
        try {
            if (timeoutMillis > 0) {
                thread.join(timeoutMillis);
            } else {
                thread.join();
            }
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
