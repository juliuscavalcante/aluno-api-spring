package com.example.springalunosapi.api.service.impl;

import com.example.springalunosapi.api.exception.RecursoNaoEncontradoException;
import com.example.springalunosapi.api.exception.ValidacaoException;
import com.example.springalunosapi.api.model.Aluno;
import com.example.springalunosapi.api.service.AlunoService;
import com.example.springalunosapi.api.service.CursoService;
import com.example.springalunosapi.api.exception.IdInvalidoException;
import com.example.springalunosapi.api.repository.AlunoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoService cursoService;

    private void validId(Long id) {
        if(id == null || id <= 0) {
            throw new IdInvalidoException();
        }
        else if(!alunoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException();
        }
    }

    private void validar(Aluno aluno) {
        List<String> erros = new LinkedList<>();
        if(StringUtils.isEmpty(aluno.getNome())) {
            erros.add("O nome deve ser informado.");
        }
        else if(aluno.getNome().length() > 80) {
            erros.add("Nome deve ter no máximo 80 caracteres.");
        }

        else if(aluno.getCpf().length() > 11) {
            erros.add("Cpf deve ter no máximo 11 caracteres.");
        }
        else {
            final Long id = aluno.getId() == null ? 0L : aluno.getId();
            if(alunoRepository.existsByIdNotAndCpf(id, aluno.getCpf())) {
                erros.add("Este curso já foi cadastrado.");
            }
        }

        if(StringUtils.isNotEmpty(aluno.getEndereco()) && aluno.getEndereco().length() > 150) {
            erros.add("Endereço deve ter no máximo 150 caracteres.");
        }

        if(aluno.getDataDeNascimento() == null) {
            erros.add("Data de nascimento deve ser informada.");
        }

        if(aluno.getCurso() == null || aluno.getCurso().getId() == null) {
            erros.add("Curso deve ser informado.");
        }
        else if(!cursoService.existsById(aluno.getCurso().getId())) {
            erros.add("Curso válido deve ser informado.");
        }

        if(erros.size() > 0) {
            throw new ValidacaoException(erros);
        }
    }

    @Override
    public Aluno insert(Aluno aluno) {
        validar(aluno);
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Aluno aluno, Long id) {
        validId(id);
        aluno.setId(id);
        validar(aluno);
        return alunoRepository.save(aluno);
    }

    @Override
    public Optional<Aluno> findById(Long id){
        validId(id);
        return alunoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        validId(id);
        alunoRepository.deleteById(id);
    }


    @Override
    public Page<Aluno> paginar(Integer page, Integer size) {
        return alunoRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nome")));
    }


    @Override
    public List<Aluno> findAll() {
        return alunoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
}
