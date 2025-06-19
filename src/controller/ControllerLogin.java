package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.lang.reflect.Method;


public class ControllerLogin {

    @FXML private Button boutonConnexion;
    @FXML private TextField champNomUtilisateur;
    @FXML private PasswordField champMotDePasse;
    @FXML private ToggleButton boutonSecouriste;
    @FXML private ToggleButton boutonAdmin;
    private final ToggleGroup roleGroup = new ToggleGroup();

	@FXML
	private void initialize() {
		boutonAdmin.setToggleGroup(roleGroup);
		boutonSecouriste.setToggleGroup(roleGroup);
		boutonAdmin.setSelected(true); // Sélection par défaut
		changerRole(); // Appliquer le style initial
	}


    @FXML
    private void changerRole() {
        boutonAdmin.setStyle("-fx-background-color: #5f7dfc; -fx-text-fill: white;");
        boutonSecouriste.setStyle("-fx-background-color: #5f7dfc; -fx-text-fill: white;");

        if (boutonAdmin.isSelected()) {
            boutonAdmin.setStyle("-fx-background-color: #2a47c9; -fx-text-fill: white;");
        }
        if (boutonSecouriste.isSelected()) {
            boutonSecouriste.setStyle("-fx-background-color: #2a47c9; -fx-text-fill: white;");
        }
    }

    @FXML
    private void appuyerConnexion() {
        boutonConnexion.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: white;");
    }

    @FXML
    private void relacherConnexion() {
        boutonConnexion.setStyle("-fx-background-color: #3c60f4; -fx-text-fill: white;");
    }
	
	
    @FXML
	private void seConnecter(ActionEvent event) {
		String username = champNomUtilisateur.getText();
		String password = champMotDePasse.getText();

		boolean estAdmin = boutonAdmin.isSelected();
		boolean identifiantsCorrects = true;

		if (identifiantsCorrects) {
			try {
				String fxml;
				String css;
				String nomUtilisateur = "Lucie Chauvin"; // Nom par défaut

				if (estAdmin) {
					fxml = "/fxml/admin/planningAdmin.fxml";
					css = "/css/planningAdmin.css";
				} else {
					fxml = "/fxml/secouriste/planningSecours.fxml";
					css = "/css/planningSecours.css";
				}

				FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
				Parent root = loader.load();

				// Obtenir le contrôleur et lui transmettre les infos
				Object controller = loader.getController();

				Method setNomRoleMethod = controller.getClass().getMethod("setNomRole", String.class, String.class);
				setNomRoleMethod.invoke(controller, nomUtilisateur, estAdmin ? "Administrateur" : "Secouriste");

				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource(css).toExternalForm());

				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur de connexion");
			alert.setHeaderText("Identifiants incorrects");
			alert.showAndWait();
		}
	}


}
