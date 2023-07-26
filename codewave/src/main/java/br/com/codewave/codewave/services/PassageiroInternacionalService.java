package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.Models.PassageiroInternacional;
import br.com.codewave.codewave.repositories.PassageiroInternacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.codewave.codewave.util.Localizacao.haversine;

@Service
public class PassageiroInternacionalService {

    @Autowired
    private PassageiroInternacionalRepository passageiroInternacionalRepository;

    // Método que adiciona um passageiro
    public void adicionar(PassageiroInternacional passageiroQueSeraSalvo) {
        passageiroInternacionalRepository.save(passageiroQueSeraSalvo);
    }

    // Método que lista todos os passageiros que foram adicionados
    public List<PassageiroInternacional> listarTodos() {
        return passageiroInternacionalRepository.findAll();
    }

    // Método que procura um passageiro pelo cpf e lista
    public PassageiroInternacional acharPorId(String cpf) {
        Optional<PassageiroInternacional> optionalDestino = passageiroInternacionalRepository.findByCpf(cpf);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Passageiro Não Encontrado");
        }
        return optionalDestino.get();
    }

    // Método que atualiza um passageiro pelo cpf
    public void atualizar(String cpf, PassageiroInternacional passageiroInternacional){
        if (passageiroInternacionalRepository.existsById(cpf)){
            passageiroInternacionalRepository.save(passageiroInternacional);
        }
    }

    // Método que remove um passageiro pelo cpf
    public void remove(String cpf) {
        PassageiroInternacional pesquisarDestino = acharPorId(cpf);
        if (passageiroInternacionalRepository.existsById((cpf))){
            passageiroInternacionalRepository.deleteById((cpf));
        }
    }

    // Método que calcula a latitude e a longitude do passageiro e retorna o método haversine da classe localizacao
    public double calculoDeProximidade(double latitudePassageiro, double longitudePassageiro,
                                       double latidudeMotorista, double longitudeMotorista){

        return haversine(latitudePassageiro, longitudePassageiro,
                latidudeMotorista, longitudeMotorista);
    }
    public PassageiroInternacional buscarPorEmail(String email) {
        return passageiroInternacionalRepository.findByEmail(email);
    }
    public String gerarNovaSenha() {
        int length = 12;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            newPassword.append(characters.charAt(randomIndex));
        }

        return newPassword.toString();
    }

}
