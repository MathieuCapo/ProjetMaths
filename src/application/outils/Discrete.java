/* 
 * Simulation d'une loi uniforme discréte
 * IUT INFO 2                                Uniforme.java
 */

package application.outils;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Classe qui simule une loi discrete sur un ensemble E
 * fini, E est un sous-ensemble de R
 * L'utilisateur rentre les paramétres qu'il souhaite et le
 * programme renvoie des valeurs aléatoire en fonction de ces paramétres
 * @author landry.assemat
 *
 */
public class Discrete {

	private static Scanner entree = new Scanner(System.in);

	private static Double esperance;
	
	private ArrayList<Double> tab = new ArrayList<Double>();
	private static ArrayList<Double> proba = new ArrayList<Double>();

	//private static Double variance;

	private final static int TAILLE = 4;

	

	/* tableau qui compte le nombre de fois que l'on tire les différentes
	 * variables dans le tableau tab
	 */
	private static Double[][] compteur;

	static /* valeur random */
	Random rand = new Random();

	private static Double aVerif = 0.0;
	
	public void setaVerif(Double aVerif) {
		this.aVerif = aVerif;
	}

	/**
	 * Constructeur initial de la loi uniforme
	 */
	public Discrete() {
		super();
	}

	public Discrete(ArrayList<Double> tab, ArrayList<Double> proba) {
		this.tab= tab;
		this.proba = proba;
	}
	
	/**
	 * Constructeur avec arguments de la loi discréte
	 * @param esperance
	 * @param variance
	 */
	public Discrete(Double esperance) {
		this.esperance = esperance;
		//this.variance = variance;
	}

	/**
	 * Simule la loi discréte aléatoire sur des
	 * grandes valeurs pour savoir si on obitent
	 * la même esperance
	 * param nombre de simulations
	 * return esperance aleatoire
	 */
	public Double[][] simuler(Double nombre) {
            compteur = new Double[proba.size()][2];
            for(int i = 0; i< tab.size(); i++) {
                System.out.println(tab.get(i));
                compteur[i][0] = tab.get(i);
            }
            /* la valeur tirée au sort dans le tableau */
            Double hasard;
            for (int y = 0; y < nombre; y++) {
                hasard = Math.random();
                Double pos = 0.0;
                int test = 0;
                for (int i = 0; i < proba.size(); i++) {
                    pos += proba.get(i);
                    if (hasard < pos) {
                        if(compteur[i][1] == null) {
                            compteur[i][1]=1.0;
                        }else {
                            compteur[i][1]++;
                        }
                        test = i;
                        break;
                    }
                }
            }
            return compteur;
	}
}
