package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    Optional<Empresa> findByCnpj(String cnpj);
}
