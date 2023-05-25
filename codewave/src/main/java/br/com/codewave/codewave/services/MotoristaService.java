package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.codewave.codewave.util.Localizacao.haversine;

@Service
public class MotoristaService {
    @Autowired
    public MotoristaRepository motoristaRepository;

    public void adicionar(Motorista motoristaQueSeraSalvo) {
        motoristaRepository.save(motoristaQueSeraSalvo);
    }

    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll();
    }

    public Motorista acharPorId(String cpf) {
        Optional<Motorista> optionalDestino = motoristaRepository.findByCpf(cpf);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Motorista Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(String cpf, Motorista motorista){
        if (motoristaRepository.existsById((cpf))){
            motoristaRepository.save(motorista);
        }
    }

    public void remove(String cpf) {
        Motorista pesquisarMotorista = acharPorId(cpf);
        if (motoristaRepository.existsById((cpf))){
            motoristaRepository.deleteById((cpf));
        }
    }

    public double calculoDeProximidade(double latitudePassageiro, double longitudePassageiro,
                                       double latidudeMotorista, double longitudeMotorista){

        return haversine(latitudePassageiro, longitudePassageiro,
                latidudeMotorista, longitudeMotorista);
    }
}
