package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, String> {

    Optional<Motorista> findByCpf(String cpf);
}
