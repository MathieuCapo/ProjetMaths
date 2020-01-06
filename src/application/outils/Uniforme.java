/* 
 * Simulation d'une loi uniforme discr�te
 * IUT INFO 2                                Uniforme.java
 */

package application.outils;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe qui simule une loi uniforme discr�te sur un ensemble E
 * fini, E est un sous-ensemble de R
 * L'utilisateur rentre les param�tres qu'il souhaite et le
 * programme renvoie des valeurs al�atoire en fonction de ces param�tres
 * @author landry.assemat
 *
 */


public class Uniforme {

	private static Double esperance;

	
	private static Scanner entree = new Scanner(System.in);
	//private static Double variance;
	
	private static int TAILLE = 10;
	
	private static Double[] tab = new Double[TAILLE];
	
	private static Double[] proba = new Double[TAILLE];
	
	/* tableau qui compte le nombre de fois que l'on tire les diff�rentes
	 * variables dans le tableau tab
	 */
	private static Double[][] compteur;
	
	static /* valeur random */
	Random rand = new Random();

    public static void setTAILLE(int TAILLE) {
        Uniforme.TAILLE = TAILLE;
    }
        
        
	
	/**
	 * Constructeur initial de la loi uniforme
	 */
	public Uniforme() {
		super();
	}
	
	
	public Uniforme(Double tab[]) {
		this.tab = tab;
		setTAILLE(tab.length);
	}
	
	/**
	 * Methode qui va calculer avec des nombres al�atoires
	 * de la loi uniforme discr�te et renvoi les r�sultats 
	 * return resultat
	 * @return tableau des nombres al�atoires g�n�r�s
	 */
	public static Double[] initialiser() {
		
		Double n = 0.0;
		boolean valide = false;
		
		for(int i=0; i < tab.length; i++) {
			System.out.println("entrer valeur");
			do {
				if(entree.hasNextInt()) {
					n = entree.nextDouble();
					valide = true;
				} else {
					System.out.println("Re-rentrer la valeur incorrect");
				}

				entree.nextLine();
			} while(!valide);

			tab[i] = n;
		}
		return tab;
	}
	
	
	/**
	 * M�thode qui renvoie le tableau des probabilit�s
	 * qui contient les probabilit�s du tableau d'entier
	 * al�atoire
	 * @return proba tableau de probabilite
	 */
	public static Double[] probabilite() {
		
		for (int j = 0; j < proba.length; j++) {
			Double n = 1.0/TAILLE;
			proba[j] = n;
		}
		return proba;
	}
	
	/**
	 * Calcule l'esp�rance de la loi uniforme disc�rte
	 * @return esperance
	 */
	public static Double esperance() {
		
		esperance = 0.0;
		
		for(int i = 0; i < TAILLE; i++) {
			esperance += tab[i]*proba[i];
		}
		return esperance;
	}
	
	/**
	 * Simule la loi uniforme discr�te al�atoire sur des
	 * grandes valeurs pour savoir si on obitent
	 * la m�me esperance
	 * @param nombre de simulations
	 * return esperance aleatoire
	 */
	public static Double[][] simuler(Double nombre) {
            probabilite();
            /* la valeur tir� au sort dans le tableau */
            int hasard;
            compteur = new Double[TAILLE][2];
            
            for(int i = 0; i < TAILLE; i++){
                compteur[i][0] = tab[i];
            }

            for(int i = 0; i < nombre; i++) {
                hasard = rand.nextInt(TAILLE);
                for(int j = 0; j < TAILLE ; j++) {
                    if (hasard == j) {
                        if(compteur[hasard][1] == null) {
                            compteur[hasard][1] = 1.0;
                        } else {
                            compteur[hasard][1]++;
                        }
                    }
                }
            }
            return compteur;
		
	}
	
	/**
	 * Affiche la siulation et les tableaux
	 
	public static void afficher(Double n) {
		
		for (int i = 0; i < TAILLE; i++) {
			System.out.print(tab[i] + " | ");
		}
		System.out.println("\n");
		for (int i = 0; i < TAILLE; i++) {
			System.out.print(proba[i] + " | ");
		}
		System.out.println("\n\nesperance : " + esperance);
		
		System.out.println("\nNombre de tirages effectu�s : " + n);
		
		System.out.println("\nNombres d'apparitions des nombres : ");
		for (int i = 0; i < TAILLE; i++) {
			System.out.print(compteur[i] + " | ");
		}
		
		System.out.println("\n\nMoyenne d'apparition : ");
		for (int i = 0; i < TAILLE; i++) {
			System.out.print(compteur[i]/n + " | ");
		}
		
	}*/
	
}

