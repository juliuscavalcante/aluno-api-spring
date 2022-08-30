package com.example.springalunosapi.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "nome", length = 80)
    private String nome;

    @NotBlank
    @Column(name = "cpf", length = 11, unique = true)
    @CPF(message = "Digite um CPF válido")
    private String cpf;

    @NotBlank
    @Column(name = "endereco", length = 150)
    private String endereco;

    @NotBlank
    @Column(name = "data_de_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
    @Past(message = "Não é permitido data futura")
    private LocalDate dataDeNascimento;

    @JsonIgnoreProperties({ "nome", "descricao", "valor" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String cpf, String endereco, LocalDate dataDeNascimento, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataDeNascimento = dataDeNascimento;
        this.curso = curso;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", curso=" + curso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id.equals(aluno.id) && nome.equals(aluno.nome) && cpf.equals(aluno.cpf) && endereco.equals(aluno.endereco) && dataDeNascimento.equals(aluno.dataDeNascimento) && curso.equals(aluno.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, endereco, dataDeNascimento, curso);
    }

}
