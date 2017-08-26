package br.com.roicamp.event.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roicamp.event.CampanhaNewEvent;
import br.com.roicamp.model.Campanha;
import br.com.roicamp.search.CampanhaSearchRepository;

@Component
public class CampanhaProrrogacaoVigenciaListener implements ApplicationListener<CampanhaNewEvent> {

	@Autowired
	private CampanhaSearchRepository dao;

	private static final int PRORROGACAO_CAMPANHA_DIAS = 1;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void onApplicationEvent(CampanhaNewEvent event) {
		List<Campanha> campanhasVigentes = dao.findCampanhasVigentes(event.getCampanha());
		this.prorrogarCampanha(campanhasVigentes, PRORROGACAO_CAMPANHA_DIAS);
		campanhasVigentes.add(event.getCampanha());
		this.prorrogarCampanhasIguais(campanhasVigentes, event.getCampanha().getId());
	}

	private void prorrogarCampanhasIguais(List<Campanha> campanhas, Long campanhaNovaId) {
		Collections.sort(campanhas);
		for (Campanha campanha : campanhas) {
			Campanha campanhaFimVigenciaIgual = this.getCampanhaFimVigenciaIgual(campanhas, campanha.getFimVigencia(), campanha.getId());
			if (campanhaFimVigenciaIgual != null && campanha.getId().equals(campanhaNovaId) == false) {
				this.prorrogarCampanha(campanha, PRORROGACAO_CAMPANHA_DIAS);
			}
		}
	}

	private Campanha getCampanhaFimVigenciaIgual(List<Campanha> campanhas, Date fimVigencia, Long campanhaNotId) {
		for (Campanha campanha : campanhas) {
			if (campanha.isTerminoVigenciaIgual(fimVigencia, campanhaNotId)) {
				return campanha;
			}
		}
		return null;
	}

	private void prorrogarCampanha(List<Campanha> campanhasVigentes, int days) {
		for (Campanha campanha : campanhasVigentes) {
			this.prorrogarCampanha(campanha, days);
		}
	}

	private void prorrogarCampanha(Campanha campanha, int days) {
		if (campanha != null) {
			campanha.setFimVigencia(new DateTime(campanha.getFimVigencia()).plusDays(days).toDate());
		}
	}

}
