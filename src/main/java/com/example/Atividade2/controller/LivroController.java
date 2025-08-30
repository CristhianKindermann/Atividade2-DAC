package com.example.Atividade2.controller;

import com.example.Atividade2.exception.ResourceNotFoundException;
import com.example.Atividade2.model.Livro;
import com.example.Atividade2.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<Page<Livro>> listarPaginado(Pageable pageable) {
        Page<Livro> livros = livroRepository.findAll(pageable);
        // Adiciona links HATEOAS para cada livro na lista
        livros.forEach(livro -> {
            try {
                livro.add(linkTo(methodOn(LivroController.class).buscarPorId(livro.getId())).withSelfRel());
            } catch (ResourceNotFoundException e) {
                // Should not happen in this context
            }
        });
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com id: " + id));

        livro.add(linkTo(methodOn(LivroController.class).buscarPorId(id)).withSelfRel());
        livro.add(linkTo(methodOn(LivroController.class).listarPaginado(null)).withRel("todos-livros"));

        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        Livro novoLivro = livroRepository.save(livro);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoLivro.getId()).toUri();
        return ResponseEntity.created(location).body(novoLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro detalhesLivro) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com id: " + id));

        livro.setTitulo(detalhesLivro.getTitulo());
        livro.setIsbn(detalhesLivro.getIsbn());
        livro.setAnoPublicacao(detalhesLivro.getAnoPublicacao());
        livro.setDisponivel(detalhesLivro.isDisponivel());
        livro.setAutorId(detalhesLivro.getAutorId());

        Livro livroAtualizado = livroRepository.save(livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro não encontrado com id: " + id);
        }
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}