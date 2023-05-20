package br.com.codewave.Models.src.main.java.br.com.codewave.Models.services;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Corrida;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Motorista;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.CorridaRepository;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {
    @Autowired
    public MotoristaRepository motoristaRepository;

    @Autowired
    public CorridaRepository corridaRepository;

    public void adicionar(Motorista motoristaQueSeraSalvo) {
        motoristaRepository.save(motoristaQueSeraSalvo);
    }

    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll();
    }

    public Motorista acharPorId(Integer id) {
        Optional<Motorista> optionalDestino = motoristaRepository.findById(id);
        if (optionalDestino.isEmpty()) {
            throw new RuntimeException("Motorista Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Motorista motorista) {
        if (motoristaRepository.existsById(id)) {
            motoristaRepository.save(motorista);
        }
    }

    public void remove(Integer id) {
        Motorista pesquisarMotorista = acharPorId(id);
        if (motoristaRepository.existsById(id)) {
            motoristaRepository.deleteById(id);
        }
    }

    public void receberCorrida(Integer id) {
        Optional<Corrida> corrida = corridaRepository.findById(id);
            if (corrida.isPresent()){
                 corrida.get();
            }
    }

}