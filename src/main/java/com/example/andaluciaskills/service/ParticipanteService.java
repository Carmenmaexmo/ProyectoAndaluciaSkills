package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.ParticipanteDTO;
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

    public ParticipanteService(ParticipanteRepository participanteRepository, EspecialidadRepository especialidadRepository) {
        this.participanteRepository = participanteRepository;
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<ParticipanteDTO> obtenerTodos() {
        return participanteRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParticipanteDTO> obtenerPorId(Integer id) {
        return participanteRepository.findById(id)
                .map(this::convertirADTO);
    }

    @Override
    public ParticipanteDTO agregarParticipante(ParticipanteDTO participanteDTO) {
        Participante participante = convertirAEntidad(participanteDTO);
        Participante participanteGuardado = participanteRepository.save(participante);
        return convertirADTO(participanteGuardado);
    }

    @Override
    public void eliminarParticipante(Integer id) {
        participanteRepository.deleteById(id);
    }

    private ParticipanteDTO convertirADTO(Participante participante) {
        ParticipanteDTO participanteDTO = new ParticipanteDTO();
        participanteDTO.setIdParticipante(participante.getIdParticipante());
        participanteDTO.setNombre(participante.getNombre());
        participanteDTO.setApellidos(participante.getApellidos());
        participanteDTO.setCentro(participante.getCentro());
        participanteDTO.setEspecialidadId(participante.getEspecialidad().getIdEspecialidad());
        return participanteDTO;
    }

    private Participante convertirAEntidad(ParticipanteDTO participanteDTO) {
        Participante participante = new Participante();
        participante.setIdParticipante(participanteDTO.getIdParticipante());
        participante.setNombre(participanteDTO.getNombre());
        participante.setApellidos(participanteDTO.getApellidos());
        participante.setCentro(participanteDTO.getCentro());
        Especialidad especialidad = especialidadRepository.findById(participanteDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        participante.setEspecialidad(especialidad);
        return participante;
    }
}