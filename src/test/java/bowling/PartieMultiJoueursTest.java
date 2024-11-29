package bowling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartieMultiJoueursTest {

	@Test
	void testDemarreNouvellePartie() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		String message = partie.demarreNouvellePartie(new String[] {"Pierre", "Paul"});
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", message);
	}

	@Test
	void testEnregistreLancer() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[] {"Pierre", "Paul"});

		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2", partie.enregistreLancer(5));
		assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", partie.enregistreLancer(3));
	}

	@Test
	void testScorePour() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[] {"Pierre", "Paul"});
		partie.enregistreLancer(5);
		partie.enregistreLancer(4); // Score Pierre = 9
		partie.enregistreLancer(3);
		partie.enregistreLancer(2); // Score Paul = 5

		assertEquals(9, partie.scorePour("Pierre"));
		assertEquals(5, partie.scorePour("Paul"));
	}

	@Test
	void testScorePourJoueurInconnu() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[] {"Pierre", "Paul"});
		assertThrows(IllegalArgumentException.class, () -> partie.scorePour("Jacques"));
	}

	@Test
	void testDemarreNouvellePartieAvecListeNulle() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		Exception exception = assertThrows(IllegalArgumentException.class,
			() -> partie.demarreNouvellePartie(null));
		assertEquals("La liste des joueurs ne peut pas être vide.", exception.getMessage());
	}

	@Test
	void testDemarreNouvellePartieAvecListeVide() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		Exception exception = assertThrows(IllegalArgumentException.class,
			() -> partie.demarreNouvellePartie(new String[0]));
		assertEquals("La liste des joueurs ne peut pas être vide.", exception.getMessage());
	}
}

