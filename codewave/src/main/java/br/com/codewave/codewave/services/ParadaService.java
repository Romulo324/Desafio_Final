package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Parada;
import br.com.codewave.codewave.repositories.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {
    @Autowired
    public ParadaRepository paradaRepository;

    // Método que adiciona uma parada
    public void adicionar(Parada paradaQueSeraSalvo) {
        paradaRepository.save(paradaQueSeraSalvo);
    }

    // Método que lista todas as paradas que foram adicionadas
    public List<Parada> listarTodos() {
        return paradaRepository.findAll();
    }

    // Método que procura uma parada pelo id e lista
    public Parada acharPorId(Integer id) {
        Optional<Parada> optionalParada = paradaRepository.findById(id);
        if (optionalParada.isEmpty()){
            throw new RuntimeException("parada Não Encontrada");
        }
        return optionalParada.get();
    }

    // Método que atualiza uma parada pelo id
    public void atualizar(Integer id, Parada parada){
        if (paradaRepository.existsById(id)){
            paradaRepository.save(parada);
        }
    }

    // Método que remove uma parada pelo id
    public void remove(Integer id) {
        Parada pesquisarParada = acharPorId(id);
        if (paradaRepository.existsById(id)){
            paradaRepository.deleteById(id);
        }
    }
}
