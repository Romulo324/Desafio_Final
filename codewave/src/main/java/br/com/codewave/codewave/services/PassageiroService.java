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

    public Passageiro acharPorId(String cpf) {
        Optional<Passageiro> optionalDestino = passageiroRepository.findByCpf(cpf);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Passageiro Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(String cpf, Passageiro passageiro){
        if (passageiroRepository.existsById(cpf)){
            passageiroRepository.save(passageiro);
        }
    }

    public void remove(String cpf) {
        Passageiro pesquisarDestino = acharPorId(cpf);
        if (passageiroRepository.existsById((cpf))){
            passageiroRepository.deleteById((cpf));
        }
    }

    public double calculoDeProximidade(double latitudePassageiro, double longitudePassageiro,
                                       double latidudeMotorista, double longitudeMotorista){

        return haversine(latitudePassageiro, longitudePassageiro,
                latidudeMotorista, longitudeMotorista);
    }
}
