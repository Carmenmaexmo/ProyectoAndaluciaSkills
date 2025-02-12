package com.example.andaluciaskills.repository;

import com.example.andaluciaskills.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    //Buscar participantes por especialidad (id)
    @Query("SELECT p FROM Participante p WHERE p.especialidad.idEspecialidad = :especialidadId")
    List<Participante> findByEspecialidad(Integer especialidadId);

    //Buscar participante por nombre
    List<Participante> findByNombreContaining(String nombre);
}