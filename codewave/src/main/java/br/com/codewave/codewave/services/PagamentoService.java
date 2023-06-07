package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Pagamento;
import br.com.codewave.codewave.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    public PagamentoRepository pagamentoRepository;

    // Método que adiciona um pagamento
    public void adicionar(Pagamento pagamentoQueSeraSalvo) {
        double resultado = pagamentoQueSeraSalvo.getValorFinal().doubleValue() * pagamentoQueSeraSalvo.getPorcentagemDoMotorista() / 100;
        pagamentoQueSeraSalvo.setResultadoPorcentagemDoMotorista(BigDecimal.valueOf(resultado).setScale(2, RoundingMode.HALF_UP));
        pagamentoRepository.save(pagamentoQueSeraSalvo);
    }


    // Método que lista todos os pagamentos adicionados
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    // Método que lista um pagamento pelo id
    public Pagamento acharPorId(Integer id) {
        Optional<Pagamento> optionalDestino = pagamentoRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Pagamento Não Encontrado");
        }
        return optionalDestino.get();
    }

    // Método que atualiza um pagamento pelo id
    public void atualizar(Integer id, Pagamento pagamento){
        if (pagamentoRepository.existsById(id)){
            pagamentoRepository.save(pagamento);
        }
    }

    //Método que remove um pagamento pelo id
    public void remove(Integer id) {
        Pagamento pesquisarPagamento = acharPorId(id);
        if (pagamentoRepository.existsById(id)){
            pagamentoRepository.deleteById(id);
        }
    }
}
