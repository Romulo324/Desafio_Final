package br.com.codewave.codewave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
