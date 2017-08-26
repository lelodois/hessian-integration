package br.com.roicamp;

import org.junit.Assert;
import org.junit.Test;

import br.com.roicamp.stream.ProcuraVogalStream;

public class ProcuraVogalTest {

	@Test
	public void procuraVogalTest() {
		Assert.assertNull(ProcuraVogalStream.findFirstChar("aAbBABacaf"));
	}

	@Test
	public void procuraAllVogaisTest() {
		Assert.assertEquals("a", ProcuraVogalStream.findFirstChar("aba"));
		Assert.assertEquals("e", ProcuraVogalStream.findFirstChar("aAbBABacafe"));
		Assert.assertEquals("i", ProcuraVogalStream.findFirstChar("abi"));
		Assert.assertEquals("o", ProcuraVogalStream.findFirstChar("abo"));
		Assert.assertEquals("u", ProcuraVogalStream.findFirstChar("abu"));
	}

	@Test
	public void procuraVogaisComRepeticaoTest() {
		Assert.assertEquals("u", ProcuraVogalStream.findFirstChar("abacaebu"));
		Assert.assertEquals("o", ProcuraVogalStream.findFirstChar("itieiriwiyo"));
	}

}
