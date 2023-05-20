package br.com.codewave.codewave.controllers;


import br.com.codewave.codewave.Models.Parada;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.ParadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um parada" , description = "Método que acessa o método adicionar do service e cria a parada")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Parada criada com sucesso!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity adicionaParada(@RequestBody Parada parada,
                                         @RequestParam(required = false) Integer corridaId) {

        parada.setCorrida(corridaService.acharPorId(corridaId));

        try {
            paradaService.adicionar(parada);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel adicionar uma parada!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Parada adicionada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    @Operation(summary = "Lista todas as paradas" , description = "Método que acessa o método listarTodos do service e lista todas as paradas")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Paradas listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de paradas não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(paradaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de parada!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista uma parada" , description = "Método que acessa o método acharPorId do service e lista uma parada")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Parada listada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da parada não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarParada(@PathVariable Integer id) {
        try {
            return new ResponseEntity(paradaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza parada" , description = "Método que acessa o método atualizar do service e atualiza a parada")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Parada atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do parada não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Parada parada) {
        paradaService.atualizar(id, parada);
        try {
            return new ResponseEntity("Parada atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove parada" , description = "Método que acessa o método remove do service e remove a parada")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Parada removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da parada não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        paradaService.remove(id);
        try {
            return new ResponseEntity("Parada " + id + " cancelada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}