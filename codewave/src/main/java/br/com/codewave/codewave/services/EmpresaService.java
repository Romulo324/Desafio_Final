package br.com.codewave.codewave.services;

import br.com.codewave.codewave.Models.Empresa;
import br.com.codewave.codewave.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    public EmpresaRepository empresaRepository;

    // Método que adiciona uma empresa
    public void adicionar(Empresa empresaQueSeraSalvo) {
        empresaRepository.save(empresaQueSeraSalvo);
    }

    // Método que lista todas as empresas adicionadas
    public List<Empresa> listarTodos() {
        return empresaRepository.findAll();
    }

    // Método que lista uma empresa pelo cnpj
    public Empresa acharPorId(String cnpj) {
        Optional<Empresa> optionalDestino = empresaRepository.findByCnpj(cnpj);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Empresa Não Encontrada");
        }
        return optionalDestino.get();
    }

    // Método que atualiza uma empresa pelo cnpj
    public void atualizar(String cnpj, Empresa empresa){
        if (empresaRepository.existsById((cnpj))){
            empresaRepository.save(empresa);
        }
    }

    // Método que remove uma empresa pelo cnpj
    public void remove(String cnpj) {
        Empresa pesquisarEmpresa = acharPorId(cnpj);
        if (empresaRepository.existsById((cnpj))){
            empresaRepository.deleteById((cnpj));
        }
    }
}
