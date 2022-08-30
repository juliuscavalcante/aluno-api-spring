package com.example.springalunosapi.api.controller;

import com.example.springalunosapi.api.exception.RecursoNaoEncontradoException;
import com.example.springalunosapi.api.model.Curso;
import com.example.springalunosapi.api.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "page")
    public ResponseEntity<Page<Curso>> paginar(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) throws Exception {
        final Page<Curso> pag = cursoService.paginar(page, size);
        if (CollectionUtils.isEmpty(pag.getContent())) {
            throw new RecursoNaoEncontradoException();
        }
        return ResponseEntity.ok(pag);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() throws Exception {
        final List<Curso> lista = cursoService.findAll();
        if (CollectionUtils.isEmpty(lista)) {
            throw new RecursoNaoEncontradoException();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Curso> findById(@PathVariable long id) throws Exception {
        final Curso curso = cursoService.findById(id).orElseThrow(RecursoNaoEncontradoException::new);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> insert(@RequestBody Curso curso) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.insert(curso));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Curso> update(@RequestBody Curso curso, @PathVariable long id) throws Exception {
        return ResponseEntity.ok(cursoService.update(curso, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws Exception {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}