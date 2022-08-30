package com.example.springalunosapi.api.repository;

import com.example.springalunosapi.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByIdNotAndCpf(Long id, String cpf);

}
