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

    public void adicionar(Empresa empresaQueSeraSalvo) {
        empresaRepository.save(empresaQueSeraSalvo);
    }

    public List<Empresa> listarTodos() {
        return empresaRepository.findAll();
    }

    public Empresa acharPorId(Integer id) {
        Optional<Empresa> optionalDestino = empresaRepository.findById(id);
        if (optionalDestino.isEmpty()){
            throw new RuntimeException("Empresa Não Encontrada");
        }
        return optionalDestino.get();
    }

    public void atualizar(Integer id, Empresa empresa){
        if (empresaRepository.existsById(id)){
            empresaRepository.save(empresa);
        }
    }

    public void remove(Integer id) {
        Empresa pesquisarEmpresa = acharPorId(id);
        if (empresaRepository.existsById(id)){
            empresaRepository.deleteById(id);
        }
    }
}
