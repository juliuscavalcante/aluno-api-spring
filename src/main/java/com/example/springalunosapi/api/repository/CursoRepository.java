package com.example.springalunosapi.api.repository;

import com.example.springalunosapi.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByIdNotAndNome(Long id, String nome);
}
