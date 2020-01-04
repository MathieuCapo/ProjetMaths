package application.outils;

import java.util.ArrayList;

public class OutilsInterface {

	public static Double[] convertirTableau(ArrayList<Double> valeurs) {
		Double[] tab = new Double[valeurs.size()];
		for(int i = 0; i <valeurs.size(); i++) {
			tab[i] = valeurs.get(i);
		}
		return tab;
	}

	public static Double[][] simulerDiscrete(ArrayList<Double> listValeurs, ArrayList<Double> listProbabilite,Double nbRep) {
		Discrete loi = new Discrete(listValeurs,listProbabilite);
		return loi.simuler(nbRep);
	}

	public static Double[] simulerNormale(double esperance, double ecartType, Double repetition) {
		Normale loi = new Normale(esperance, ecartType);
		return loi.simuler(repetition);
	}

	public static Double[] simulerExponentielle(double lambda, Double repetition) {
		Exponentielle loi = new Exponentielle(lambda);
		return loi.simuler(repetition);
	}

	public static Double[] simulerBinomiale(double nbRep, double proba, Double repetition) {
		//TODO
		return null;
	}

	public static Double[][] simulerUniforme(ArrayList<Double> listValeurs, Double repetition) {
		Double[] tab = convertirTableau(listValeurs);
		Uniforme loi = new Uniforme(tab);
		return loi.simuler(repetition);
	}
}
