package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	static Scene scene;
	
	static Stage page;
	
	
	/**
	 * Permet de lancer l'application
	 */
	public void start(Stage primaryStage) {
		try {
			page = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("appli.fxml"));
			page.setTitle("LOI MATHS");
			scene = new Scene(root,600,400);
			page.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			page.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scn) {
		scene = scn;
		majInterface();
	}

	private static void majInterface() {
		page.setScene(scene);
		page.setTitle("Courbe simulation");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
