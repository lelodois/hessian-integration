package br.com.roicamp.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roicamp.event.CampanhaExchangeTeamEvent;
import br.com.roicamp.model.CampanhaUsuario;
import br.com.roicamp.search.CampanhaUsuarioSearchRepository;

@Component
public class CampanhaDesassociacaoTimeListener implements ApplicationListener<CampanhaExchangeTeamEvent> {

	@Autowired
	private CampanhaUsuarioSearchRepository dao;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void onApplicationEvent(CampanhaExchangeTeamEvent event) {
		for (CampanhaUsuario campanhaUsuario : dao.findUsuarioCampanhasByCampanha(event.getCampanha().getId())) {
			campanhaUsuario.getBaseModel().inativar();
		}
	}

}
