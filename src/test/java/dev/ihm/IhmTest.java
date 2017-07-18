package dev.ihm;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.utils.VerificationSaisie;

public class IhmTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

	private static final Logger LOG = LoggerFactory.getLogger(IhmTest.class);
	private VerificationSaisie verifSaisie;

	@Before
	public void setUp() throws Exception {
		this.verifSaisie = mock(VerificationSaisie.class);

	}

	@Test
	public void testVerifCateQuit() {
		systemInMock.provideLines("TEST", "viandes", "quit");

		String logConsole = systemOutRule.getLog();
		assert (VerificationSaisie.verifCate() == null);
	}

	@Test
	public void testVerifCateViande() {
		systemInMock.provideLines("TEST", "viandes", "viande");

		String logConsole = systemOutRule.getLog();
		assert (VerificationSaisie.verifCate().toString()).contains("VIANDE");
	}
}
