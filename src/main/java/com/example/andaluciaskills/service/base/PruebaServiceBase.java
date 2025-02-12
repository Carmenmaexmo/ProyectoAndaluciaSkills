package com.example.andaluciaskills.service.base;

import com.example.andaluciaskills.dto.PruebaDTO;
import java.util.List;
import java.util.Optional;

public interface PruebaServiceBase {
    List<PruebaDTO> obtenerTodas();
    Optional<PruebaDTO> obtenerPorId(Integer id);
    PruebaDTO agregarPrueba(PruebaDTO pruebaDTO);
    void eliminarPrueba(Integer id);
}