package com.example.andaluciaskills.service;

import com.example.andaluciaskills.dto.PruebaDTO;
import com.example.andaluciaskills.mapper.PruebaMapper;
import com.example.andaluciaskills.model.Prueba;
import com.example.andaluciaskills.repository.PruebaRepository;
import com.example.andaluciaskills.service.base.PruebaServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PruebaService implements PruebaServiceBase {

    private final PruebaRepository pruebaRepository;
    private final PruebaMapper pruebaMapper;

    public PruebaService(PruebaRepository pruebaRepository, PruebaMapper pruebaMapper) {
        this.pruebaRepository = pruebaRepository;
        this.pruebaMapper = pruebaMapper;
    }

    @Override
    public List<PruebaDTO> obtenerTodas() {
        return pruebaRepository.findAll().stream()
                .map(pruebaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PruebaDTO> obtenerPorId(Integer id) {
        return pruebaRepository.findById(id)
                .map(pruebaMapper::toDTO);
    }

    @Override
    public PruebaDTO agregarPrueba(PruebaDTO pruebaDTO) {
        Prueba prueba = pruebaMapper.toEntity(pruebaDTO);
        Prueba pruebaGuardada = pruebaRepository.save(prueba);
        return pruebaMapper.toDTO(pruebaGuardada);
    }

    @Override
    public void eliminarPrueba(Integer id) {
        pruebaRepository.deleteById(id);
    }
}