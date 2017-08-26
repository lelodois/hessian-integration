package br.com.roicamp.stream;

class SegundaRegra extends ProcuraVogalChain {

	@Override
	public Character continuar(char atual, IStream reader) {
		if (reader.hasNext()) {
			return new TerceiraRegra().procurar(atual, reader);
		}
		return atual;
	}

	@Override
	public boolean podeContinuar(char atual, IStream reader) {
		return isVogal(atual);
	}

}
