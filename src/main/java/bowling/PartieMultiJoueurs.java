package bowling;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
	private Map<String, PartieMonoJoueur> joueurs;
	private List<String> ordreJoueurs;
	private int indexJoueurCourant;

	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("La liste des joueurs ne peut pas être vide.");
		}

		joueurs = new HashMap<>();
		ordreJoueurs = Arrays.asList(nomsDesJoueurs);
		for (String joueur : ordreJoueurs) {
			joueurs.put(joueur, new PartieMonoJoueur());
		}
		indexJoueurCourant = 0;

		return "Prochain tir : joueur " + ordreJoueurs.get(indexJoueurCourant) + ", tour n° 1, boule n° 1";
	}

	@Override
	public String enregistreLancer(int quillesAbattues) {
		String joueurCourant = ordreJoueurs.get(indexJoueurCourant);
		PartieMonoJoueur partie = joueurs.get(joueurCourant);

		if (partie.enregistreLancer(quillesAbattues)) {
			return "Prochain tir : joueur " + joueurCourant + ", tour n° " + partie.numeroTourCourant() +
				", boule n° " + partie.numeroProchainLancer();
		} else {
			// Passer au joueur suivant
			indexJoueurCourant = (indexJoueurCourant + 1) % ordreJoueurs.size();
			joueurCourant = ordreJoueurs.get(indexJoueurCourant);
			partie = joueurs.get(joueurCourant);

			return "Prochain tir : joueur " + joueurCourant + ", tour n° " + partie.numeroTourCourant() +
				", boule n° " + partie.numeroProchainLancer();
		}
	}

	@Override
	public int scorePour(String joueur) {
		if (!joueurs.containsKey(joueur)) {
			throw new IllegalArgumentException("Joueur inconnu : " + joueur);
		}
		return joueurs.get(joueur).score();
	}
}
