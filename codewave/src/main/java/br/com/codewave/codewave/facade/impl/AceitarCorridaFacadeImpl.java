package br.com.codewave.codewave.facade.impl;

import br.com.codewave.codewave.Models.Corrida;
import br.com.codewave.codewave.Models.Pagamento;
import br.com.codewave.codewave.facade.AceitarCorridaFacade;
import br.com.codewave.codewave.services.CorridaService;
import br.com.codewave.codewave.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarCorridaFacadeImpl implements AceitarCorridaFacade {

    @Autowired
    private CorridaService corridaService;
    @Autowired
    private PagamentoService pagamentoService;

    @Override
    public void aceitarCorrida(String cpf, Pagamento pagamento, Integer corridaId) {
        pagamentoService.adicionar(pagamento);
        Corrida corrida = corridaService.acharPorId(corridaId);
        corrida.setPagamento(pagamento);
        corridaService.aceitarCorrida(corrida, cpf);
    }
}
