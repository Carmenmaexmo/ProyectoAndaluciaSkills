package com.example.andaluciaskills.repository;

import com.example.andaluciaskills.model.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {

    //Buscar pruebas por especialidad
    @Query("SELECT p FROM Prueba p WHERE p.especialidad.idEspecialidad = :especialidadId")
    List<Prueba> findByEspecialidad(Integer especialidadId);
}