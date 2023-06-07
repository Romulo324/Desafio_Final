package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Destino;
import br.com.codewave.codewave.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    @Autowired
    public DestinoRepository destinoRepository;

    // Método que adiciona um destino
    public void adicionar(Destino destinoQueSeraSalvo) {
        destinoRepository.save(destinoQueSeraSalvo);
    }


    // Método que lista todos os destinos adicionados
    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }

    // Método que procura um destino pelo id e lista
    public Destino acharPorId(Integer id) {
        Optional<Destino> optionalDestino = destinoRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Destino Não Encontrado");
        }
        return optionalDestino.get();
    }

    // Método que atualiza um destino pelo id
    public void atualizar(Integer id, Destino destino){
        if (destinoRepository.existsById(id)){
            destinoRepository.save(destino);
        }
    }

    // Método que remove um destino pelo id
    public void remove(Integer id) {
        Destino pesquisarDestino = acharPorId(id);
        if (destinoRepository.existsById(id)){
            destinoRepository.deleteById(id);
        }
    }
}
