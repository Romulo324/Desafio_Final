package br.com.codewave.Models.src.main.java.br.com.codewave.Models.services;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Pagamento;
import br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    public PagamentoRepository pagamentoRepository;

    public void adicionar(Pagamento pagamentoQueSeraSalvo) {
        pagamentoRepository.save(pagamentoQueSeraSalvo);
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento acharPorId(Integer id) {
        Optional<Pagamento> optionalDestino = pagamentoRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Pagamento Não Encontrado");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Pagamento pagamento){
        if (pagamentoRepository.existsById(id)){
            pagamentoRepository.save(pagamento);
        }
    }

    public void remove(Integer id) {
        Pagamento pesquisarPagamento = acharPorId(id);
        if (pagamentoRepository.existsById(id)){
            pagamentoRepository.deleteById(id);
        }
    }
}
