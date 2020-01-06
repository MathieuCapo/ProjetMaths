package application.outils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TraitementFichier {
	
	/**
	 * debut du nomm du fichier
	 */
	private static String NOM_FICHIER = "resultat";

	/**
	 * Extension du fichier a enregistrer
	 */
	private static String EXTENSION = ".csv";

	/**
	 * Enregistre toutes les variables aleatoires simuler 
	 * @param resultats resultats obtenu
	 * @param nomLoi nom de la loi simuler
	 */
	public static void save(ArrayList<Double[]> resultats, String nomLoi) {
		/* Boucle tant qu'il reste des variables aleatoires et creer les fichier*/
		for(int i = 0; i < resultats.size(); i++) {
			enregistrerFichier(NOM_FICHIER + nomLoi + i+".csv", resultats.get(i));
		}
	}

	/**
	 * Enregistre dans le fichier tous les resultats
	 * @param nomFichier nom du fichier dans lequel on doit ecrire
	 * @param tableau tableau contenant les resultats a inscrire
	 */
	public static void enregistrerFichier(String nomFichier,Double[] tableau){
		System.out.println("Enregistrement");
		try (FileWriter fichierSortie = new FileWriter(nomFichier)){
			Arrays.sort(tableau); // tri le tableau necessaire pour le graphique
			for(int i = 0; i < tableau.length; i++) { // ecrit toutes les donnees
				fichierSortie.write(tableau[i].toString()+";\n");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println("Données enregistrées");
	}

	/**
	 * Enregistre toutes les variables aleatoires simuler 
	 * Certianes loi necessite un double tableau
	 * @param resultats resultats obtenu
	 * @param nomLoi nom de la loi simuler
	 */
	public static void saveTab2D(ArrayList<Double[][]> resultats, String nomLoi) {
		for(int i = 0; i < resultats.size(); i++) {
			enregistrerFichier2D(NOM_FICHIER + nomLoi + i+".csv", resultats.get(i));
		}
	}

	/**
	 * Enregistre dans le fichier tous les resultats
	 * @param nomFichier nom du fichier dans lequel on doit ecrire
	 * @param tableau tableau contenant les resultats a inscrire
	 */
	public static void enregistrerFichier2D(String nomFichier,Double[][] tableau){
		System.out.println("Enregistrement");
		try (FileWriter fichierSortie = new FileWriter(nomFichier)){
			for(int i = 0; i < tableau.length; i++) { // ecrit toutes les donnees
				fichierSortie.write(tableau[i][0]+";"+tableau[i][1].toString()+";\n");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println("Données enregistrées");
	}
}
