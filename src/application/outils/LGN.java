/*
 * LGN.java
 */

package application.outils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Principe : 
 * Il faut une V.A. qui représente une épreuve n'ayant que 2 issues, vrai ou faux ( bernoulli ), 
 * La proba de gagner est 0.5 car nous simulons l'expérience d'une pièce de monnaie
 * On calcule la moyenne obtenue c'est-à-dire la proportion de succés
 * On compare ensuite cette moyenne à l'espérance
 */
public class LGN {

	/**	 Nombre d'expérience à réaliser */
	private int nbExp;

	/** probabilité de succés de l'expérience */
	private double p = 0.5;

	/** liste des résultats obtenus lors de l'expérience de Bernouilli */
	private Double[][] resultatsBernouilli;

	/** Moyenne de succés obtenue */
	private Double moyenne = 0.0;

	/** scanner des entrees utilisateur */
	private Scanner entree = new Scanner(System.in);


	/**
	 * Constructeur de la Loi
	 */
	public LGN() {
		resultatsBernouilli = new Double[(int) nbExp][(int) nbExp];
	}

	/**
	 * Realisation de  l'experience de Bernoulli avec la proba de succ�s donn� par l'utilisateur
	 * @param probasucces probabilit� de succ�s de tomber sur 1 ( vrai ) lors de l'exp�rience
	 * @return un int = true ou false
	 * 1 = vrai
	 * 0 = faux
	 */
	public int Bernouilli() {
		int resultat = Math.random() <= p ? 1 : 0 ;
		return resultat;
	}

	/**
	 * Calcul le nombre de fois que le succés est présent dans conteneurChemin et le divise
	 * par de nombre total de résultat afin de trouver une moyenne
	 * @param conteneurChemin la liste de valeurs obtenues
	 * @return la moyenne du succés
	 */
	public void calculMoyenneDynamique() {
		int compteur;
		for (compteur = 0; compteur < resultatsBernouilli.length && resultatsBernouilli[0][compteur] != null; compteur++);
		
		for(int i = 0; i < compteur; i++) {
			moyenne += resultatsBernouilli[0][i];
		}
		
		moyenne /= compteur;
	}

	/**
	 * Permet de lancer la simulation
	 * @param args
	 * @return 
	 */
	public Double[][] simuler(int nbExperience) {
		/* ACTION LORSQU'ON CLIQUE SUR SIMULER */
		nbExp = (int)Math.round(nbExperience);
		resultatsBernouilli = new Double[nbExp][nbExp];
		for(int i = 0; i < nbExp; i++) {
			resultatsBernouilli[0][i] = ((double) Bernouilli());
			calculMoyenneDynamique();
			resultatsBernouilli[1][i] = moyenne;
			System.out.println("moyenne progressive : " + moyenne
							 + "\nproba de succés : " + p);
		}
		
		// test pour vérifier le contenu
		for (int i = 0; i < nbExp; i++) {
			System.out.println(resultatsBernouilli[0][i]);
			System.out.println(resultatsBernouilli[1][i]);
		}
		
		return resultatsBernouilli;
	}
	
	/** test */
	public static void main (String[] args) {
		LGN test = new LGN();
		test.simuler(1000);
	}
}
