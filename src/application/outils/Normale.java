package application.outils;

import java.lang.Math;

public class Normale {
	/**
	 * Esperance de la loi normale
	 */
	private double esperance;

	/**
	 * Ecart type de la loi normale
	 */
	private double ecartType;
	
	/**
	 * Variance de la loi normale
	 */
	private double variance;

	/**
	 * Constructeur permet de creer une loi Normale
	 */
	public Normale(double esperance,double variance) {
		this.esperance = esperance;
		this.ecartType = Math.sqrt(variance);
		this.variance = variance;
	}
	
	/**
	 * Simule la loi en effectuant nbRepetition tirage
	 * @param nbRepetition nombre de tirage aleatoire a realiser pour simuler la loi
	 * @return un tableau contenant tous les tirages de la loi
	 */
	public Double[] simuler(Double nbRepetition) {
		Double[] resultat = new Double[nbRepetition.intValue()];
		Double esperanceObt = 0.0;
		/* Tire nbRepetition fois une valeur aleatoire */
		for (int i = 0; i < nbRepetition; i++) {
			resultat[i] = tirageAleatoire();
		}
		/* Calcul de l'esperance */
		for (int i=0; i < nbRepetition; i++) {
			esperanceObt += resultat[i];
		}
		System.out.println(esperanceObt/nbRepetition + "--> doit etre egale a :" + esperance 
				+ " \nEcart type : " + ecartType + " Variance : " + variance);
		return resultat;
	}

	/**
	 * Tir aleatoirement un nombre correspondant aux parametres de la loi Normale
	 * @return RACINE(-2*LN(R1))*COS(2*PI()*S1)*ecartType+esperance
	 * Tirage aleatoire prenant en compte les parametres de la loi
	 */
	public double tirageAleatoire() {
		return Math.sqrt(-2*Math.log(Math.random()))*Math.cos(2 * Math.PI * Math.random()) * ecartType + esperance;
	}


}
