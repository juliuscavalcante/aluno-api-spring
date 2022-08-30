package com.example.springalunosapi.api.service.impl;

import com.example.springalunosapi.api.exception.IdInvalidoException;
import com.example.springalunosapi.api.exception.RecursoNaoEncontradoException;
import com.example.springalunosapi.api.exception.ValidacaoException;
import com.example.springalunosapi.api.model.Curso;
import com.example.springalunosapi.api.repository.CursoRepository;
import com.example.springalunosapi.api.service.CursoService;
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
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    private void validId(Long id) {
        if (id == null || id <= 0) {
            throw new IdInvalidoException();
        } else if (!existsById(id)) {
            throw new RecursoNaoEncontradoException();
        }
    }

    private void validar(Curso curso) {
        List<String> erros = new LinkedList<>();
        if (StringUtils.isEmpty(curso.getNome())) {
            erros.add("O nome deve ser informado");
        } else if (curso.getNome().length() > 80) {
            erros.add("O nome deve ser inferior a 80 caracteres");
        } else {
            final Long id = curso.getId() == null ? 0L : curso.getId();
            if (cursoRepository.existsByIdNotAndNome(id, curso.getNome())) {
                erros.add("O curso já está cadastrado no sistema.");
            }
        }
            if (curso.getValor() == null) {
                erros.add("O valor deve ser informado.");
            } else if (curso.getValor().signum() <=0) {
                erros.add("O valor deve ser maior que zero.");
            }

            if (StringUtils.isNotEmpty(curso.getDescricao()) && curso.getDescricao().length() > 300) {
                erros.add("A descrição deve possuir no máximo 300 caraceteres.");
            }

            if (erros.size() > 0) {
                throw new ValidacaoException(erros);
            }

    }


    @Override
    public boolean existsById(Long id) {
        return cursoRepository.existsById(id);
    }

    @Override
    public Curso insert(Curso curso) {
        validar(curso);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Curso curso, Long id) {
        validId(id);
        curso.setId(id);
        validar(curso);
        return cursoRepository.save(curso);
    }

    @Override
    public Optional<Curso> findById(Long id) {
        validId(id);
        return cursoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        validId(id);
        cursoRepository.deleteById(id);
    }


    @Override
    public Page<Curso> paginar(Integer page, Integer size) {
        return cursoRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nome")));
    }

    @Override
    public List<Curso> findAll(){
        return cursoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
}