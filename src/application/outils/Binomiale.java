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
	private int nb;
	
	/** probabilit� de succ�s de l'exp�rience */
	private double p ;
        
        private final static int NB_EXPERIENCE = 1000;
	
	/** liste des r�sultats obtenue lors de l'exp�rience de Bernouilli */
	private ArrayList<Integer> conteneurChemin;
	
	/** ensemble des moyennes, � renvoyer pour avoir l'ensemble des r�sultats des tests */
	private Double[] contientNbApparition;
	
	/** Moyenne de succ�s obtenue */
	private Double moyenne;
	
	private Double nbApparition;
        
	/** scanner des entrees utilisateur */
	private Scanner entree = new Scanner(System.in);
	
	
	/** 
	 * constructeur de la Loi
	 */
	public Binomiale(Double proba, int NbLance) {
		//entreeNbLance(); // initialise le nombre de lancer (nb)
		//entreeValProb();  // initialise la valeur de succ�s (p)
		this.p = proba;
		this.nb = NbLance;
		moyenne = 0.0;
		nbApparition = 0.0;
		conteneurChemin = new ArrayList<Integer>();
                contientNbApparition = new Double[NB_EXPERIENCE];
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
            System.out.println(moyenne);
            nbApparition = moyenne;
            moyenne /= conteneurChemin.size();
           return moyenne;
	}
	
	public Double[] getcontientNbApparition() {
		return contientNbApparition;
	}
	
	
	
	/**
	 * Lance la simulation de la loi Binomiale
	 */
	public void simuler() {
		/* Lancer le constructeur quand on ouvre la page interface */
		/* ACTION LORSQU'ON CLIQUE SUR SIMULER */
		for ( int y = 0; y < NB_EXPERIENCE; y++) {
			for(int i = 0; i < nb; i++) {
				conteneurChemin.add(Bernouilli());
			}
			moyenne = moyenneApparition();
			System.out.println("moyenne d'apparition de la valeur correct lors de la simulation : " + moyenne
								+ "\nproba de succ�s : " + p);
                    contientNbApparition[y] = nbApparition;
                    System.out.println(nbApparition);
                    nbApparition = 0.0;
                    moyenne = 0.0;
                    conteneurChemin = new ArrayList<Integer>();
		}
	}
}
