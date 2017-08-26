package br.com.roicamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;
import br.com.basecamp.message.UsuarioMessage;
import br.com.basecamp.service.CampanhaServiceHessian;
import br.com.roicamp.business.CampanhaBusiness;
import br.com.roicamp.business.CampanhaUsuarioBusiness;
import br.com.roicamp.model.Campanha;

@Service
public class CampanhaSearchServiceImpl implements CampanhaServiceHessian {

	@Autowired
	private CampanhaBusiness campanhaBusiness;

	@Autowired
	private CampanhaUsuarioBusiness campanhaUsuarioBusiness;

	public BaseResult<List<CampanhaMessage>> listCampanhasProgramadas(Long timeId) {
		try {
			List<Campanha> campanha = campanhaBusiness.listCampanhasProgramadas(timeId);
			return new BaseResult<List<CampanhaMessage>>(true, this.convetToMessage(campanha));
		} catch (Exception e) {
			return new BaseResult<List<CampanhaMessage>>(e);
		}
	}

	public BaseResult<List<CampanhaMessage>> listAlteradas() {
		try {
			List<Campanha> campanhas = campanhaBusiness.listCampanhasAlteradas();
			return new BaseResult<List<CampanhaMessage>>(true, this.convetToMessage(campanhas));
		} catch (Exception e) {
			return new BaseResult<List<CampanhaMessage>>(e);
		}
	}

	public void attachCampanhasAssociadas(UsuarioMessage message) {
		Long usuario = message.getId();
		List<Campanha> naoParticipante = campanhaUsuarioBusiness.listCampanhasUsuarioParticipante(usuario);
		message.setCampanhasParticipantes(convetToMessage(naoParticipante));
	}

	private List<CampanhaMessage> convetToMessage(List<Campanha> campanhas) {
		List<CampanhaMessage> messages = Lists.newArrayList();
		for (Campanha campanha : campanhas) {
			messages.add(campanha.buildMessage());
		}
		return messages;
	}

}
