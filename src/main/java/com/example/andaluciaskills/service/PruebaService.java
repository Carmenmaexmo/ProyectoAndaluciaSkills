package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.PruebaDTO;
import com.example.andaluciaskills.model.Especialidad;
import com.example.andaluciaskills.model.Prueba;
import com.example.andaluciaskills.repository.EspecialidadRepository;
import com.example.andaluciaskills.repository.PruebaRepository;
import com.example.andaluciaskills.service.base.PruebaServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PruebaService implements PruebaServiceBase {

    private final PruebaRepository pruebaRepository;
    private final EspecialidadRepository especialidadRepository;

    public PruebaService(PruebaRepository pruebaRepository, EspecialidadRepository especialidadRepository) {
        this.pruebaRepository = pruebaRepository;
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<PruebaDTO> obtenerTodas() {
        return pruebaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PruebaDTO> obtenerPorId(Integer id) {
        return pruebaRepository.findById(id)
                .map(this::convertirADTO);
    }

    @Override
    public PruebaDTO agregarPrueba(PruebaDTO pruebaDTO) {
        Prueba prueba = convertirAEntidad(pruebaDTO);
        Prueba pruebaGuardada = pruebaRepository.save(prueba);
        return convertirADTO(pruebaGuardada);
    }

    @Override
    public void eliminarPrueba(Integer id) {
        pruebaRepository.deleteById(id);
    }

    private PruebaDTO convertirADTO(Prueba prueba) {
        PruebaDTO pruebaDTO = new PruebaDTO();
        pruebaDTO.setIdPrueba(prueba.getIdPrueba());
        pruebaDTO.setEnunciado(prueba.getEnunciado());
        pruebaDTO.setPuntuacionMaxima(prueba.getPuntuacionMaxima());
        pruebaDTO.setEspecialidadId(prueba.getEspecialidad().getIdEspecialidad());
        return pruebaDTO;
    }

    private Prueba convertirAEntidad(PruebaDTO pruebaDTO) {
        Prueba prueba = new Prueba();
        prueba.setIdPrueba(pruebaDTO.getIdPrueba());
        prueba.setEnunciado(pruebaDTO.getEnunciado());
        prueba.setPuntuacionMaxima(pruebaDTO.getPuntuacionMaxima());
        Especialidad especialidad = especialidadRepository.findById(pruebaDTO.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        prueba.setEspecialidad(especialidad);
        return prueba;
    }
}