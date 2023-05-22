package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ParadaRepository extends JpaRepository<Parada, Integer> {
}
