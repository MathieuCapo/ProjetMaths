package application.outils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TraitementFichier {
	
	private static String NOM_FICHIER = "resultat";
	
	private static String EXTENSION = ".csv";
	
	public static void save(ArrayList<Double[]> resultats, String nomLoi) {
		for(int i = 0; i < resultats.size(); i++) {
			enregistrerFichier(NOM_FICHIER + nomLoi + i+".csv", resultats.get(i));
		}
	}
	
	public static void enregistrerFichier(String nomFichier,Double[] tableau){
		try (FileWriter fichierSortie = new FileWriter(nomFichier)){
			Arrays.sort(tableau);
			for(int i = 0; i < tableau.length; i++) {
                            fichierSortie.write(tableau[i].toString()+";\n");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
        
        public static void saveTab2D(ArrayList<Double[][]> resultats, String nomLoi) {
		for(int i = 0; i < resultats.size(); i++) {
			enregistrerFichier2D(NOM_FICHIER + nomLoi + i+".csv", resultats.get(i));
		}
	}
        
        public static void enregistrerFichier2D(String nomFichier,Double[][] tableau){
		try (FileWriter fichierSortie = new FileWriter(nomFichier)){
			for(int i = 0; i < tableau.length; i++) {
                            fichierSortie.write(tableau[i][0]+";"+tableau[i][1].toString()+";\n");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
