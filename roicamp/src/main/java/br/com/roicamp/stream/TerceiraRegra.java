package br.com.roicamp.stream;

class TerceiraRegra extends ProcuraVogalChain {

	@Override
	public Character continuar(char atual, IStream reader) {
		return atual;
	}

	@Override
	public boolean podeContinuar(char atual, IStream reader) {
		return reader.hasNext() && isConsoante(reader.getNext());
	}

}
