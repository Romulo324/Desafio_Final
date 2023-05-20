package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Avaliacao;
import br.com.codewave.codewave.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping(value = "/novas")
    public ResponseEntity minhasAvaliacoes(@RequestBody Avaliacao avaliacao) {
        avaliacaoService.adicionar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity ListarTodos() {
        return new ResponseEntity(avaliacaoService.listarTodos(), HttpStatus.OK);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity ListarporAvaliacao(@PathVariable Integer id) {
        return new ResponseEntity(avaliacaoService.acharPorId(id), HttpStatus.OK);

    }
    @PutMapping(value = "/tarefas/{titulo}")
    public ResponseEntity Alterar(@PathVariable Integer id, @RequestBody Avaliacao avaliacao) {
        avaliacaoService.atualizar(id, avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }



}
