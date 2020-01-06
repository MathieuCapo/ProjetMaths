/*
 * Simulation.java
 */

package application.outils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Principe : 
 * Il faut une V.A. qui repr�sente une �preuve n'ayant que 2 issue, vrai faux ( bernoulli ), 
 * la proba de gagner est d�finie par l'utilisateur, on calcule la moyenne
 * du nombre de fois ou l'utilisateur � gagn� et on compare � l'esp�rance
 */
public class Binomiale {

	/**	 Nombre d'exp�rience � r�aliser */
	private double nb;

	/** probabilit� de succ�s de l'exp�rience */
	private double p ;

	/** liste des r�sultats obtenue lors de l'exp�rience de Bernouilli */
	private ArrayList<Integer> conteneurChemin;
	
	/** le nombre de fois que l'utilisateur veut r�aliser la loi Binomiale */
	private double nbExperience;
	
	/** ensemble des moyennes, � renvoyer pour avoir l'ensemble des r�sultats des tests */
	private Double[] contientNbApparition;

	/** Moyenne de succ�s obtenue */
	private Double moyenne;

	private Double nbApparition;

	/** scanner des entrees utilisateur */
	private Scanner entree = new Scanner(System.in);


	/** 
	 * constructeur de la Loi
	 * @param proba de succ�s de la loi de Bernouilli
	 * @param NbLance nombre d'exp�rience de Bernouilli � r�aliser par loi binomiale
	 * @param nbExperience nombre de fois que l'utilisateur veut r�aliser la loi Binomiale
	 */
	public Binomiale(double proba, double NbLance,Double nbExperience) {
		//entreeNbLance(); // initialise le nombre de lancer (nb)
		//entreeValProb();  // initialise la valeur de succ�s (p)
		this.p = proba;
		this.nb = NbLance;
		this.nbExperience = nbExperience;
		moyenne = 0.0;
		nbApparition = 0.0;
		conteneurChemin = new ArrayList<Integer>();
		contientNbApparition = new Double[(int) this.nbExperience];
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
	 * Calcul le nombre de fois que le succ�s est pr�sent dans conteneurChemin et le divise
	 * par de nombre total de r�sultat afin de trouver une moyenne
	 * @param conteneurChemin la liste de valeurs obtenues
	 * @return la moyenne du succ�s
	 */
	public Double moyenneApparition() {
		for(int i = 0; i < conteneurChemin.size(); i++) {
			if(conteneurChemin.get(i).equals(1)) {
				moyenne += 1; 
			}
		}
		//System.out.println(moyenne);
		nbApparition = moyenne;
		moyenne /= conteneurChemin.size();
		return moyenne;
	}

	public Double[] getcontientNbApparition() {
		return contientNbApparition;
	}

	/**
	 * Permet de lancer la simulation
	 * @param args
	 * @return 
	 */
	public Double[] simuler() {
		for ( int y = 0; y < nbExperience; y++) {
			for(int i = 0; i < nb; i++) {
				conteneurChemin.add(Bernouilli());
			}
			moyenne = moyenneApparition();
			System.out.println("moyenne d'apparition de la valeur correct lors de la simulation : " + moyenne
					+ "\nproba de succ�s : " + p);
			contientNbApparition[y] = nbApparition;
			System.out.println("nombre d'apparition : " + nbApparition + '\n' + '\n');
			nbApparition = 0.0;
			moyenne = 0.0;
			conteneurChemin = new ArrayList<Integer>();
		}
		return contientNbApparition;
	}
}
