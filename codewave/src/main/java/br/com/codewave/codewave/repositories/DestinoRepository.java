package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DestinoRepository extends JpaRepository<Destino, Integer> {

}
