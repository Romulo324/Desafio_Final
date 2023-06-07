package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Destino;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.DestinoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um destino" , description = "Método que acessa o método adicionar do service e cria um destino")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Destino criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Parametro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoDestino(@RequestBody Destino destino) {
        try {
            destinoService.adicionar(destino);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar destino!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Destino cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    @Operation(summary = "Lista todos os destinos" , description = "Método que acessa o método listarTodos do service e lista todos os destinos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK - Destinos listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de destinos não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(destinoService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de destino!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista um destino" , description = "Método que acessa o método acharPorId do service e lista um destino")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Destino listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do destino não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarDestino(@PathVariable Integer id) {
        try {
            return new ResponseEntity(destinoService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza o destino" , description = "Método que acessa o método atualizar do service e atualiza o destino")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Destino atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do destino não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Destino destino) {
        destinoService.atualizar(id, destino);
        try {
            return new ResponseEntity("Destino atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove destino" , description = "Método que acessa o método remove do service e remove o destino")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Destino removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do destino não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        destinoService.remove(id);
        try {
            return new ResponseEntity("Destino " + id + " cancelado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}