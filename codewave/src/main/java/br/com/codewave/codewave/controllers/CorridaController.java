package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;

import br.com.codewave.codewave.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private DestinoService destinoService;

    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping(value = "/nova")
    @Operation(summary = "Cria uma corrida" , description = "Método que acessa o método adicionar do service e cria uma corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Corrida criada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Parametro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novaCorrida(@RequestBody Corrida corrida,
                                      @RequestParam String cpfMotorista,
                                      @RequestParam String cpfPassageiro,
                                      @RequestParam Integer destinoId) {
        corrida.setMotorista(motoristaService.acharPorId(cpfMotorista));
        corrida.setPassageiro(passageiroService.acharPorId(cpfPassageiro));
        corrida.setDestino(destinoService.acharPorId(destinoId));

        try {
            corridaService.adicionar(corrida);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar corrida!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Corrida cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    @Operation(summary = "Lista todas as corridas" , description = "Método que acessa o método listarTodos do service e lista todas as corridas")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corridas listadas com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de corridas não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(corridaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar uma historico de corrida!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista uma corrida" , description = "Método que acessa o método acharPorId do service e lista uma corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corrida listada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(corridaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }catch (NullPointerException e) {
            return new ResponseEntity("" , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza a corrida" , description = "Método que acessa o método atualizar do service e atualiza a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corrida atualizada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Corrida corrida) {
        corridaService.atualizar(id, corrida);
        try {
            return new ResponseEntity("Corrida atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove corrida" , description = "Método que acessa o método remove do service e remove a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corrida removida com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        corridaService.remove(id);
        try {
            return new ResponseEntity("Corrida " + id + " cancelada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
