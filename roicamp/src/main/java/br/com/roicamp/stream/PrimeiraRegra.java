package br.com.roicamp.stream;

class PrimeiraRegra extends ProcuraVogalChain {

	@Override
	Character continuar(char atual, IStream reader) {
		return new SegundaRegra().procurar(reader.getNext(), reader);
	}

	@Override
	public boolean podeContinuar(char atual, IStream reader) {
		return isConsoante(atual) && reader.hasNext();
	}

}
