package br.com.roicamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.UsuarioMessage;
import br.com.roicamp.business.UsuarioBusiness;
import br.com.roicamp.model.Usuario;

@Service
public class UsuarioServiceImpl {

	@Autowired
	private UsuarioBusiness business;

	@Autowired
	private CampanhaSearchServiceImpl campanhaSearchService;

	public BaseResult<UsuarioMessage> inativarUsuario(Long entityId) {
		try {
			business.inativar(entityId);
			return new BaseResult<UsuarioMessage>(true);
		} catch (Exception e) {
			return new BaseResult<UsuarioMessage>(e);
		}
	}

	public BaseResult<UsuarioMessage> putNewUsuario(UsuarioMessage message) {
		try {
			Usuario usuario = business.findOneUsuarioByEmail(message.getEmail());
			if (usuario == null) {
				message = business.putNew(message);
			} else {
				business.putUpdate(message);
				message.setId(usuario.getId());
			}
			return this.attachCampanhasAssociadas(message);
		} catch (Exception e) {
			return new BaseResult<UsuarioMessage>(e);
		}
	}

	public BaseResult<UsuarioMessage> putUpdateUsuario(UsuarioMessage message) {
		try {
			UsuarioMessage usuarioMessage = business.putUpdate(message);
			return this.attachCampanhasAssociadas(usuarioMessage);
		} catch (Exception e) {
			return new BaseResult<UsuarioMessage>(e);
		}
	}

	private BaseResult<UsuarioMessage> attachCampanhasAssociadas(UsuarioMessage message) throws Exception {
		try {
			campanhaSearchService.attachCampanhasAssociadas(message);
			return new BaseResult<UsuarioMessage>(true, message);
		} catch (Exception e) {
			return new BaseResult<UsuarioMessage>("Usuario atualizado, mas servi�o de campanha indispon�vel");
		}
	}
}
