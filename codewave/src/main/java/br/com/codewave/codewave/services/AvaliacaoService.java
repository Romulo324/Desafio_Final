package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Avaliacao;
import br.com.codewave.codewave.Models.AvaliacaoEnum;
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

    public void adicionar(Avaliacao avaliacao) {
        Avaliacao model = new Avaliacao();
        switch (avaliacao.getAvaliacao()){
            case PÉSSIMO:
                model.setAvaliacao(AvaliacaoEnum.PÉSSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case MUITO_BOM:
                model.setAvaliacao(AvaliacaoEnum.MUITO_BOM);
                break;

            case ÓTIMO:
                model.setAvaliacao(AvaliacaoEnum.ÓTIMO);
                break;

        }

        avaliacaoRepository.save(model);
    }

    public void listar(Avaliacao avaliacao) {
        Avaliacao model = new Avaliacao();
        switch (avaliacao.getAvaliacao()){
            case PÉSSIMO:
                model.setAvaliacao(AvaliacaoEnum.PÉSSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case MUITO_BOM:
                model.setAvaliacao(AvaliacaoEnum.MUITO_BOM);
                break;

            case ÓTIMO:
                model.setAvaliacao(AvaliacaoEnum.ÓTIMO);
                break;

        }

        avaliacaoRepository.save(model);
    }

    public void deletar(Avaliacao avaliacao) {
        Avaliacao model = new Avaliacao();
        switch (avaliacao.getAvaliacao()){
            case PÉSSIMO:
                model.setAvaliacao(AvaliacaoEnum.PÉSSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case MUITO_BOM:
                model.setAvaliacao(AvaliacaoEnum.MUITO_BOM);
                break;

            case ÓTIMO:
                model.setAvaliacao(AvaliacaoEnum.ÓTIMO);
                break;

        }

        avaliacaoRepository.save(model);
    }

    public void atualizar(Avaliacao avaliacao) {
        Avaliacao model = new Avaliacao();
        switch (avaliacao.getAvaliacao()){
            case PÉSSIMO:
                model.setAvaliacao(AvaliacaoEnum.PÉSSIMO);

                break;

            case RUIM:
                model.setAvaliacao(AvaliacaoEnum.RUIM);
                break;

            case BOM:
                model.setAvaliacao(AvaliacaoEnum.BOM);
                break;

            case MUITO_BOM:
                model.setAvaliacao(AvaliacaoEnum.MUITO_BOM);
                break;

            case ÓTIMO:
                model.setAvaliacao(AvaliacaoEnum.ÓTIMO);
                break;

        }

        avaliacaoRepository.save(model);
    }

}
