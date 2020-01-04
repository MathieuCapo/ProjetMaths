package application.outils;

import java.util.Random;
import java.util.Scanner;

public class Exponentielle {
	
	/** Nombre de tirages */
	private int nbTirages = 10000;
	
	/** Lambda bouchon */
	private static Double LAMBDA_DEFAUT = -1.0;
	
	/** Tableau contenant les r�sultats des simulations */
	private Double resultats[] = new Double[nbTirages];
	
//	/** Tableau qui compte le nombre de fois que l'on a tir� les diff�rents r�sultats */
//	private static int[] compteur = new int[NB_TIRAGES];
//	
//	private Double resultatsSansDoublons[] = new Double[NB_TIRAGES];
	
	
	/** Le param�tre lambda */
	private Double lambda;
	
	
    /**
     * @param lambda
     */
    public Exponentielle(Double lambda) {
    	this.lambda = lambda;		
    }
    
    
    /**
     * Permet d'initialiser lambda
     */
    public void setLambda() {

    	Scanner entree = new Scanner(System.in);
    	
		boolean ok = false;
		
		do {
			if (entree.hasNextDouble()) {
                this.lambda = entree.nextDouble();
                ok = this.lambda > 0 ? true : false;
            }
			
            if (!ok) {
                System.out.println("Veuillez saisir un double positif");
            }
            
            entree.nextLine();
            
		} while (!ok);
		
    }
    
	
    /**
     * Tire un double positif al�atoire
     */
    public double tirage() {
    	Random rand = new Random();
	    return 1 - Math.abs(rand.nextDouble());
    }
    
    
	/**
	 * Fait le calcul de la probabilit� d'un nombre random positif
	 * @param lambda param�tre de la loi Exponentielle
	 * @return la proba
	 */
	public double calculProba(double lambda) {
	    return -(1 / lambda) * Math.log( tirage() );
	}
	
	
	/**
	 * Effectue 10 000 simulations al�atoires
	 */
	public Double[] simuler(Double repetition) {
		nbTirages = (int)Math.round(repetition);
		for (int i = 0; i < nbTirages; i++) {
			this.resultats[i] = this.calculProba(lambda);
		}
		return this.resultats;
	}
	
	
	/** 
     * Calcule l'esp�rance
     * @return 1/lamda esp�rance de la loi
     */
    public double calculEspe() {
    	return 1/lambda;
    }
	
    
    /** 
     * Calcule la variance
     * @return 1/lamba�, soit la variance de la loi
     */
    public double calculVar() {
    	return 1/Math.pow(lambda, 2);
    }
    
    
    /**
     * Calcule l'�cart-type
     * @return 1/lambda �cart-type de la loi
     */
    public double ecartType() {
    	return 1/lambda;
    }
    
    
	/**
	 * Cacule la moyenne des valeurs simul�es
	 * @param resultats obtenus
	 * @return moyenne
	 */
	public double calculMoy(Double[] resultats) {
		// Le cumul de toutes les simulations
		double cumul = 0.0;
		
		for (int i = 0; i < resultats.length; i++) {
			cumul += resultats[i];
		}
		
		return cumul/resultats.length;
	}

}