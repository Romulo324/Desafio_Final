package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Avaliacao;
import br.com.codewave.codewave.Models.Destino;
import br.com.codewave.codewave.repositories.AvaliacaoRepository;
import br.com.codewave.codewave.repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {
    @Autowired
    public AvaliacaoRepository avaliacaoRepository;

    public void adicionar(Avaliacao salvarAvaliacao) {
        avaliacaoRepository.save(salvarAvaliacao);
    }

    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao acharPorId(Integer id) {
        Optional<Avaliacao> optionalAvaliacao = avaliacaoRepository.findById(id);
        return optionalAvaliacao.get();
    }

    public void atualizar(Integer id, Avaliacao avaliacao){
        if (avaliacaoRepository.existsById(id)){
            avaliacaoRepository.save(avaliacao);
        }
    }

    public void remove(Integer id) {
        Avaliacao pesquisarAvaliacao = acharPorId(id);
        if (avaliacaoRepository.existsById(id)){
            avaliacaoRepository.deleteById(id);
        }
    }
}
