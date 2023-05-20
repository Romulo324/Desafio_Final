package br.com.codewave.Models.src.main.java.br.com.codewave.Models.services;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Corrida;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CorridaService {
    @Autowired
    public CorridaRepository corridaRepository;

    public void adicionar(Corrida corridaQueSeraSalvo) {
        corridaRepository.save(corridaQueSeraSalvo);
    }

    public List<Corrida> listarTodos() {
        return corridaRepository.findAll();
    }

    public Corrida acharPorId(Integer id) {
        Optional<Corrida> optionalCorrida = corridaRepository.findById(id);
        if (optionalCorrida.isEmpty()){
            throw new RuntimeException("Corrida Não Encontrada");
        }
        return optionalCorrida.get();
    }

    public void atualizar(Integer id, Corrida corrida){
        if (corridaRepository.existsById(id)){
            corridaRepository.save(corrida);
        }
    }

    public void remove(Integer id) {
        Corrida pesquisarCorrrida = acharPorId(id);
        if (corridaRepository.existsById(id)){
            corridaRepository.deleteById(id);
        }
    }
}
