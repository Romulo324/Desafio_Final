package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Usuario;
import br.com.codewave.codewave.services.UsuarioService;
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
    public ResponseEntity novoUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.adicionar(usuario);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar usuario!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Usuario cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(usuarioService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de usuarios!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarUsuario(@PathVariable Integer id) {
        try {
            return new ResponseEntity(usuarioService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Usuario usuario) {
        usuarioService.atualizar(id, usuario);
        try {
            return new ResponseEntity("Usuario atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        usuarioService.remove(id);
        try {
            return new ResponseEntity("Usuario " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
