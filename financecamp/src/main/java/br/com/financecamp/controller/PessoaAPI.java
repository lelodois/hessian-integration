package br.com.financecamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;
import br.com.basecamp.service.CampanhaServiceHessian;
import br.com.financecamp.model.Pessoa;
import br.com.financecamp.repository.PessoaRepository;

@RestController
public class PessoaAPI {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private CampanhaServiceHessian campanhaService;

	@GetMapping(value = "/entrar/{nome}")
	public Pessoa entrou(@PathVariable String nome) {
		return repository.save(new Pessoa(nome));
	}

	@GetMapping(value = "/campanha/{nome}")
	public BaseResult<List<CampanhaMessage>> campanhas() {
		return campanhaService.listAlteradas();
	}
}
