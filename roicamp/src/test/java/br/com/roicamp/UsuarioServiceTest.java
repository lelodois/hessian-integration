package br.com.roicamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.basecamp.message.BaseResult;
import br.com.basecamp.message.UsuarioMessage;
import br.com.roicamp.model.BaseModel;
import br.com.roicamp.model.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest extends BaseServiceMockTest {

	@Test
	public void inativarUsuarioSuccessTest() {
		Long entityId = 154l;
		Usuario mocked = this.getMockUsuario(entityId, 999l);
		Mockito.when(usuarioBaseDao.findOne(entityId)).thenReturn(mocked);
		Assert.assertTrue(usuarioService.inativarUsuario(entityId).isSuccess());
		Assert.assertNotNull(mocked.getBaseModel().getDataInativacao());
	}

	@Test
	public void inativarUsuarioErrorTest() {
		Assert.assertFalse(usuarioService.inativarUsuario(null).isSuccess());
	}

	@Test
	public void putNewUsuarioSuccessTest() {
		UsuarioMessage message = getMockUsuario(null, 999l).buildMessage();
		Mockito.when(usuarioBaseDao.save(Mockito.any(Usuario.class))).thenReturn(getMockUsuario(1l, 999l));
		BaseResult<UsuarioMessage> putNewUsuario = usuarioService.putNewUsuario(message);
		Assert.assertTrue(putNewUsuario.isSuccess());
		Assert.assertNotNull(getUsuarioMessage(putNewUsuario).getId());
	}

	@Test
	public void putNewUsuarioErrorObrigatorioTest() {
		UsuarioMessage message = getMockUsuario(null, 999l).buildMessage();
		message.setEmail(null);
		BaseResult<UsuarioMessage> putNewUsuario = usuarioService.putNewUsuario(message);
		Assert.assertFalse(putNewUsuario.isSuccess());
	}

	@Test
	public void putNewUsuarioCampanhaServiceDownTest() {
		UsuarioMessage message = getMockUsuario(null, 999l).buildMessage();
		Mockito.when(usuarioSearchDao.findOneUsuarioByEmail(message.getEmail())).thenReturn(getMockUsuario(78l, 999l));
		BaseResult<UsuarioMessage> putNewUsuario = usuarioService.putNewUsuario(message);
		Assert.assertFalse(putNewUsuario.isSuccess());
	}

	@Test
	public void putUpdateUsuarioSuccessTest() {
		long entityId = 1l;
		Usuario saved = this.getMockUsuario(entityId, 999l);
		Mockito.when(usuarioBaseDao.findOne(entityId)).thenReturn(saved);
		Mockito.when(usuarioBaseDao.save(Mockito.any(Usuario.class))).thenReturn(getMockUsuario(1l, 999l));

		UsuarioMessage newUsuario = getMockUsuario(entityId, 999l).buildMessage();
		newUsuario.setEmail("novo@novo.com.br");
		BaseResult<UsuarioMessage> putNewUsuario = usuarioService.putUpdateUsuario(newUsuario);
		Assert.assertTrue(putNewUsuario.isSuccess());
	}

	private UsuarioMessage getUsuarioMessage(BaseResult<UsuarioMessage> putNewUsuario) {
		return (UsuarioMessage) putNewUsuario.getResult();
	}

	private Usuario getMockUsuario(Long entityId, Long timeId) {
		Usuario usuario = new Usuario();
		usuario.setId(entityId);
		usuario.setDataNascimento(DateTime.now().minusYears(20).toDate());
		usuario.setNome("Usuarioo: " + entityId);
		usuario.setEmail("email@email.com.br");
		usuario.setTimeId(timeId);
		usuario.setBaseModel(BaseModel.neww());
		return usuario;
	}

}
