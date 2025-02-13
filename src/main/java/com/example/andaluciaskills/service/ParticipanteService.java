package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.ParticipanteDTO;
import com.example.andaluciaskills.mapper.ParticipanteMapper;
import com.example.andaluciaskills.model.Especialidad;
import com.example.andaluciaskills.model.Participante;
import com.example.andaluciaskills.repository.EspecialidadRepository;
import com.example.andaluciaskills.repository.ParticipanteRepository;
import com.example.andaluciaskills.service.base.ParticipanteServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteService implements ParticipanteServiceBase {

    private final ParticipanteRepository participanteRepository;
    private final EspecialidadRepository especialidadRepository;
    private final ParticipanteMapper participanteMapper;

    public ParticipanteService(ParticipanteRepository participanteRepository, EspecialidadRepository especialidadRepository, ParticipanteMapper participanteMapper) {
        this.participanteRepository = participanteRepository;
        this.especialidadRepository = especialidadRepository;
        this.participanteMapper = participanteMapper;
    }

    @Override
    public List<ParticipanteDTO> obtenerTodos() {
        return participanteRepository.findAll().stream()
                .map(participanteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParticipanteDTO> obtenerPorId(Integer id) {
        return participanteRepository.findById(id)
                .map(participanteMapper::toDTO);
    }

    @Override
    public ParticipanteDTO agregarParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = participanteMapper.toEntity(participanteDTO);
        Especialidad especialidad = especialidadRepository.findById(participanteDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        participante.setEspecialidad(especialidad);
        Participante participanteGuardado = participanteRepository.save(participante);
        return participanteMapper.toDTO(participanteGuardado);
    }

    @Override
    public void eliminarParticipante(Integer id) {
        participanteRepository.deleteById(id);
    }
}