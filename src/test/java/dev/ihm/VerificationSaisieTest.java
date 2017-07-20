package dev.ihm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.utils.VerificationSaisie;
import fr.pizzeria.model.CategoriePizza;

public class VerificationSaisieTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

	private static final Logger LOG = LoggerFactory.getLogger(VerificationSaisieTest.class);
	private VerificationSaisie verifSaisie;

	@Before
	public void setUp() throws Exception {
		this.verifSaisie = mock(VerificationSaisie.class);

	}

	@Test
	public void verifCateQuit() {
		systemInMock.provideLines("Quit");
		VerificationSaisie.verifCate();
		assertThatNullPointerException();
	}

	@Test
	public void verifCateSaisie() {
		systemInMock.provideLines("Viande");
		CategoriePizza cate = VerificationSaisie.verifCate();
		assertThat(cate.getCategorie()).contains("Viande");
	}

	@Test
	public void verifCateErreurSaisie() {
		systemInMock.provideLines("Viandes", "Viande");
		VerificationSaisie.verifCate();
		String logConsole = systemOutRule.getLog();
		assertThatExceptionOfType(IllegalArgumentException.class);

	}

	@Test
	public void verifSaisiePrixQuit() {
		systemInMock.provideLines("QUIT");

		assertThat(VerificationSaisie.verifSaisiePrix() == 0.0D);
	}

	@Test
	public void verifSaisiePrixInf0() {
		systemInMock.provideLines("-10", "10.0");
		double prix = VerificationSaisie.verifSaisiePrix();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("Erreur le prix doit être supérieur à 0");

	}

}
