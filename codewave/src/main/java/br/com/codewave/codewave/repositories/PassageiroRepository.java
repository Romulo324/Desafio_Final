package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, String> {
    Optional<Passageiro> findByCpf(String cpf);
    Passageiro findByEmail(String email);
}
