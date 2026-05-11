package com.leandra.agendadortarefas.controller;

import com.leandra.agendadortarefas.business.TarefasService;
import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO tarefasDTO, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefas(token,tarefasDTO));
    }

}
