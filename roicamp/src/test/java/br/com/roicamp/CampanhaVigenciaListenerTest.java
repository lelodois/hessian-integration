package br.com.roicamp;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import br.com.roicamp.comum.DateUtils;
import br.com.roicamp.event.CampanhaNewEvent;
import br.com.roicamp.event.impl.CampanhaProrrogacaoVigenciaListener;
import br.com.roicamp.exception.DateInvalidException;
import br.com.roicamp.model.BaseModel;
import br.com.roicamp.model.Campanha;
import br.com.roicamp.search.CampanhaSearchRepository;

@RunWith(MockitoJUnitRunner.class)
public class CampanhaVigenciaListenerTest {

	@Mock
	private CampanhaSearchRepository dao;

	@InjectMocks
	private CampanhaProrrogacaoVigenciaListener listener;

	@Test
	public void deveAdicionarDiaTerminoTest() throws DateInvalidException {
		Campanha novaCampanha = this.createCampanhaMock(100l, "05/08/2017");
		List<Campanha> campanhasVigentes = Lists.newArrayList();
		campanhasVigentes.add(this.createCampanhaMock(101l, "10/08/2017"));
		campanhasVigentes.add(this.createCampanhaMock(102l, "12/08/2017"));

		Mockito.when(dao.findCampanhasVigentes(novaCampanha)).thenReturn(campanhasVigentes);
		listener.onApplicationEvent(new CampanhaNewEvent(this, novaCampanha));

		campanhasVigentes.add(novaCampanha);

		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 100l, "05/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 101l, "11/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 102l, "13/08/2017");
	}

	@Test
	public void deveAdicionarDiaTerminoEReorganizarTerminoExampleTest() throws DateInvalidException {
		Campanha novaCampanha = this.createCampanhaMock(3l, "03/10/2017");
		List<Campanha> campanhasVigentes = Lists.newArrayList();
		campanhasVigentes.add(this.createCampanhaMock(1l, "03/10/2017"));
		campanhasVigentes.add(this.createCampanhaMock(2l, "02/10/2017"));

		Mockito.when(dao.findCampanhasVigentes(novaCampanha)).thenReturn(campanhasVigentes);
		listener.onApplicationEvent(new CampanhaNewEvent(this, novaCampanha));

		campanhasVigentes.add(novaCampanha);

		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 1l, "05/10/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 2l, "04/10/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 3l, "03/10/2017");
	}

	@Test
	public void nadaAFazerTest() throws DateInvalidException {
		Campanha novaCampanha = this.createCampanhaMock(3l, "03/10/2017");
		List<Campanha> campanhasVigentes = Lists.newArrayList();

		Mockito.when(dao.findCampanhasVigentes(novaCampanha)).thenReturn(campanhasVigentes);
		listener.onApplicationEvent(new CampanhaNewEvent(this, novaCampanha));

		assertDataCampanha("03/10/2017", novaCampanha, "Nada deve ser alterado");
	}

	@Test
	public void deveAdicionarDiaTerminoEReorganizarTerminoTest() throws DateInvalidException {
		Campanha novaCampanha = this.createCampanhaMock(100l, "10/08/2017");
		List<Campanha> campanhasVigentes = Lists.newArrayList();
		campanhasVigentes.add(this.createCampanhaMock(101l, "10/08/2017"));
		campanhasVigentes.add(this.createCampanhaMock(102l, "11/08/2017"));
		campanhasVigentes.add(this.createCampanhaMock(103l, "09/08/2017"));
		campanhasVigentes.add(this.createCampanhaMock(104l, "15/08/2017"));
		campanhasVigentes.add(this.createCampanhaMock(105l, "14/08/2017"));

		Mockito.when(dao.findCampanhasVigentes(novaCampanha)).thenReturn(campanhasVigentes);
		listener.onApplicationEvent(new CampanhaNewEvent(this, novaCampanha));

		campanhasVigentes.add(novaCampanha);

		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 100l, "10/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 103l, "11/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 101l, "12/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 102l, "13/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 105l, "15/08/2017");
		this.campanhaDeveTerTerminoIgual(campanhasVigentes, 104l, "16/08/2017");
	}

	private Campanha createCampanhaMock(Long id, String fimVigencia) throws DateInvalidException {
		Campanha campanha = new Campanha();
		campanha.setId(id);
		campanha.setFimVigencia(DateUtils.toDateTruncate(fimVigencia));
		campanha.setBaseModel(BaseModel.neww());
		return campanha;
	}

	private void campanhaDeveTerTerminoIgual(List<Campanha> vigentes, Long id, String terminoEsperado) throws DateInvalidException {
		Assert.assertFalse(vigentes.isEmpty());
		boolean campanhaLocalizada = false;
		for (Campanha campanha : vigentes) {
			if (campanha.getId().equals(id)) {
				campanhaLocalizada = true;
				String errorMessage = id + " esp " + terminoEsperado + " retornado: " + DateUtils.toStringDateTruncate(campanha.getFimVigencia());
				this.assertDataCampanha(terminoEsperado, campanha, errorMessage);
			}
		}
		Assert.assertTrue(campanhaLocalizada);
	}

	private void assertDataCampanha(String terminoEsperado, Campanha campanha, String errorMessage) throws DateInvalidException {
		Assert.assertTrue(errorMessage, DateUtils.isSameDay(DateUtils.toDateTruncate(terminoEsperado), campanha.getFimVigencia()));
	}

}
