package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.CorridaInternacional;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.services.CorridaInternacionalService;
import br.com.codewave.codewave.services.DestinoService;
import br.com.codewave.codewave.services.MotoristaService;
import br.com.codewave.codewave.services.PassageiroService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/corridainternacional")
public class CorridaInternacionalController {

    @Autowired
    private CorridaInternacionalService corridaInternacionalService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private DestinoService destinoService;

    @PostMapping(value = "nova")
    public ResponseEntity novaCorridaInternacional(@RequestBody CorridaInternacional corridaInternacional,
                                                   @RequestParam String cpfMotorista,
                                                   @RequestParam String cpfPassageiro,
                                                   @RequestParam Integer destinoId){

        corridaInternacional.setMotorista(motoristaService.acharPorId(cpfMotorista));
        corridaInternacional.setPassageiro(passageiroService.acharPorId(cpfPassageiro));
        corridaInternacional.setDestino(destinoService.acharPorId(destinoId));

        try {
            corridaInternacionalService.adicionar(corridaInternacional);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar corrida internacional!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Corrida internacional cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    public ResponseEntity listarTodasAsCorridasInternacionais() {
        try {
            return new ResponseEntity(corridaInternacionalService.listarTodos(), HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não há corridas internacionais!", HttpStatus.NOT_FOUND);
        }catch (NullPointerException e) {
            return new ResponseEntity("Algo deu errado!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarPorPassaporte(@PathVariable Integer id) {
        try {
            return new ResponseEntity(corridaInternacionalService.acharPorPassaporte(id), HttpStatus.OK );
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Id não encontrado!", HttpStatus.NOT_FOUND);
        }catch (NullPointerException e) {
            return new ResponseEntity("Algo deu errado!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizarCorridaInternacional(@PathVariable Integer id, CorridaInternacional corridaInternacional) {
        corridaInternacionalService.atualizar(id,corridaInternacional);
        try {
            return new ResponseEntity("Corrida internacional atualizado com sucesso!", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Id não encontrado!", HttpStatus.NOT_FOUND);
        }catch (NullPointerException e) {
            return new ResponseEntity("Algo deu errado!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarCorridaInternacional(@PathVariable Integer id) {
        corridaInternacionalService.remove(id);
        try {
            return new ResponseEntity("Corrida internacional deletada com sucesso!", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Id não encontrado", HttpStatus.NOT_FOUND);
        }catch (NullPointerException e) {
            return  new ResponseEntity("Algo deu errado!", HttpStatus.BAD_REQUEST);
        }
    }
}
