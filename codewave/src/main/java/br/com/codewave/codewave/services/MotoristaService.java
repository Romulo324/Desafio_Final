package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Motorista;
import br.com.codewave.codewave.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MotoristaService {
    @Autowired
    public MotoristaRepository motoristaRepository;

    public void adicionar(Motorista motoristaQueSeraSalvo) {
        motoristaRepository.save(motoristaQueSeraSalvo);
    }

    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll();
    }

    public Motorista acharPorId(Integer id) {
        Optional<Motorista> optionalDestino = motoristaRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Motorista Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Motorista motorista){
        if (motoristaRepository.existsById(id)){
            motoristaRepository.save(motorista);
        }
    }

    public void remove(Integer id) {
        Motorista pesquisarMotorista = acharPorId(id);
        if (motoristaRepository.existsById(id)){
            motoristaRepository.deleteById(id);
        }
    }
    
}
