package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.EspecialidadDTO;
import com.example.andaluciaskills.mapper.EspecialidadMapper;
import com.example.andaluciaskills.model.Especialidad;
import com.example.andaluciaskills.repository.EspecialidadRepository;
import com.example.andaluciaskills.service.base.EspecialidadServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadService implements EspecialidadServiceBase {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    @Override
    public List<EspecialidadDTO> obtenerTodas() {
        return especialidadRepository.findAll().stream()
                .map(especialidadMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EspecialidadDTO> obtenerPorId(Integer id) {
        return especialidadRepository.findById(id)
                .map(especialidadMapper::toDTO);
    }

    @Override
    public EspecialidadDTO agregarEspecialidad(EspecialidadDTO especialidadDTO) {
        Especialidad especialidad = especialidadMapper.toEntity(especialidadDTO);
        Especialidad especialidadGuardada = especialidadRepository.save(especialidad);
        return especialidadMapper.toDTO(especialidadGuardada);
    }

    @Override
    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }
}