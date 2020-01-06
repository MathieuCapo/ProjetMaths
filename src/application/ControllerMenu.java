package application;

import java.util.ArrayList;
import java.util.Optional;

import application.outils.Binomiale;
import application.outils.Discrete;
import application.outils.Exponentielle;
import application.outils.Graphique;
import application.outils.Normale;
import application.outils.OutilsInterface;
import application.outils.TraitementFichier;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerMenu {

	@FXML
	private AnchorPane ap;
	
	@FXML
	private Label lbEsperance,probabilite,lbLamda,lbVariance,lbLoi,lbValeur,lbNbRepetition,lbNbRepLoi;

	@FXML
	private TextField premiereEntre,nbRepetition,deuxiemeEntre;

	@FXML
	private Button btnSimuler,btnAjouter;

	@FXML
	private ComboBox<String> choixLoi;
	
	/**
	 * Liste des valeurs possibles 
	 * Necessaire pour les lois Discrete
	 */
	ArrayList<Double> listValeurs = new ArrayList<Double>();
	
	/**
	 * Liste des probabilites
	 */
	ArrayList<Double> listProbabilite = new ArrayList<Double>();
	
	/**
	 * Tableau contenant les resulats des variables aleatoires simuler
	 * pour la loi choisie
	 * Pour certaines lois il est necessaire d'utiliser un compteur
	 * il est donc necessaire d'utiliser un tableau a 2 dimensions
	 */
	ArrayList<Double[][]> resultat2D = new ArrayList<Double[][]>();
	
	/**
	 * Tableau contenant les resulats des variables aleatoires simuler
	 * pour la loi choisie
	 */
	ArrayList<Double[]> resultat = new ArrayList<Double[]>();
	
	/**
	 * Nombre de repetition
	 */
	Double repetition;
	
	/**
	 * Nom de la loi
	 */
	String loi;
	


	/**
	 * Options de lois offertes a l'utilisateur
	 */
	 ObservableList<String> options = 
			 FXCollections.observableArrayList(
					 "Normale",
					 "Exponentielle",
					 "Binomiale",
					 "Discrete",
					 "Uniforme"
					 );

	 @FXML
	 /**
	  * Fait apparaitre les elements de l'interface en fonction de la loi choisie
	  * @param event
	  */
	 void changerInterface(ActionEvent event) {
		 if(choixLoi.getValue().toString().equals("Normale")){
			 toutCacher();
			 lbVariance.setVisible(true);
			 premiereEntre.setVisible(true);
			 lbEsperance.setVisible(true);
			 deuxiemeEntre.setVisible(true);
		 } else if(choixLoi.getValue().toString().equals("Exponentielle")) {
			 toutCacher();
			 lbLamda.setVisible(true);
			 premiereEntre.setVisible(true);
		 } else if(choixLoi.getValue().toString().equals("Binomiale")) {
			 toutCacher();
			 lbNbRepLoi.setVisible(true);
			 premiereEntre.setVisible(true);
			 deuxiemeEntre.setVisible(true);
			 probabilite.setVisible(true);
		 } else if(choixLoi.getValue().toString().equals("Discrete")) {
			 toutCacher();
			 lbValeur.setVisible(true);
			 premiereEntre.setVisible(true);
			 deuxiemeEntre.setVisible(true);
			 probabilite.setVisible(true);
			 btnAjouter.setVisible(true);
		 } else if(choixLoi.getValue().toString().equals("Uniforme")) {
			 toutCacher();
			 lbValeur.setVisible(true);
			 premiereEntre.setVisible(true);
			 btnAjouter.setVisible(true);
		 }
	 }

	 @FXML
	 /**
	  * Cache tous les elements lies aux lois
	  */
	 private void toutCacher() {
		 lbVariance.setVisible(false);
		 lbEsperance.setVisible(false);
		 lbLamda.setVisible(false);
		 premiereEntre.setVisible(false); 
		 deuxiemeEntre.setVisible(false);
		 btnAjouter.setVisible(false);
		 probabilite.setVisible(false);
		 lbValeur.setVisible(false);
		 lbNbRepLoi.setVisible(false); 
	 }

	 /**
	  * A l'initialisation configure les choix de la combobox
	  * permettant a l'utilisateur de choisir sa loi
	  */
	 public void initialize() {
		 choixLoi.setItems(options);
		 toutCacher();
	 }
	 
	 /**
	  * Simule la loi en fonction des entrees de l'utilisateur
	  * Recupere le nombre de repetition et le nom de la loi
	  * Puis la fonction selon le nom execute les verifications 
	  * puis appel la fonction qui simule la loi et ajoute les resultats 
	  * a la liste des resultats
	  * @param event
	  */
	 @FXML
	 void simulerLoi(ActionEvent event) {
		 //TODO ajouter verif, assez de valeurs, nbDerepetition entre ok etc valeurs ok pas de lettres etc
		 loi = choixLoi.getValue().toString();
		 // si le nombre de r�p�tition n'est pas valide on affiche le message d'erreur du default
		 try {
			 repetition = Double.parseDouble(nbRepetition.getText());
		 } catch(NumberFormatException e) {
			 loi = "invalide";
		}
		// si le champ ne peut pas �tre un nombre 
		 if (!OutilsInterface.canBeNumber(premiereEntre.getText())) {
			 // on affiche le default du switch 
			 loi = "invalide";
		}
		
		 switch(loi) {
			 case "Exponentielle":
				 Double val = Double.parseDouble(premiereEntre.getText());
				 // v�rif si sup�rieur � 0
				 if (OutilsInterface.verifValeurPos(val)) {
					 resultat.add(OutilsInterface.simulerExponentielle(val,repetition));
				 } else {
					 ErreurSaisie();
				 }
				 break;
				 
			 case "Normale": 
				 Double val1 = Double.parseDouble(premiereEntre.getText());
				 Double val2 = Double.parseDouble(deuxiemeEntre.getText());
				 // les deux valeurs doivent �tre sup�rieur � 0 
				 if (OutilsInterface.verifValeurPos(val1) && OutilsInterface.verifValeurPos(val2))  {
					 resultat.add(OutilsInterface.simulerNormale(val1,val2,repetition));
				 } else {
					 ErreurSaisie();
				 }
				 break;
				 
			 case "Binomiale":
				 Double valeur1 = Double.parseDouble(premiereEntre.getText()); // nb de rep�tition
				 Double valeur2 = Double.parseDouble(deuxiemeEntre.getText()); // probabilit� (>=0 <=1)
				 if (OutilsInterface.verifValeurPos(valeur1) && OutilsInterface.verifValeurIntervalle(0.0,1.0,valeur2)) {
					 resultat.add(OutilsInterface.simulerBinomiale(valeur1, valeur2,repetition));
				 } else {
					 ErreurSaisie();
				 }	
				 break;
				 
			 case "Discrete":
				 resultat2D.add(OutilsInterface.simulerDiscrete(listValeurs,listProbabilite,repetition));	 
				 break;
				 
			 case "Uniforme":
				 resultat2D.add(OutilsInterface.simulerUniforme(listValeurs,repetition));
				 break;
				 
			 case "invalide":
				 ErreurSaisie();
				 break;
				 
			 default: 
				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("ERREUR: Choix loi");
				 alert.setHeaderText("Choix loi incorrect");
				 alert.setContentText("Choisissez une loi pour simuler");
				 alert.show();
		 }
		 autreSimulation();
		 
	 }
	 
	 /**
	  * Demande a l'utilisateur s'il souhaite realiser une autre simulation
	  * Si c'est le cas ferme la boite de dialog
	  * Sinon enrtegistre la liste des resultats
	  */
	 public void autreSimulation() {
		 //Fait disparaitre les elements lies au choix de la loi
		 choixLoi.setVisible(false);
		 lbLoi.setVisible(false);
		 Alert alert = new Alert(AlertType.CONFIRMATION);
		 alert.setTitle("CONFIRMATION : Arret simulation");
		 alert.setHeaderText("ARRETER SIMULATION");
		 alert.setContentText("Arreter de simuler et enregistrer ?");

		 Optional<ButtonType> result = alert.showAndWait();
		 /* Si l'utilisateur veut enregister */
		 if (result.get() == ButtonType.OK){
			 switch(loi) {
				 case "Exponentielle":
					 TraitementFichier.save(resultat, "Exponentielle");
					 break;
					 
				 case "Normale":
					 TraitementFichier.save(resultat, "Normale");
					 break;
					 
				 case "Binomiale":
					 TraitementFichier.save(resultat, "Binomiale");
					 break;	
					 
				 case "Discrete":
					 TraitementFichier.saveTab2D(resultat2D, "Discrete");
					 break;
					 
				 case "Uniforme":
					 TraitementFichier.saveTab2D(resultat2D, "Uniforme");
					 break;
			 }
			 Main.setScene(Graphique.graphe());
			 //Platform.exit();
		 } else {
			 choixLoi.setVisible(true);
			 lbLoi.setVisible(true);
		 }
	 }
	 
	public void ErreurSaisie() {
		Alert invalide = new Alert(AlertType.INFORMATION);
		 invalide.setTitle("ERREUR: Valeur incorrecte");
		 invalide.setHeaderText("Choix valeur incorrect");
		 invalide.setContentText("V�rifier que les param�tres soient correctes pour cette loi,"
		 		+ "\nles probabilt�s sont sous forme (0.x...)");
		 invalide.show();
	}

	@FXML
	 void ajouterVal(ActionEvent event) {
		 choixLoi.setVisible(false);
		 lbLoi.setVisible(false);
		 // TODO v�rif premiere entree
		 System.out.println(" AJOUT " + premiereEntre.getText());
		 if(choixLoi.getValue().toString().equals("Discrete")) {
			 //TODO v�rif 2�me entr�e
			 listValeurs.add(Double.parseDouble(premiereEntre.getText()));
			 listProbabilite.add(Double.parseDouble(deuxiemeEntre.getText()));
		 } else {
			 listValeurs.add(Double.parseDouble(premiereEntre.getText()));
		 }
	 }
}
