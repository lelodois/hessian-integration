package br.com.roicamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import br.com.basecamp.service.CampanhaServiceHessian;

@Configuration
public class HessianConfiguration {

	@Autowired
	private CampanhaServiceHessian campanhaService;

	@Bean(name = "/CampanhaService")
	public HessianServiceExporter campanhaServiceBean() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(campanhaService);
		exporter.setServiceInterface(CampanhaServiceHessian.class);
		return exporter;
	}

}
