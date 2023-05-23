package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.EmpresaService;
import br.com.codewave.codewave.services.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um motorista" , description = "Método que acessa o método adicionar do service e cria o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Pagamento criado com sucesso!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
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
    @Operation(summary = "Lista todos os motoristas" , description = "Método que acessa o método listarTodos do service e lista todos os motoristas")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Motoristas listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de motoristas não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(motoristaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de motoristas!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista um motorista" , description = "Método que acessa o método acharPorId do service e lista um motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Motorista listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(motoristaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza o motorista" , description = "Método que acessa o método atualizar do service e atualiza o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Motorista atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Motorista motorista) {
        motoristaService.atualizar(id, motorista);
        try {
            return new ResponseEntity("Motorista " + id + "atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove motorista" , description = "Método que acessa o método remove do service e remove o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Motorista removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        motoristaService.remove(id);
        try {
            return new ResponseEntity("Motorista " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/aceitar")
    @Operation(summary = "Aceita corrida" , description = "Método que acessa o método aceitarCorrida do service e aceita a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Corrida aceita!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity aceitarCorrida(@PathVariable Integer id , @RequestBody Corrida corrida) {
        try {
            corridaService.aceitarCorrida(corrida, id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + id + " aceita!" , HttpStatus.OK);
    }

    @PutMapping(value = "/finalizar")
    @Operation(summary = "Finaliza corrida" , description = "Método que acessa o método finalizarCorrida do service e finaliza a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Corrida finalizada!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity finalizarCorrida(@PathVariable Integer id , @RequestBody Corrida corrida) {
        try {
            corridaService.finalizarCorrida(corrida, id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + id + " finalizada!" , HttpStatus.OK);
    }
}
