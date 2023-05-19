package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.CorridaEnum;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.MotoristaService;
import br.com.codewave.codewave.services.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/corrida")
public class CorridaController {

    @Autowired
    private CorridaService corridaService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private PassageiroService passageiroService;


    @PostMapping(value = "/nova")
    public ResponseEntity novaCorrida(@RequestBody Corrida corrida,
                                      @RequestParam Integer motoristaId,
                                      @RequestParam Integer passageiroId) {
        corrida.setMotorista(motoristaService.acharPorId(motoristaId));
        corrida.setPassageiro(passageiroService.acharPorId(passageiroId));


        try {
            corridaService.adicionar(corrida);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar corrida!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Corrida cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(corridaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar uma historico de corrida!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(corridaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Corrida corrida) {
        corridaService.atualizar(id, corrida);
        try {
            return new ResponseEntity("Corrida atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        corridaService.remove(id);
        try {
            return new ResponseEntity("Corrida " + id + " cancelada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
