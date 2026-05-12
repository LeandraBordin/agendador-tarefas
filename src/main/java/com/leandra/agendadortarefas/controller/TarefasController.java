package com.leandra.agendadortarefas.controller;

import com.leandra.agendadortarefas.business.TarefasService;
import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import com.leandra.agendadortarefas.infrastructure.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO tarefasDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefas(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial,dataFinal));
    }
    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String token){
        return  ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorEmail(token));
    }
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id){
        tarefasService.deletaTarefaPorID(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status")Status status, @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }
    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO tarefasDTO, @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO,id));
    }
}
