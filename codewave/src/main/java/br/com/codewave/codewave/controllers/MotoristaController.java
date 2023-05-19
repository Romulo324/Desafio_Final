package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.EmpresaService;
import br.com.codewave.codewave.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/motorista")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CorridaService corridaService;


    @PostMapping(value = "/novo")
    public ResponseEntity novoMotorista(@RequestBody Motorista motorista,
                                        @RequestParam(required = false) Integer empresaId) {

        motorista.setEmpresa(empresaService.acharPorId(empresaId));

        try {
            motoristaService.adicionar(motorista);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar um motorista!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Motorista cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(motoristaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de motoristas!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(motoristaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Motorista motorista) {
        motoristaService.atualizar(id, motorista);
        try {
            return new ResponseEntity("Motorista " + id + "atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        motoristaService.remove(id);
        try {
            return new ResponseEntity("Motorista " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/aceitar")
    public ResponseEntity aceitarCorrida(@PathVariable Integer id , @RequestBody Corrida corrida) {
        try {
            corridaService.aceitarCorrida(corrida, id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + id + " aceita!" , HttpStatus.OK);
    }

    @PutMapping(value = "/finalizar")
    public ResponseEntity finalizarCorrida(@PathVariable Integer id , @RequestBody Corrida corrida) {
        try {
            corridaService.finalizarCorrida(corrida, id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + id + " finalizada!" , HttpStatus.OK);
    }
}
