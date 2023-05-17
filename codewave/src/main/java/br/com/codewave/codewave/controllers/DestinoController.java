package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Destino;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/destino")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;


    @PostMapping(value = "/novo")
    public ResponseEntity novoDestino(@RequestBody Destino destino) {
        try {
            destinoService.adicionar(destino);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar destino!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Destino cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(destinoService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de destino!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarDestino(@PathVariable Integer id) {
        try {
            return new ResponseEntity(destinoService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Destino destino) {
        destinoService.atualizar(id, destino);
        try {
            return new ResponseEntity("Destino atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        destinoService.remove(id);
        try {
            return new ResponseEntity("Destino " + id + " cancelado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}