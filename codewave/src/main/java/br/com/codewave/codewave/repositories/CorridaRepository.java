package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CorridaRepository extends JpaRepository<Corrida, Integer> {

}
