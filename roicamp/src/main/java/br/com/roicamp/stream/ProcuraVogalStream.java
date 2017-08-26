package br.com.roicamp.stream;

public class ProcuraVogalStream implements IStream {

	private String text;
	private int index = 0;

	public ProcuraVogalStream(String text) {
		this.text = text;
	}

	@Override
	public char getNext() {
		return text.charAt(index++);
	}

	@Override
	public boolean hasNext() {
		return text.length() >= (index + 1);
	}

	public static String findFirstChar(String text) {
		ProcuraVogalStream stream = new ProcuraVogalStream(text);

		Character result = null;
		while (stream.hasNext()) {
			Character atual = stream.getNext();
			result = result != null && atual.equals(result) ? null : result;

			if (result == null) {
				result = new PrimeiraRegra().procurar(atual, stream);
			}
		}
		return result != null ? String.valueOf(result) : null;
	}

}
