package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private CorridaService corridaService;


    @PostMapping(value = "/nova")
    public ResponseEntity novaCorrida(@RequestBody Passageiro passageiro,
                                      @RequestParam Integer corridaId,
                                      @RequestParam Integer id1) {

        passageiro.setCorrida(corridaService.acharPorId(corridaId));


        try {
            passageiroService.adicionar(passageiro);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar um passageiro!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Passageiro cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(passageiroService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar uma historico de passageiros!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(passageiroService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Passageiro passageiro) {
        passageiroService.atualizar(id, passageiro);
        try {
            return new ResponseEntity("Passageiro atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        passageiroService.remove(id);
        try {
            return new ResponseEntity("Passageiro " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
