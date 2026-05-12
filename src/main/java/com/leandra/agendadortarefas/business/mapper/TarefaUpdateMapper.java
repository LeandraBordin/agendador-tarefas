package com.leandra.agendadortarefas.business.mapper;

import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import com.leandra.agendadortarefas.infrastructure.entity.Tarefas;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {
    void updateTarefas(TarefasDTO tarefasDTO, @MappingTarget Tarefas entity);
}
