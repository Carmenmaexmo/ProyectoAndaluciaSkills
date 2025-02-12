package com.example.andaluciaskills.service;

import com.example.andaluciaskills.model.Especialidad;
import com.example.andaluciaskills.repository.EspecialidadRepository;
import com.example.andaluciaskills.service.base.EspecialidadServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements EspecialidadServiceBase {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<Especialidad> obtenerTodas() {
        return especialidadRepository.findAll();
    }

    @Override
    public Optional<Especialidad> obtenerPorId(Integer id) {
        return especialidadRepository.findById(id);
    }

    @Override
    public Especialidad agregarEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public void eliminarEspecialidad(Integer id) {
        especialidadRepository.deleteById(id);
    }
}