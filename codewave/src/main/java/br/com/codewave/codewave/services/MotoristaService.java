package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.codewave.codewave.util.Localizacao.haversine;

@Service
public class MotoristaService {
    @Autowired
    public MotoristaRepository motoristaRepository;

    // Método que adiciona um motorista
    public void adicionar(Motorista motoristaQueSeraSalvo) {
        motoristaRepository.save(motoristaQueSeraSalvo);
    }

    // Método que lista todos os motoristas cadastrados
    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll();
    }

    // Método que procura um motorista pelo cpf e lista
    public Motorista acharPorId(String cpf) {
        Optional<Motorista> optionalDestino = motoristaRepository.findByCpf(cpf);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Motorista Não Encontrado");
        }
        return optionalDestino.get();
    }

    // Método que atualiza um motorista pelo cpf
    public void atualizar(String cpf, Motorista motorista){
        if (motoristaRepository.existsById((cpf))){
            motoristaRepository.save(motorista);
        }
    }

    // Método que remove um motorista pelo cpf
    public void remove(String cpf) {
        Motorista pesquisarMotorista = acharPorId(cpf);
        if (motoristaRepository.existsById((cpf))){
            motoristaRepository.deleteById((cpf));
        }
    }

    // Método que calcula a latitude e a longitude do motorista e retorna o método haversine da classe localizacao
    public double calculoDeProximidade(double latitudePassageiro, double longitudePassageiro,
                                       double latidudeMotorista, double longitudeMotorista){

        return haversine(latitudePassageiro, longitudePassageiro,
                latidudeMotorista, longitudeMotorista);
    }
    public Motorista buscarPorEmail(String email) {
        return motoristaRepository.findByEmail(email);
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
