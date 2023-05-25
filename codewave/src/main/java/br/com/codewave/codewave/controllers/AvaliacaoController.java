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

    @PostMapping(value = "/add")
    public ResponseEntity addAvaliacao(@RequestBody Avaliacao avaliacao) {
        avaliacaoService.adicionar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.CREATED);
    }
    @GetMapping(value = "/listar")
    public ResponseEntity listarPorMotorista(@RequestBody Avaliacao avaliacao){
        avaliacaoService.listar(avaliacao);
        return  new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    @PutMapping(value = "/atualizar")
    public ResponseEntity atualizarAvaliacao(@RequestBody Avaliacao avaliacao){
        avaliacaoService.atualizar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deletar")
    public ResponseEntity deletarAvaliacao(@RequestBody Avaliacao avaliacao){
        avaliacaoService.deletar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }
}
