package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class ControllerDispoSecours {

    @FXML private Button boutonSeDeconnecter;
    @FXML private Button boutonPlannig;
    @FXML private Button boutonDispo;
    @FXML private GridPane calendarGrid;
    @FXML private Label labelMois;
    @FXML private Label labelNomUtilisateur;
    @FXML private Label labelRole;

    private LocalDate currentDate = LocalDate.now();
    private Map<Button, EtatJour> etatBoutons = new HashMap<>();

    private enum EtatJour {
        NEUTRE, DISPO, INDISPO
    }

    @FXML
    private void initialize() {
        labelNomUtilisateur.setText("Rachel Smith");
        labelRole.setText("Secouriste");
        updateMois(currentDate);
    }

    public void setNomRole(String nom, String role) {
        labelNomUtilisateur.setText(nom);
        labelRole.setText(role);
    }

    private void updateMois(LocalDate date) {
        currentDate = date;
        String mois = date.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        labelMois.setText(mois.substring(0, 1).toUpperCase() + mois.substring(1));
    }

    @FXML
    private void changerEtatJour(ActionEvent event) {
        Button jour = (Button) event.getSource();
        jour.getStyleClass().removeAll("jour-neutre", "jour-dispo", "jour-indispo");

        if (!etatBoutons.containsKey(jour)) {
            etatBoutons.put(jour, EtatJour.NEUTRE);
        }

        EtatJour etatActuel = etatBoutons.get(jour);

        if (etatActuel == EtatJour.NEUTRE) {
            jour.getStyleClass().add("jour-dispo");
            etatBoutons.put(jour, EtatJour.DISPO);
        } else {
            if (etatActuel == EtatJour.DISPO) {
                jour.getStyleClass().add("jour-indispo");
                etatBoutons.put(jour, EtatJour.INDISPO);
            } else {
                jour.getStyleClass().add("jour-neutre");
                etatBoutons.put(jour, EtatJour.NEUTRE);
            }
        }
    }

    @FXML
    private void setPlanning(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secouriste/planningSecours.fxml"));
            Parent root = loader.load();
            ControllerPlanningSecours controller = loader.getController();
            controller.setNomRole(labelNomUtilisateur.getText(), labelRole.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/planningSecours.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setDispo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secouriste/dispoSecours.fxml"));
            Parent root = loader.load();
            ControllerDispoSecours controller = loader.getController();
            controller.setNomRole(labelNomUtilisateur.getText(), labelRole.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/dispoSecours.css").toExternalForm());

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

    @FXML
    private void precedentMois(ActionEvent event) {
        currentDate = currentDate.minusMonths(1);
        updateMois(currentDate);
    }

    @FXML
    private void suivantMois(ActionEvent event) {
        currentDate = currentDate.plusMonths(1);
        updateMois(currentDate);
    }

    public void setNom(String nom) {
        labelNomUtilisateur.setText(nom);
    }
}
