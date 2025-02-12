package com.example.andaluciaskills.service.base;

import com.example.andaluciaskills.dto.EspecialidadDTO;
import java.util.List;
import java.util.Optional;

public interface EspecialidadServiceBase {
    List<EspecialidadDTO> obtenerTodas();
    Optional<EspecialidadDTO> obtenerPorId(Integer id);
    EspecialidadDTO agregarEspecialidad(EspecialidadDTO especialidadDTO);
    void eliminarEspecialidad(Integer id);
}