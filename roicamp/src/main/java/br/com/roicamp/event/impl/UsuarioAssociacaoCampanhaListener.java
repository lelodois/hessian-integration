package br.com.roicamp.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roicamp.business.CampanhaBusiness;
import br.com.roicamp.event.UsuarioNewEvent;
import br.com.roicamp.model.Campanha;
import br.com.roicamp.model.CampanhaUsuario;
import br.com.roicamp.model.Usuario;
import br.com.roicamp.repository.CampanhaUsuarioBaseRepository;

@Component
public class UsuarioAssociacaoCampanhaListener implements ApplicationListener<UsuarioNewEvent> {

	@Autowired
	private CampanhaBusiness campanhaBusiness;

	@Autowired
	private CampanhaUsuarioBaseRepository dao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void onApplicationEvent(UsuarioNewEvent event) {
		Usuario usuario = event.getUsuario();
		for (Campanha campanha : campanhaBusiness.listCampanhasProgramadas(usuario.getTimeId())) {
			dao.save(new CampanhaUsuario(campanha, usuario));
		}
	}
}
