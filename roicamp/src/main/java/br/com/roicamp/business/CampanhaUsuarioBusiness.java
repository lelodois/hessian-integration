package br.com.roicamp.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roicamp.model.Campanha;
import br.com.roicamp.model.CampanhaUsuario;
import br.com.roicamp.repository.CampanhaUsuarioBaseRepository;
import br.com.roicamp.search.CampanhaUsuarioSearchRepository;
import ch.lambdaj.Lambda;

@Service
public class CampanhaUsuarioBusiness {

	@Autowired
	private CampanhaUsuarioSearchRepository searchDao;

	@Autowired
	private CampanhaUsuarioBaseRepository baseDao;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Campanha> listCampanhasProgramadasNaoAssociadas(Long timeId, Long usuarioId) {
		return searchDao.findCampanhasProgramadasNaoAssociadas(timeId, usuarioId);
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Campanha> listCampanhasUsuarioParticipante(Long usuarioId) {
		return Lambda.extract(searchDao.findUsuarioCampanhasByUsuario(usuarioId),
				Lambda.on(CampanhaUsuario.class).getCampanha());
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public CampanhaUsuario merge(CampanhaUsuario campanhaUsuario) {
		return baseDao.save(campanhaUsuario);
	}

}
