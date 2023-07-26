package br.com.codewave.codewave.repositories;

import br.com.codewave.codewave.Models.PassageiroInternacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassageiroInternacionalRepository extends JpaRepository<PassageiroInternacional, String> {

    Optional<PassageiroInternacional> findByCpf(String cpf);

    PassageiroInternacional findByEmail(String email);

}
