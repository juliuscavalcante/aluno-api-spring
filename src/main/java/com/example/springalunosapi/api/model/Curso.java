package com.example.springalunosapi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "nome", length = 50)
    private String nome;

    @NotBlank
    @Column(name = "descricao", length = 300)
    private String descricao;

    @NotBlank
    @Column(name = "valor")
    private BigDecimal valor;

    @JsonIgnore
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Aluno> alunos;

    public Curso() {
    }

    public Curso(Long id, String nome, String descricao, BigDecimal valor, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id.equals(curso.id) && nome.equals(curso.nome) && descricao.equals(curso.descricao) && valor.equals(curso.valor) && alunos.equals(curso.alunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, valor, alunos);
    }
}
