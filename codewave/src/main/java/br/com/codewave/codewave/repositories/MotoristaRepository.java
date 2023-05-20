package br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
}
