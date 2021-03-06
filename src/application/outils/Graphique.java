
package application.outils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


public class Graphique {

	/**
	 * Nom du fichier
	 */
	private static String nomFich;

	/**
	 * Affiche une boite de dialogue qui demande a l'utilisateur d'entrer un nom de fichier
	 */
	private static void showInputTextDialog() {
		TextInputDialog dialog = new TextInputDialog("resultat.csv");
		dialog.setTitle("NOM FICHIER");
		dialog.setHeaderText("Entrer le nom du fichier :");
		dialog.setContentText("Nom:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			nomFich = name;
		});
	}

	/**
	 * Au lancement de la''plication charge le graphique
	 */
	public static Scene graphe() {
		/*Initialisation du graphique */
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setCreateSymbols(false);
		/*Initialisation de la page */
		Scene scene  = new Scene(lineChart,800,600);
		showInputTextDialog();

		
		//Definition des axes
		xAxis.setLabel("Nombre de point");
		yAxis.setLabel("Valeur");
		lineChart.setTitle("Graphe simulation");
		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName("Simulation");
		String ligne;
		ArrayList<Double> resultats = new ArrayList<Double>();
		ArrayList<Double> valeurs = new ArrayList<Double>();
		int compteur = 0;
		/* Reggex permettant de savoir quel loi le graphe doit afficher*/
		String reggexUniforme = "resultatUniforme[0-9]+.csv";
		String reggexDiscrete = "resultatDiscrete[0-9]+.csv";
		String reggexExponentielle = "resultatExponentielle[0-9]+.csv";
		String reggexBinomiale = "resultatBinomiale[0-9]+.csv";
		int x;
		/* Lecture du fichier de l'utilisateur */
		try(BufferedReader lect= new BufferedReader(new FileReader(nomFich))) {
			/* Si loi uniforme ou discrete */
			if(nomFich.matches(reggexUniforme) || nomFich.matches(reggexDiscrete)) {
				xAxis.setLabel("Valeur");
				yAxis.setLabel("Nombre d'apparition");
				while((ligne = lect.readLine()) != null) {
					x = 1;
					/* Decoupe et recupere les deux valeurs ( valeurs et le nombre d'apparition */
					for (String val: ligne.split(";")) {
						if(x == 1) {
							System.out.println(val);
							valeurs.add(Double.parseDouble(val));
						} else if(x == 2){
							System.out.println(val);
							resultats.add(Double.parseDouble(val));
						}
						x++;
					}
					compteur++;
				}
				/* Ajoute les valeurs au graphe */
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(valeurs.get(i), resultats.get(i)));
				}
				lineChart.setCreateSymbols(true);
				//Si est une loi Exponentielle
			}else if(nomFich.matches(reggexExponentielle)) {
				while((ligne = lect.readLine()) != null) {//lit le fichier
					resultats.add(Double.parseDouble(ligne.substring(0,ligne.length()-1)));
					compteur++;
				}
				int compteurFin = resultats.size()-1;
				for(int i = 0; i < resultats.size(); i++) { //ajoute en partant de la fin les valeurs
					series.getData().add(new XYChart.Data(i, resultats.get(compteurFin)));
					compteurFin--;
				}
			} else { //pour toutes les autres lois recupere uniquement une colonne
				if(nomFich.matches(reggexBinomiale)) {
					xAxis.setLabel("Numero tirage");
					yAxis.setLabel("Nombre de succes");
				}
				while((ligne = lect.readLine()) != null) { // lit le fichier et ajoute les resultats
					resultats.add(Double.parseDouble(ligne.substring(0,ligne.length()-1)));
					compteur++;
				}
				/* Ajoute au graph les points */
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(i, resultats.get(i)));
				}
			}
			lineChart.getData().add(series); // ajoute les points au graphe
			return scene;
		} catch(IOException e) {
			System.err.println("Erreur");
		}
		return scene;
	}

}
