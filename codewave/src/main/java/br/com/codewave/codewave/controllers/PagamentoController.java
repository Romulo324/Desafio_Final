package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Pagamento;
import br.com.codewave.codewave.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um pagamento" , description = "Método que acessa o método adicionar do service e cria o pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Pagamento criado com sucesso!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoPagamento(@RequestBody Pagamento pagamento) {
        try {
            pagamentoService.adicionar(pagamento);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar pagamento!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Pagamento cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    @Operation(summary = "Lista todos os pagamentos" , description = "Método que acessa o método listarTodos do service e lista todos os pagamentos")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Pagamentos listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de pagamentos não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(pagamentoService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de pagamento!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista um pagamento" , description = "Método que acessa o método acharPorId do service e lista um pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Pagamento listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do pagamento não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarPagamento(@PathVariable Integer id) {
        try {
            return new ResponseEntity(pagamentoService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza pagamento" , description = "Método que acessa o método atualizar do service e atualiza o pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Pagamento atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do pagamento não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Pagamento pagamento) {
        pagamentoService.atualizar(id, pagamento);
        try {
            return new ResponseEntity("Pagamento atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove pagamento" , description = "Método que acessa o método remove do service e remove o pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Pagamento removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do pagamento não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        pagamentoService.remove(id);
        try {
            return new ResponseEntity("Pagamento " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
