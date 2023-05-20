package br.com.codewave.Models.src.main.java.br.com.codewave.Models.repositories;

import br.com.codewave.Models.src.main.java.br.com.codewave.Models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
