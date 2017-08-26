package br.com.roicamp.fixture;

import org.joda.time.DateTime;

import br.com.basecamp.message.UsuarioMessage;
import br.com.roicamp.comum.DateUtils;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioFixture implements TemplateLoader {

	@Override
	public void load() {

		Fixture.of(UsuarioMessage.class).addTemplate("valid", new Rule() {
			{
				add("nome", random("X500", "X510", "X520", "X530", "X540", "X550"));
				add("timeId", random(100l, 200l, 600l, 400l));
				add("email", random("frango@teste.com.br", "gol@teste.com.br", "defesa@teste.com.br"));
				add("dataNascimento", DateUtils.toStringDateTruncate(DateTime.now().minusYears(20).toDate()));
			}
		});

	}
}
