package br.com.codewave.codewave.controllers;


import br.com.codewave.codewave.Models.Parada;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/parada")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @Autowired
    private CorridaService corridaService;


    @PostMapping(value = "/nova")
    public ResponseEntity adicionaParada(@RequestBody Parada parada,
                                         @RequestParam Integer codigo) {

        parada.setCorrida(corridaService.acharPorId(codigo));

        try {
            paradaService.adicionar(parada);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel adicionar uma parada!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Parada adicionada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(paradaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de parada!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarParada(@PathVariable Integer id) {
        try {
            return new ResponseEntity(paradaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Parada parada) {
        paradaService.atualizar(id, parada);
        try {
            return new ResponseEntity("Parada atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        paradaService.remove(id);
        try {
            return new ResponseEntity("Parada " + id + " cancelada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
