package br.com.basecamp.service;

import java.util.List;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;

public interface CampanhaServiceHessian {

	public BaseResult<List<CampanhaMessage>> listAlteradas();

}