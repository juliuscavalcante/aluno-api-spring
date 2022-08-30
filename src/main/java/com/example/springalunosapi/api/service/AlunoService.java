package com.example.springalunosapi.api.service;

import com.example.springalunosapi.api.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlunoService {

    Aluno insert(Aluno aluno);

    Aluno update(Aluno aluno, Long id);

    Optional<Aluno> findById(Long id);

    void delete(Long id);

    Page<Aluno> paginar(Integer page, Integer size);

    List<Aluno> findAll();

}
