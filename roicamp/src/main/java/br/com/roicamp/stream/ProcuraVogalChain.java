package br.com.roicamp.stream;

import java.util.List;

import com.google.common.collect.Lists;

abstract class ProcuraVogalChain {

	final List<String> consoantes = Lists.newArrayList("B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z");

	public Character procurar(char atual, IStream reader) {
		if (podeContinuar(atual, reader)) {
			return continuar(atual, reader);
		}
		return null;
	}

	abstract Character continuar(char atual, IStream reader);

	abstract boolean podeContinuar(char atual, IStream reader);

	protected boolean isConsoante(Character cchar) {
		return cchar != null && consoantes.contains(String.valueOf(cchar).toUpperCase());
	}

	protected boolean isVogal(Character cchar) {
		return cchar != null && isConsoante(cchar) == false;
	}
}