package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.services.EmpresaService;
import br.com.codewave.codewave.services.MotoristaService;
import br.com.codewave.codewave.services.PassageiroService;
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
    private PassageiroService passageiroService;

    @Autowired
    private EmpresaService empresaService;


    @PostMapping(value = "/novo")
    public ResponseEntity novoMotorista(@RequestBody Motorista motorista,
                                        @RequestParam(required = false) Integer codigo) {

        motorista.setEmpresa(empresaService.acharPorId(codigo));

        try {
            motoristaService.adicionar(motorista);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar um motorista!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Motorista cadastrado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(motoristaService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar um historico de motoristas!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/localizacao")
    public ResponseEntity localizacao(@RequestParam Integer codigoPassageiro,
                                      @RequestParam Integer codigoMotorista) {

        Passageiro passageiroAchado = passageiroService.acharPorId(codigoPassageiro);
        Motorista motoristaAchado = motoristaService.acharPorId(codigoMotorista);

        try{
            return new ResponseEntity<>( String.format("%.3f", motoristaService.calculoDeProximidade(passageiroAchado.getLatitude(),
                    passageiroAchado.getLongitude(), motoristaAchado.getLatitude(), motoristaAchado.getLongitude())) + " KM", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Algum do(s) Id(s) não existe!" , HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(motoristaService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Motorista motorista) {
        motoristaService.atualizar(id, motorista);
        try {
            return new ResponseEntity("Motorista atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        motoristaService.remove(id);
        try {
            return new ResponseEntity("Motorista " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
