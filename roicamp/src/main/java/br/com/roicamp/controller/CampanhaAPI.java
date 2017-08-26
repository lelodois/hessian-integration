package br.com.roicamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;
import br.com.roicamp.service.CampanhaSearchServiceImpl;
import br.com.roicamp.service.CampanhaServiceImpl;

@RestController
@RequestMapping("campanha")
public class CampanhaAPI {

	@Autowired
	private CampanhaServiceImpl service;

	@Autowired
	private CampanhaSearchServiceImpl searchService;

	@PostMapping(value = "/adicionar")
	public ResponseEntity<BaseResult<CampanhaMessage>> adicionar(
			@RequestBody(required = true) CampanhaMessage campanha) {
		return this.getResponseEntity(service.putNewCampanha(campanha));
	}

	@PutMapping(value = "/alterar")
	public ResponseEntity<BaseResult<CampanhaMessage>> alterar(@RequestBody(required = true) CampanhaMessage campanha) {
		return this.getResponseEntity(service.putUpdateCampanha(campanha));
	}

	@GetMapping(value = "/listar/programadas/{timeId}")
	public ResponseEntity<BaseResult<List<CampanhaMessage>>> listarProgramadas(
			@PathVariable(name = "timeId", required = true) Long timeId) {
		return this.getResponseEntityByList(searchService.listCampanhasProgramadas(timeId));
	}

	@DeleteMapping(value = "/inativar/{campanhaId}")
	public ResponseEntity<BaseResult<CampanhaMessage>> inativar(
			@PathVariable(name = "campanhaId", required = true) Long campanhaId) {
		return this.getResponseEntity(service.inativarCampanha(campanhaId));
	}

	@GetMapping(value = "/listar/alteradas")
	public ResponseEntity<BaseResult<List<CampanhaMessage>>> listarAlteradas() {
		return this.getResponseEntityByList(searchService.listAlteradas());
	}

	private ResponseEntity<BaseResult<CampanhaMessage>> getResponseEntity(BaseResult<CampanhaMessage> baseResult) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<BaseResult<CampanhaMessage>>(baseResult, httpHeaders, HttpStatus.OK);
	}

	private ResponseEntity<BaseResult<List<CampanhaMessage>>> getResponseEntityByList(
			BaseResult<List<CampanhaMessage>> baseResult) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<BaseResult<List<CampanhaMessage>>>(baseResult, httpHeaders, HttpStatus.OK);
	}

}