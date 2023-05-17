package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Integer> {
}
