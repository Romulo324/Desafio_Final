package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Usuario;
import br.com.codewave.codewave.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um usuario" , description = "Método que acessa o método adicionar do service e cria usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Usuario criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Parametro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.adicionar(usuario);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar usuario!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Usuario cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    @Operation(summary = "Lista todos os usuarios" , description = "Método que acessa o método listarTodos do service e lista todos os usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Usuarios listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de usuario não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(usuarioService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de usuarios!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista um usuario" , description = "Método que acessa o método acharPorId do service e lista um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Usuario listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do usuario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarUsuario(@PathVariable Integer id) {
        try {
            return new ResponseEntity(usuarioService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza usuario" , description = "Método que acessa o método atualizar do service e atualiza o usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Usuario atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do usuario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Usuario usuario) {
        usuarioService.atualizar(id, usuario);
        try {
            return new ResponseEntity("Usuario atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove usuario" , description = "Método que acessa o método remove do service e remove o usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Usuario removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id do usuario não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        usuarioService.remove(id);
        try {
            return new ResponseEntity("Usuario " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}