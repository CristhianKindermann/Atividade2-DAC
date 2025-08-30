package com.example.Atividade2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class Livro extends RepresentationModel<Livro> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private int anoPublicacao;
    private boolean disponivel;
    private Long autorId;
}