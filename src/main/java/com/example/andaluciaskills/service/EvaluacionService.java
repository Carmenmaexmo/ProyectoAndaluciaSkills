package com.example.andaluciaskills.service;

import com.example.andaluciaskills.model.Evaluacion;
import com.example.andaluciaskills.repository.EvaluacionRepository;
import com.example.andaluciaskills.service.base.EvaluacionServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService implements EvaluacionServiceBase {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    @Override
    public List<Evaluacion> obtenerTodas() {
        return evaluacionRepository.findAll();
    }

    @Override
    public Optional<Evaluacion> obtenerPorId(Integer id) {
        return evaluacionRepository.findById(id);
    }

    @Override
    public Evaluacion agregarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public void eliminarEvaluacion(Integer id) {
        evaluacionRepository.deleteById(id);
    }
}