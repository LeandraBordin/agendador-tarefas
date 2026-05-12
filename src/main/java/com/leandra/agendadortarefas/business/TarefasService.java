package com.leandra.agendadortarefas.business;

import com.leandra.agendadortarefas.business.dto.TarefasDTO;
import com.leandra.agendadortarefas.business.mapper.TarefaUpdateMapper;
import com.leandra.agendadortarefas.business.mapper.TarefasMapper;
import com.leandra.agendadortarefas.infrastructure.entity.Tarefas;
import com.leandra.agendadortarefas.infrastructure.enums.Status;
import com.leandra.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.leandra.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.leandra.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasMapper tarefasMapper;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateMapper tarefaUpdateMapper;
    public TarefasDTO gravarTarefas(String token, TarefasDTO tarefasDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacao(Status.PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        Tarefas entity = tarefasMapper.paraTarefaEntity(tarefasDTO);

        return tarefasMapper.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasMapper.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefasDTO> buscaTarefasAgendadasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefasMapper.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorID(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por ID: Id não encontrado" + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(Status status, String id) {
        try {
            Tarefas entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            entity.setStatusNotificacao(status);
            return tarefasMapper.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new RuntimeException("Erro ao alterar Status da tarefa"+e.getCause());
        }
    }
    public TarefasDTO updateTarefas(TarefasDTO tarefasDTO,String id){
        try {
            Tarefas entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            entity.setDataAlteracao(LocalDateTime.now());
            tarefaUpdateMapper.updateTarefas(tarefasDTO, entity);
            return tarefasMapper.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new RuntimeException("Erro ao alterar tarefa"+e.getCause());
        }
    }
}
