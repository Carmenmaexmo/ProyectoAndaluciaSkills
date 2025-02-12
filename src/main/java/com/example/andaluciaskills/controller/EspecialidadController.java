package com.example.andaluciaskills.controller;

import com.example.andaluciaskills.dto.EspecialidadDTO;
import com.example.andaluciaskills.service.EspecialidadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public List<EspecialidadDTO> listar() {
        return especialidadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<EspecialidadDTO> obtener(@PathVariable Integer id) {
        return especialidadService.obtenerPorId(id);
    }

    @PostMapping
    public EspecialidadDTO agregar(@RequestBody EspecialidadDTO especialidadDTO) {
        return especialidadService.agregarEspecialidad(especialidadDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        especialidadService.eliminarEspecialidad(id);
    }
}
