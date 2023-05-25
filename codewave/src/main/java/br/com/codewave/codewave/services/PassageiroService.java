package br.com.codewave.codewave.services;


import br.com.codewave.codewave.Models.Passageiro;
import br.com.codewave.codewave.repositories.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.codewave.codewave.util.Localizacao.haversine;

@Service
public class PassageiroService {
    @Autowired
    public PassageiroRepository passageiroRepository;

    public void adicionar(Passageiro passageiroQueSeraSalvo) {
        passageiroRepository.save(passageiroQueSeraSalvo);
    }

    public List<Passageiro> listarTodos() {
        return passageiroRepository.findAll();
    }

    public Passageiro acharPorId(Integer id) {
        Optional<Passageiro> optionalDestino = passageiroRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Passageiro Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Passageiro passageiro){
        if (passageiroRepository.existsById(id)){
            passageiroRepository.save(passageiro);
        }
    }

    public void remove(Integer id) {
        Passageiro pesquisarDestino = acharPorId(id);
        if (passageiroRepository.existsById(id)){
            passageiroRepository.deleteById(id);
        }
    }

    public double calculoDeProximidade(double latitudePassageiro, double longitudePassageiro,
                                       double latidudeMotorista, double longitudeMotorista){

        return haversine(latitudePassageiro, longitudePassageiro,
                latidudeMotorista, longitudeMotorista);
    }

    public Passageiro buscarPorEmail(String email) {
        return passageiroRepository.findByEmail(email);
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