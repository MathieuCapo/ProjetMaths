package application.outils;

import java.lang.Math;

public class Normale {

		private double esperance;
		
		private double ecartType;
		
		public Normale(double esperance,double ecartType) {
			this.esperance = esperance;
			this.ecartType = ecartType;
		}
		
		public Double[] simuler(Double nbRepetition) {
			Double[] resultat = new Double[nbRepetition.intValue()]; // TODO remplacer par variable globale
			Double esperanceObt = 0.0;
			for (int i = 0; i < nbRepetition; i++) {
				resultat[i] = tirageAleatoire();
			}
			for (int i=0; i < nbRepetition; i++) {
				esperanceObt += resultat[i];
			}
			System.out.println(esperanceObt/nbRepetition + "--> doit etre egale a :" + esperance);
			return resultat;
		}
                
		/**
		 * 
		 * @return RACINE(-2*LN(R1))*COS(2*PI()*S1)*ecartType+esperance
		 */
		public double tirageAleatoire() {
			return Math.sqrt(-2*Math.log(Math.random()))*Math.cos(2 * Math.PI * Math.random()) * ecartType + esperance;
		}
		
		
}
