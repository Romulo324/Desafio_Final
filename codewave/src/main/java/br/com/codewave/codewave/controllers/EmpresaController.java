package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Empresa;
import br.com.codewave.codewave.services.EmpresaService;
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
    public ResponseEntity novaEmpresa(@RequestBody Empresa empresa) {
        try {
            empresaService.adicionar(empresa);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar empresa!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Empresa cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(empresaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de empresas!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarEmpresa(@PathVariable Integer id) {
        try {
            return new ResponseEntity(empresaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Empresa empresa) {
        empresaService.atualizar(id, empresa);
        try {
            return new ResponseEntity("Empresa atualizada com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        empresaService.remove(id);
        try {
            return new ResponseEntity("Empresa " + id + " deletada!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
