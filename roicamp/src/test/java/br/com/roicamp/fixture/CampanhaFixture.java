package br.com.roicamp.fixture;

import org.joda.time.DateTime;

import br.com.basecamp.message.CampanhaMessage;
import br.com.roicamp.comum.DateUtils;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CampanhaFixture implements TemplateLoader {

	@Override
	public void load() {

		Fixture.of(CampanhaMessage.class).addTemplate("valid", new Rule() {
			{
				add("nome", random("Campe�es", "Amadores Bons", "Mestres", "Inacredit�veis"));
				add("timeId", random(100l, 200l, 600l, 400l));
				add("inicioVigencia", DateUtils.toStringDateTruncate(DateTime.now().toDate()));
				add("fimVigencia", DateUtils.toStringDateTruncate(DateTime.now().plusMonths(1).toDate()));
			}
		});
	}
}
