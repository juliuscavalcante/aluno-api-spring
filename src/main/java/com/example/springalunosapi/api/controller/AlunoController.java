package com.example.springalunosapi.api.controller;

import com.example.springalunosapi.api.exception.RecursoNaoEncontradoException;
import com.example.springalunosapi.api.model.Aluno;
import com.example.springalunosapi.api.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "page")
    public ResponseEntity<Page<Aluno>> paginar(@RequestParam(defaultValue = "0")int page,
                                               @RequestParam(defaultValue = "10") int size) throws Exception {
        final Page<Aluno> pag = alunoService.paginar(page, size);
        if(CollectionUtils.isEmpty(pag.getContent())) {
            throw new RecursoNaoEncontradoException();
        }
        return ResponseEntity.ok(pag);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() throws Exception {
        final List<Aluno> lista = alunoService.findAll();
        if (CollectionUtils.isEmpty(lista)) {
            throw new RecursoNaoEncontradoException();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) throws Exception {
        final Aluno aluno = alunoService.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> insert(@RequestBody Aluno aluno) throws  Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.insert(aluno));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable long id) throws Exception {
        return ResponseEntity.ok(alunoService.update(aluno, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
