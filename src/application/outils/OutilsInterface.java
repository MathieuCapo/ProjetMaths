package application.outils;

import java.util.ArrayList;

public class OutilsInterface {

	/**
	 * Convertie une arrayList en un tableau
	 * @param valeurs ArrayList conteant des valeurs
	 * @return un tableau contenant toutes les valeurs de l'ArrayList valeurs
	 */
	public static Double[] convertirTableau(ArrayList<Double> valeurs) {
		Double[] tab = new Double[valeurs.size()];
		for(int i = 0; i <valeurs.size(); i++) {
			tab[i] = valeurs.get(i);
		}
		return tab;
	}

	/**
	 * Creer une loi Discrete
	 * Puis simule la loi en simulant nbRep valeurs
	 * @param listValeurs liste des valeurs
	 * @param listProbabilite liste des probabilites
	 * @param nbRep nombre de repetitions
	 * @return les resultats des tirages de la simulation de la loi
	 */
	public static Double[][] simulerDiscrete(ArrayList<Double> listValeurs, ArrayList<Double> listProbabilite,Double nbRep) {
		Discrete loi = new Discrete(listValeurs,listProbabilite);
		return loi.simuler(nbRep);
	}

	/**
	 * Creer une loi Normale
	 * Puis simule la loi en simulant nbRep valeurs
	 * @param esperance Esperance de la loi a simuler
	 * @param variance Variance de la loi a simuler
	 * @param repetition nombre de repetitions
	 * @return les resultats des tirages de la simulation de la loi
	 */
	public static Double[] simulerNormale(double esperance, double variance, Double repetition) {
		Normale loi = new Normale(esperance, variance);
		return loi.simuler(repetition);
	}

	/**
	 * Creer une loi Exponentielle
	 * Puis simule la loi en simulant nbRep valeurs
	 * @param lambda lambda de la loi Exponentielle
	 * @param repetition nombre de repetitions
	 * @return les resultats des tirages de la simulation de la loi
	 */
	public static Double[] simulerExponentielle(double lambda, Double repetition) {
		Exponentielle loi = new Exponentielle(lambda);
		return loi.simuler(repetition);
	}

	/**
	 * Creer une loi Binomiale
	 * Puis simule la loi en simulant nbRep valeurs
	 * @param nbRep nombre de repetition de tirages de la loi
	 * @param proba probabilite de victoire
	 * @param repetition nombre de simulation de la loi a realiser
	 * @return les resultats des tirages de la simulation de la loi
	 */
	public static Double[] simulerBinomiale(double nbRep, double proba, Double repetition) {
		Binomiale loi = new Binomiale(proba, nbRep, repetition);
		return loi.simuler(repetition);
	}

	/**
	 * Creer une loi Uniforme
	 * Puis simule la loi en simulant nbRep valeurs
	 * @param listValeurs liste des valeurs possibles
	 * @param repetition nombre de repetitions
	 * @return les resultats des tirages de la simulation de la loi
	 */
	public static Double[][] simulerUniforme(ArrayList<Double> listValeurs, Double repetition) {
		Double[] tab = convertirTableau(listValeurs);
		Uniforme loi = new Uniforme(tab);
		return loi.simuler(repetition);
	}
	
	
	/**
	 * Vérifie si la String peut être parse en nombre afin de 
	 * pouvoir l'envoyer aux méthodes gérant les différentes lois
	 * @param aVerif
	 * @return un booleen vrai si aVerif a le bon format, faux sinon
	 */
	public static boolean canBeNumber(String aVerif) {
		try {
			Double.parseDouble(aVerif);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * permet de vérifier que la valeur se situe bien 
	 * entre les valeurs passées en argument (comprises)
	 * @return un boolean = true si valeur correct false sinon
	 */
	public static boolean verifValeur(Double debut, Double fin, Double valeur) {
		return valeur >= debut && valeur <= fin;
	}
}
