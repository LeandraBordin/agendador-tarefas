package com.leandra.agendadortarefas.business.mapper;

import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import com.leandra.agendadortarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasMapper {
    Tarefas paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(Tarefas tarefasEntity);

    List<Tarefas> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOS);
    List<TarefasDTO> paraListaTarefasDTO(List<Tarefas> tarefas);
}
