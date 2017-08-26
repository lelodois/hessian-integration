package br.com.roicamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;
import br.com.roicamp.business.CampanhaBusiness;

@Service
public class CampanhaServiceImpl {

	@Autowired
	private CampanhaBusiness business;

	public BaseResult<CampanhaMessage> putUpdateCampanha(CampanhaMessage message) {
		try {
			return new BaseResult<CampanhaMessage>(true, business.putUpdateCampanha(message));
		} catch (Exception e) {
			return new BaseResult<CampanhaMessage>(e);
		}
	}

	public BaseResult<CampanhaMessage> putNewCampanha(CampanhaMessage message) {
		try {
			return new BaseResult<CampanhaMessage>(true, business.putNewCampanha(message));
		} catch (Exception e) {
			return new BaseResult<CampanhaMessage>(e);
		}
	}

	public BaseResult<CampanhaMessage> inativarCampanha(Long entityId) {
		try {
			business.inativar(entityId);
			return new BaseResult<CampanhaMessage>(true);
		} catch (Exception e) {
			return new BaseResult<CampanhaMessage>(e);
		}
	}

}
