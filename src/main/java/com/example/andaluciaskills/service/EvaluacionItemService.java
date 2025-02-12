package com.example.andaluciaskills.service;

import com.example.andaluciaskills.model.EvaluacionItem;
import com.example.andaluciaskills.repository.EvaluacionItemRepository;
import com.example.andaluciaskills.service.base.EvaluacionItemServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionItemService implements EvaluacionItemServiceBase {

    private final EvaluacionItemRepository evaluacionItemRepository;

    public EvaluacionItemService(EvaluacionItemRepository evaluacionItemRepository) {
        this.evaluacionItemRepository = evaluacionItemRepository;
    }

    @Override
    public List<EvaluacionItem> obtenerTodos() {
        return evaluacionItemRepository.findAll();
    }

    @Override
    public Optional<EvaluacionItem> obtenerPorId(Integer id) {
        return evaluacionItemRepository.findById(id);
    }

    @Override
    public EvaluacionItem agregarEvaluacionItem(EvaluacionItem evaluacionItem) {
        return evaluacionItemRepository.save(evaluacionItem);
    }

    @Override
    public void eliminarEvaluacionItem(Integer id) {
        evaluacionItemRepository.deleteById(id);
    }
}