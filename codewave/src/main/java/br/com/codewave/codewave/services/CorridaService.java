package br.com.codewave.codewave.services;
import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.enums.CorridaEnum;
import br.com.codewave.codewave.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorridaService {
    @Autowired
    public CorridaRepository corridaRepository;

    @Autowired
    private MotoristaService motoristaService;

    // Método que adicona uma corrida
    public void adicionar(Corrida corridaQueSeraSalvo) {
        corridaRepository.save(corridaQueSeraSalvo);
    }

    // Método que lista todas as corridas adicionadas
    public List<Corrida> listarTodos() {
        return corridaRepository.findAll();
    }

    // Método que procura uma corrida pelo id e lista
    public Corrida acharPorId(Integer id) {
        Optional<Corrida> optionalCorrida = corridaRepository.findById(id);
        if (optionalCorrida.isEmpty()){
            throw new RuntimeException("Corrida Não Encontrada");
        }
        return optionalCorrida.get();
    }

    // Método que atualiza uma corrida
    public void atualizar(Integer id, Corrida corrida){
        if (corridaRepository.existsById(id)){
            corridaRepository.save(corrida);
        }
    }

    // Método que remove uma corrida
    public void remove(Integer id) {
        Corrida pesquisarCorrrida = acharPorId(id);
        if (corridaRepository.existsById(id)){
            corridaRepository.deleteById(id);
        }
    }

    // Método que aceita uma corrida puxando o cpf do motorista e mudando o status
    public void aceitarCorrida(Corrida corrida, String cpf) {
        corrida.setMotorista(motoristaService.acharPorId(cpf));
        corrida.setStatus(CorridaEnum.EM_ANDAMENTO);
        corridaRepository.save(corrida);
    }

    // Método que finaliza uma corrida usando o cpf do motorista e muda o status
    public void finalizarCorrida(Corrida corrida, String cpf) {
        corrida.setMotorista(motoristaService.acharPorId(cpf));
        corrida.setStatus(CorridaEnum.FINALIZADO);
        corridaRepository.save(corrida);
    }
}