package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import model.dao.DAODPS;
import model.persistence.DPS;
import javafx.scene.layout.GridPane;



import java.util.List;

public class ControllerDPSAdmin {
	
	@FXML private Button boutonSeDeconnecter;
    @FXML private Button boutonPlanning;
    @FXML private Button boutonDPS;
    @FXML private Button boutonInfos;
    @FXML private Button boutonAdd;
    @FXML private Label labelNomUtilisateur;
    @FXML private Label labelRole;
    @FXML private GridPane dpsGrid;


    public void setNomRole(String nom, String role) {
		labelNomUtilisateur.setText(nom);
		labelRole.setText(role);
		remplirGrilleDPS();
	}

    private void remplirGrilleDPS() {
		dpsGrid.getChildren().clear();

		// Ajout de l’en-tête
		dpsGrid.add(new Label("Événement"), 0, 0);
		dpsGrid.add(new Label("Date"), 1, 0);
		dpsGrid.add(new Label("Heure début"), 2, 0);
		dpsGrid.add(new Label("Heure fin"), 3, 0);
		dpsGrid.add(new Label("Lieu"), 4, 0);
		dpsGrid.add(new Label("Compétence requise"), 5, 0);

		List<DPS> dpsList = new DAODPS().findAll();

		int row = 1;
		for (DPS dps : dpsList) {
			String evenement = dps.getSport() != null ? dps.getSport().getNom() : "N/A";
			String date = dps.getJournee() != null ? dps.getJournee().toString() : "N/A";
			String heureDebut = String.format("%02dh%02d", dps.getHoraireDep() / 100, dps.getHoraireDep() % 100);
			String heureFin = String.format("%02dh%02d", dps.getHoraireFin() / 100, dps.getHoraireFin() % 100);
			String lieu = dps.getSite() != null ? dps.getSite().getNom() : "N/A";
			//String competence = dps.getSport() != null ? dps.getSport().getCompetenceRequise() : "N/A";

			dpsGrid.add(new Label(evenement), 0, row);
			dpsGrid.add(new Label(date), 1, row);
			dpsGrid.add(new Label(heureDebut), 2, row);
			dpsGrid.add(new Label(heureFin), 3, row);
			dpsGrid.add(new Label(lieu), 4, row);
			//dpsGrid.add(new Label(competence), 5, row);

			row++;
		}
	}	



    @FXML
    private void ajouterDps(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/popups/popupAjouterDPS.fxml"));
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.setScene(new Scene(root));
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Ajouter un DPS");
            popup.showAndWait();

            remplirGrilleDPS();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setPlanning(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/planningAdmin.fxml"));
            Parent root = loader.load();
            ControllerPlanningAdmin controller = loader.getController();
            controller.setNomRole(labelNomUtilisateur.getText(), labelRole.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/planningAdmin.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setDPS(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/dpsAdmin.fxml"));
            Parent root = loader.load();
            ControllerDPSAdmin controller = loader.getController();
            controller.setNomRole(labelNomUtilisateur.getText(), labelRole.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/planningAdmin.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setSecours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/secouristesAdmin.fxml"));
            Parent root = loader.load();
            ControllerSecouristesAdmin controller = loader.getController();
            controller.setNomRole(labelNomUtilisateur.getText(), labelRole.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/secouristesAdmin.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seDeconnecter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
