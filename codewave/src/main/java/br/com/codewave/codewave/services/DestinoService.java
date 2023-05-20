package br.com.codewave.Models.src.main.java.br.com.codewave.Models.services;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Destino;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    @Autowired
    public DestinoRepository destinoRepository;

    public void adicionar(Destino destinoQueSeraSalvo) {
        destinoRepository.save(destinoQueSeraSalvo);
    }

    public List<Destino> listarTodos() {
        return destinoRepository.findAll();
    }

    public Destino acharPorId(Integer id) {
        Optional<Destino> optionalDestino = destinoRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Destino Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Destino destino){
        if (destinoRepository.existsById(id)){
            destinoRepository.save(destino);
        }
    }

    public void remove(Integer id) {
        Destino pesquisarDestino = acharPorId(id);
        if (destinoRepository.existsById(id)){
            destinoRepository.deleteById(id);
        }
    }
}
