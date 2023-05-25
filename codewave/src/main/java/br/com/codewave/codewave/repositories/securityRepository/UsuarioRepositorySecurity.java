package br.com.codewave.codewave.repositories.securityRepository;

import br.com.codewave.codewave.Models.UsuarioSecurityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorySecurity extends JpaRepository<UsuarioSecurityModel, Long> {

    public UsuarioSecurityModel findByUsername(String username);
}
