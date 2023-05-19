package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Empresa;
import br.com.codewave.codewave.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    @PostMapping(value = "/nova")
    @Operation(summary = "Cria uma empresa" , description = "Método que acessa o método adicionar do service e cria uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Pagamento criado com sucesso!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novaEmpresa(@RequestBody Empresa empresa) {
        try {
            empresaService.adicionar(empresa);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar empresa!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Empresa cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    @Operation(summary = "Lista todas as empresas" , description = "Método que acessa o método listarTodos do service e lista todas as empresas")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Empresas listadas com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de empresas não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(empresaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de empresas!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista uma empresa" , description = "Método que acessa o método acharPorId do service e lista uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Empresa listada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CNPJ da empresa não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarEmpresa(@PathVariable Integer id) {
        try {
            return new ResponseEntity(empresaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CNPJ não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza a empresa" , description = "Método que acessa o método atualizar do service e atualiza a empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Empresa atualizada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CNPJ da empresa não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Empresa empresa) {
        empresaService.atualizar(id, empresa);
        try {
            return new ResponseEntity("Empresa atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CNPJ não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove empresa" , description = "Método que acessa o método remove do service e remove a empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Empresa removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CNPJ da empresa não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        empresaService.remove(id);
        try {
            return new ResponseEntity("Empresa " + id + " deletada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CNPJ não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
