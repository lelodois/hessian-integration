package br.com.roicamp.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roicamp.business.CampanhaUsuarioBusiness;
import br.com.roicamp.event.UsuarioUpdateEvent;
import br.com.roicamp.model.Campanha;
import br.com.roicamp.model.CampanhaUsuario;
import br.com.roicamp.model.Usuario;

@Component
public class UsuarioAssociacaoCampanhaFaltanteListener implements ApplicationListener<UsuarioUpdateEvent> {

	@Autowired
	private CampanhaUsuarioBusiness business;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void onApplicationEvent(UsuarioUpdateEvent event) {
		Usuario usuario = event.getUsuario();
		for (Campanha campanha : business.listCampanhasProgramadasNaoAssociadas(usuario.getTimeId(), usuario.getId())) {
			business.merge(new CampanhaUsuario(campanha, usuario));
		}
	}
}
