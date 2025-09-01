package com.project.concurrency.controller;

import com.project.concurrency.concurrent.FindAllWithRunnable;
import com.project.concurrency.concurrent.SaveWithThread;
import com.project.concurrency.model.dto.ConcurrenceDto;
import com.project.concurrency.model.dto.ConcurrenceListDto;
import com.project.concurrency.services.ConcurrenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/concurrences")
public class ConcurrenceController {

    private final ConcurrenceService concurrenceService;


    public ConcurrenceController(ConcurrenceService concurrenceService) {
        this.concurrenceService = concurrenceService;
    }

    @GetMapping
    public ResponseEntity<ConcurrenceListDto> getAll(){

        ConcurrenceListDto list = concurrenceService.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcurrenceDto> getById(@PathVariable UUID id){

        ConcurrenceDto rs = concurrenceService.findById(id);

        return ResponseEntity.ok(rs);
    }

    @PostMapping
    public ResponseEntity<ConcurrenceDto> create(@RequestBody ConcurrenceDto dto){

        ConcurrenceDto response = concurrenceService.save(dto);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcurrenceDto> update(
            @PathVariable UUID id,
            @RequestBody ConcurrenceDto dto) {

        ConcurrenceDto responseDto = concurrenceService.update(id, dto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        concurrenceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("threads")
    public ResponseEntity<String> threads(@RequestBody ConcurrenceDto dto) {
        // Inicia el hilo de guardado
        final SaveWithThread hiloGuardar = new SaveWithThread(concurrenceService, dto);
        hiloGuardar.start();

        // Espera máximo 2 segundos por el guardado
        if (!joinThread(hiloGuardar, 2000)) {
            return ResponseEntity.status(500).body("Error en la ejecución concurrente de hiloGuardar.");
        }

        // Ejecuta hilo búsqueda por id si el campo viene
        if (dto.getId() != null) {
            final Thread hiloFindById = new Thread(() -> {
                concurrenceService.findById(dto.getId());
                log.info("Buscando por id {}", dto.getId());
            });
            hiloFindById.start();

            if (!joinThread(hiloFindById, 0)) {
                return ResponseEntity.status(500).body("Error en la ejecución concurrente de hiloFindById.");
            }
        }

        // Ejecuta búsqueda de todos
        final Thread hiloFindAll = new Thread(() -> {
            ConcurrenceListDto concurrenceListDto = concurrenceService.findAll();
            log.info("Buscando todos {}", concurrenceListDto);
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
