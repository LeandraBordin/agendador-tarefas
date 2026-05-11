package com.leandra.agendadortarefas.business.mapper;

import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import com.leandra.agendadortarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {
    Tarefas paraTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefaDTO(Tarefas tarefasEntity);
}
