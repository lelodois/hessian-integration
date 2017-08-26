package br.com.roicamp.comum;

import org.apache.commons.lang3.Validate;

public class BaseValidator {

	private BaseValidator() {
	}

	public static void notNull(String message, Object... itens) {
		for (Object item : itens) {
			Validate.notNull(item, message == null ? "Campo obrigat�rio n�o preenchido" : message);
			Validate.notBlank(item.toString(), message == null ? "Campo obrigat�rio est� vazio" : message);
		}
	}
}
