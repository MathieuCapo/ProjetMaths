package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


public class Graphe {
	
	private static String nomFich;
	
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

	public static Stage graphe() {
		Stage stage = new Stage();
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<Number,Number> lineChart = 
				new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setCreateSymbols(false);
		Scene scene  = new Scene(lineChart,800,600);
		stage.setScene(scene);
		stage.show();
		
		showInputTextDialog();
		
		stage.setTitle("Courbe simulation");
		//defining the axes
		
		xAxis.setLabel("Nombre de point");

		lineChart.setTitle("Graphe simulation");
		//defining a series
		XYChart.Series series = new XYChart.Series();
		series.setName("Simulation");
		String ligne;
		ArrayList<Double> resultats = new ArrayList<Double>();
		ArrayList<Double> valeurs = new ArrayList<Double>();
		int compteur = 0;
		String reggexUniforme = "resultatUniforme[0-9]+.csv";
		String reggexDiscrete = "resultatDiscrete[0-9]+.csv";
		String reggexExponentielle = "resultatExponentielle[0-9]+.csv";
		int x;
		
		try(BufferedReader lect= new BufferedReader(new FileReader(nomFich))) {
			if(nomFich.matches(reggexUniforme)) {
				lineChart.setCreateSymbols(true);
				while((ligne = lect.readLine()) != null) {
					 x = 1;
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
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(valeurs.get(i), resultats.get(i)));
				}
				lineChart.setCreateSymbols(true);
				
			} else if(nomFich.matches(reggexDiscrete)) {
				while((ligne = lect.readLine()) != null) {
					 x = 1;
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
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(valeurs.get(i), resultats.get(i)));
				}
				lineChart.setCreateSymbols(true);
				
				
			/*else if(nomFich.matches(reggexExponentielle)) {
				while((ligne = lect.readLine()) != null) {
					resultats.add(Double.parseDouble(ligne.substring(0,ligne.length()-1)));
					compteur++;
				}
				int compteurFin = resultats.size()-1;
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(i, resultats.get(compteurFin)));
					compteurFin--;
				}*/
			} else {
				while((ligne = lect.readLine()) != null) {
					resultats.add(Double.parseDouble(ligne.substring(0,ligne.length()-1)));
					compteur++;
				}
				for(int i = 0; i < resultats.size(); i++) {
					series.getData().add(new XYChart.Data(i, resultats.get(i)));
				}
			}
			lineChart.getData().add(series);
			return stage;
		} catch(IOException e) {
			System.err.println("Erreur");
		}
		return null;
	}
}

