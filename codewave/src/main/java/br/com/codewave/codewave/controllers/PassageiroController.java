package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.PassageiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {

    @Autowired
    private JavaMailSender enviar;

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private CorridaService corridaService;


    @PostMapping(value = "/nova")
    @Operation(summary = "Cria um passageiro" , description = "Método que acessa o método adicionar do service e cria passageiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Passageiro criado com sucesso!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoPassageiro(@Valid @RequestBody Passageiro passageiro,
                                      @RequestParam(required = false) Integer corridaId) {

        passageiro.setCorrida(corridaService.acharPorId(corridaId));

        try {
            passageiroService.adicionar(passageiro);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel cadastrar um passageiro!" , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Passageiro cadastrada com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodas")
    @Operation(summary = "Lista todos os passageiros" , description = "Método que acessa o método listarTodos do service e lista todos os passageiros")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - passageiros listados com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Lista de passageiros não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarTodas() {
        try {
            return new ResponseEntity(passageiroService.listarTodos() , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar uma historico de passageiros!" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Lista um passageiro" , description = "Método que acessa o método acharPorId do service e lista um passageiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Passageiro listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do passageiro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarCorrida(@PathVariable Integer id) {
        try {
            return new ResponseEntity(passageiroService.acharPorId(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza passageiro" , description = "Método que acessa o método atualizar do service e atualiza o passageiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Passageiro atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do passageiro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable Integer id , @RequestBody Passageiro passageiro) {
        passageiroService.atualizar(id, passageiro);
        try {
            return new ResponseEntity("Passageiro atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enviar-senha/{email}")
    @ResponseBody
    public String enviarSenha(@PathVariable String email) {
        Passageiro passageiro = passageiroService.buscarPorEmail(email);

        if (passageiro == null) {
            return "E-mail não encontrado";

        }

        String novaSenha = passageiroService.gerarNovaSenha();
        passageiro.setSenha(novaSenha);
        passageiroService.adicionar(passageiro);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(passageiro.getEmail());
        message.setSubject("Nova senha");
        message.setText("Sua nova senha de acesso é: " + novaSenha);

        try {
            enviar.send(message);
            return "Nova e-mail envia sucesso";
        } catch (MailException e) {
            return "Erro - e-mail não envia";
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Remove passageiro" , description = "Método que acessa o método remove do service e remove o passageiro")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "OK - Passageiro removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do passageiro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable Integer id) {
        passageiroService.remove(id);
        try {
            return new ResponseEntity("Passageiro " + id + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
    }
}
