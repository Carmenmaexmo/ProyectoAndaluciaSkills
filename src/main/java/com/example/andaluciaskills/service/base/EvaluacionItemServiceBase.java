package com.example.andaluciaskills.service.base;

import com.example.andaluciaskills.dto.EvaluacionItemDTO;
import java.util.List;
import java.util.Optional;

public interface EvaluacionItemServiceBase {
    List<EvaluacionItemDTO> obtenerTodos();
    Optional<EvaluacionItemDTO> obtenerPorId(Integer id);
    EvaluacionItemDTO agregarEvaluacionItem(EvaluacionItemDTO evaluacionItemDTO);
    void eliminarEvaluacionItem(Integer id);
}