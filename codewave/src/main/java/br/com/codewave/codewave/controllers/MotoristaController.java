package br.com.codewave.codewave.controllers;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.Models.Pagamento;
import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.repositories.MotoristaRepository;
import br.com.codewave.codewave.services.*;
import br.com.codewave.codewave.util.FileUpLoadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private MotoristaRepository motoristaRepository;


    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um motorista" , description = "Método que acessa o método adicionar do service e cria o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - Pagamento criado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Parametro não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity novoMotorista(@Valid @RequestBody Motorista motorista,
                                        @RequestParam String cnpj) {

        motorista.setEmpresa(empresaService.acharPorId(cnpj));
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
            @ApiResponse(responseCode = "200" ,description = "OK - Motoristas listados com sucesso!"),
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
    @GetMapping(value = "/localizacao")
    @Operation(summary = "Lista a localização" , description = "Método que acessa o método calculoDeProximidade do service e lista a localização")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Localizaçãp listada com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Localização não localizada!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity localizacao(@RequestParam String cpfPassageiro,
                                      @RequestParam String cpfMotorista) {

        Passageiro passageiroAchado = passageiroService.acharPorId(cpfPassageiro);
        Motorista motoristaAchado = motoristaService.acharPorId(cpfMotorista);

        try{
            return new ResponseEntity<>( String.format("%.3f", motoristaService.calculoDeProximidade(passageiroAchado.getLatitude(),
                    passageiroAchado.getLongitude(), motoristaAchado.getLatitude(), motoristaAchado.getLongitude())) + " KM", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Algum do(s) Id(s) não existe!" , HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/listar/{cpf}")
    @Operation(summary = "Lista um motorista" , description = "Método que acessa o método acharPorId do service e lista um motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Motorista listado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity listarCorrida(@PathVariable String cpf) {
        try {
            return new ResponseEntity(motoristaService.acharPorId(cpf) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{cpf}")
    @Operation(summary = "Atualiza o motorista" , description = "Método que acessa o método atualizar do service e atualiza o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Motorista atualizado com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity atualizar(@PathVariable String cpf , @RequestBody Motorista motorista) {
        motoristaService.atualizar(cpf, motorista);
        try {
            return new ResponseEntity("Motorista " + cpf + "atualizado com sucesso!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{cpf}")
    @Operation(summary = "Remove motorista" , description = "Método que acessa o método remove do service e remove o motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Motorista removido com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - CPF do motorista não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity deletar(@PathVariable String cpf) {
        motoristaService.remove(cpf);
        try {
            return new ResponseEntity("Motorista " + cpf + " deletado!" , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse CPF não existe!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/aceitar/{cpf}")
    @Operation(summary = "Aceita corrida" , description = "Método que acessa o método aceitarCorrida do service e aceita a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corrida aceita!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity aceitarCorrida(@PathVariable String cpf , @RequestBody Pagamento pagamento,
                                         @RequestParam Integer corridaId) {
        pagamentoService.adicionar(pagamento);
        Corrida corrida = corridaService.acharPorId(corridaId);
        corrida.setPagamento(pagamento);

        try {
            corridaService.aceitarCorrida(corrida, cpf);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + cpf + " aceita!" , HttpStatus.OK);
    }

    @PutMapping(value = "/finalizar/{cpf}")
    @Operation(summary = "Finaliza corrida" , description = "Método que acessa o método finalizarCorrida do service e finaliza a corrida")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,description = "OK - Corrida finalizada!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Id da corrida não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity finalizarCorrida(@PathVariable String cpf , @RequestBody Corrida corrida) {
        try {
            corridaService.finalizarCorrida(corrida, cpf);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Esse id não existe!" , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Corrida " + cpf + " finalizada!" , HttpStatus.OK);
    }

    @PostMapping(value = "/photo/{cpf}")
    @Operation(summary = "Salva a foto de um motorista" , description = "Método que acessa o método saveFile do FileUpLoadUtil e salva a foto do motorista")
    @ApiResponses({
            @ApiResponse(responseCode = "201" ,description = "Created - foto salva com sucesso!"),
            @ApiResponse(responseCode = "404" ,description = "Erro - Cpf da foto não localizado!"),
            @ApiResponse(responseCode = "500" ,description = "Erro inesperado!")
    })
    public ResponseEntity salvarPhotoMotorista(Motorista motorista, @RequestParam("image")
    MultipartFile multipartFile) throws IOException {
        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            motorista.setPhoto(fileName);

            Motorista photoMotorista = motoristaRepository.save(motorista);

            String upLoaDir = "motorista-photos/" + photoMotorista.getCpf();

            FileUpLoadUtil.saveFile(upLoaDir, fileName, multipartFile);
            return new ResponseEntity("Sua Avaliação Foi Cadastrada Com SUcesso", HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity("Sua Avaliação não foi Cadastrada 😦 Tente novamente mais tarde",
                    HttpStatus.BAD_REQUEST);
        }
    }
}