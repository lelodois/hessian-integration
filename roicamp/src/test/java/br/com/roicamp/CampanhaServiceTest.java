package br.com.roicamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.CampanhaMessage;
import br.com.roicamp.model.BaseModel;
import br.com.roicamp.model.Campanha;

@RunWith(MockitoJUnitRunner.class)
public class CampanhaServiceTest extends BaseServiceMockTest {

	@Test
	public void inativarCampanhaSuccessTest() {
		Long entityId = 154l;
		Campanha mocked = this.getMockCampanha(entityId, 999l);
		Mockito.when(campanhaBaseDao.findOne(entityId)).thenReturn(mocked);
		Assert.assertTrue(campanhaService.inativarCampanha(entityId).isSuccess());
		Assert.assertNotNull(mocked.getBaseModel().getDataInativacao());
	}

	@Test
	public void inativarCampanhaErrorTest() {
		Assert.assertFalse(campanhaService.inativarCampanha(null).isSuccess());
	}

	@Test
	public void putNewCampanhaSuccessTest() {
		CampanhaMessage message = getMockCampanha(null, 999l).buildMessage();
		Mockito.when(campanhaBaseDao.save(Mockito.any(Campanha.class))).thenReturn(getMockCampanha(1l, 999l));
		BaseResult<CampanhaMessage> putNewCampanha = campanhaService.putNewCampanha(message);
		Assert.assertTrue(putNewCampanha.isSuccess());
		Assert.assertNotNull(getCampanhaMessage(putNewCampanha).getId());
	}

	@Test
	public void putNewCampanhaErrorObrigatorioTest() {
		CampanhaMessage message = getMockCampanha(null, 999l).buildMessage();
		message.setFimVigencia(null);
		BaseResult<CampanhaMessage> putNewCampanha = campanhaService.putNewCampanha(message);
		Assert.assertFalse(putNewCampanha.isSuccess());
	}

	@Test
	public void putUpdateCampanhaSuccessTest() {
		long entityId = 1l;
		Campanha saved = this.getMockCampanha(entityId, 999l);
		Mockito.when(campanhaBaseDao.findOne(entityId)).thenReturn(saved);
		Mockito.when(campanhaBaseDao.save(Mockito.any(Campanha.class))).thenReturn(getMockCampanha(1l, 999l));

		CampanhaMessage newCampanha = getMockCampanha(entityId, 999l).buildMessage();
		newCampanha.setNome("novo@novo.com.br");
		BaseResult<CampanhaMessage> putCampanha = campanhaService.putNewCampanha(newCampanha);
		Assert.assertTrue(putCampanha.isSuccess());
	}

	private CampanhaMessage getCampanhaMessage(BaseResult<CampanhaMessage> result) {
		return (CampanhaMessage) result.getResult();
	}

	private Campanha getMockCampanha(Long entityId, Long timeId) {
		Campanha campanha = new Campanha();
		campanha.setId(entityId);
		campanha.setInicioVigencia(DateTime.now().toDate());
		campanha.setFimVigencia(DateTime.now().plusDays(5).toDate());
		campanha.setNome("Campanha: " + entityId);
		campanha.setTimeId(timeId);
		campanha.setBaseModel(BaseModel.neww());
		return campanha;
	}

}
