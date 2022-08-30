package com.example.springalunosapi.api.service;

import com.example.springalunosapi.api.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CursoService {

    boolean existsById(Long id);

    Curso insert(Curso curso);

    Curso update(Curso curso, Long id);

    Optional<Curso> findById(Long id);

    void delete(Long id);

    Page<Curso> paginar(Integer page, Integer size);

    List<Curso> findAll();
}
