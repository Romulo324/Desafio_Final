package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping(value = "/nova")
    public ResponseEntity novoPagamento(@RequestBody Pagamento pagamento) {
        try {
            pagamentoService.adicionar(pagamento);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar pagamento!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Pagamento cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(pagamentoService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de pagamento!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarPagamento(@PathVariable Integer id) {
        try {
            return new ResponseEntity(pagamentoService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Pagamento pagamento) {
        pagamentoService.atualizar(id, pagamento);
        try {
            return new ResponseEntity("Pagamento atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        pagamentoService.remove(id);
        try {
            return new ResponseEntity("Pagamento " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
