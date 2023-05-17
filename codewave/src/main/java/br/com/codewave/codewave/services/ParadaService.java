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

    public void adicionar(Parada paradaQueSeraSalvo) {
        paradaRepository.save(paradaQueSeraSalvo);
    }

    public List<Parada> listarTodos() {
        return paradaRepository.findAll();
    }

    public Parada acharPorId(Integer id) {
        Optional<Parada> optionalParada = paradaRepository.findById(id);
        if (optionalParada.isEmpty()){
            throw new RuntimeException("parada Não Encontrada");
        }
        return optionalParada.get();
    }

    public void atualizar(Integer id, Parada parada){
        if (paradaRepository.existsById(id)){
            paradaRepository.save(parada);
        }
    }

    public void remove(Integer id) {
        Parada pesquisarParada = acharPorId(id);
        if (paradaRepository.existsById(id)){
            paradaRepository.deleteById(id);
        }
    }
}
