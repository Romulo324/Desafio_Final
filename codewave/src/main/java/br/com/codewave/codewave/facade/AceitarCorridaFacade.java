package br.com.codewave.codewave.facade;

import br.com.codewave.codewave.Models.Pagamento;

public interface AceitarCorridaFacade {

    void aceitarCorrida(String cpf, Pagamento pagamento, Integer corridaId);
}
