package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
}
