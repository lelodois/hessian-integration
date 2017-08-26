package br.com.financecamp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.financecamp.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public Collection<Pessoa> findByNome(String nome);
}
