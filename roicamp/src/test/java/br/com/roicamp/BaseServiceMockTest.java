package br.com.roicamp;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.basecamp.service.CampanhaServiceHessian;
import br.com.roicamp.business.CampanhaBusiness;
import br.com.roicamp.business.CampanhaUsuarioBusiness;
import br.com.roicamp.business.UsuarioBusiness;
import br.com.roicamp.repository.CampanhaBaseRepository;
import br.com.roicamp.repository.CampanhaUsuarioBaseRepository;
import br.com.roicamp.repository.UsuarioBaseRepository;
import br.com.roicamp.search.CampanhaSearchRepository;
import br.com.roicamp.search.CampanhaUsuarioSearchRepository;
import br.com.roicamp.search.UsuarioSearchRepository;
import br.com.roicamp.service.CampanhaServiceImpl;
import br.com.roicamp.service.UsuarioServiceImpl;
import net.vidageek.mirror.dsl.Mirror;

public abstract class BaseServiceMockTest {

	@Mock
	protected UsuarioSearchRepository usuarioSearchDao;

	@Mock
	protected UsuarioBaseRepository usuarioBaseDao;

	@Mock
	protected CampanhaUsuarioSearchRepository campanhaUsuarioSearchDao;

	@Mock
	protected CampanhaUsuarioBaseRepository campanhaUsuarioBaseDao;

	@Mock
	protected CampanhaSearchRepository campanhaSearchDao;

	@Mock
	protected CampanhaBaseRepository campanhaBaseDao;

	@InjectMocks
	protected UsuarioBusiness usuarioBusiness;

	@InjectMocks
	protected CampanhaBusiness campanhaBusiness;

	@InjectMocks
	protected CampanhaServiceImpl campanhaService;

	@InjectMocks
	protected CampanhaUsuarioBusiness campanhaUsuarioBusiness;

	@InjectMocks
	protected CampanhaServiceHessian campanhaSearchService;

	@InjectMocks
	protected UsuarioServiceImpl usuarioService;

	@Before
	public void setUp() {
		Mirror mirror = new Mirror();
		mirror.on(usuarioBusiness).set().field("searchDao").withValue(usuarioSearchDao);
		mirror.on(usuarioBusiness).set().field("baseDao").withValue(usuarioBaseDao);

		mirror.on(campanhaBusiness).set().field("searchDao").withValue(campanhaSearchDao);
		mirror.on(campanhaBusiness).set().field("baseDao").withValue(campanhaBaseDao);

		mirror.on(campanhaUsuarioBusiness).set().field("searchDao").withValue(campanhaUsuarioSearchDao);
		mirror.on(campanhaUsuarioBusiness).set().field("baseDao").withValue(campanhaUsuarioBaseDao);

		mirror.on(usuarioService).set().field("campanhaSearchService").withValue(campanhaSearchService);
		mirror.on(usuarioService).set().field("business").withValue(usuarioBusiness);
		mirror.on(campanhaService).set().field("business").withValue(campanhaBusiness);

		mirror.on(campanhaSearchService).set().field("campanhaBusiness").withValue(campanhaBusiness);
		mirror.on(campanhaSearchService).set().field("campanhaUsuarioBusiness").withValue(campanhaUsuarioBusiness);
	}

}
