package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class ControllerPlanningAdmin {

    @FXML private Button boutonSeDeconnecter;
    @FXML private Button boutonPlanning;
    @FXML private Button boutonDPS;
    @FXML private Button boutonInfos;
    @FXML private Button boutonAdd;
    @FXML private GridPane planningGrid;
    @FXML private DatePicker calendar;
    @FXML private Label labelMois;
    @FXML private Label labelSemaine;
    
	@FXML private Label labelRole;
    @FXML private Label labelNomUtilisateur;


    private LocalDate currentDate = LocalDate.now();
    
    @FXML
    public void initialize() {
        updatePlanning(currentDate);
        calendar.setValue(currentDate);
        calendar.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) updatePlanning(newVal);
        });
    }
    
    public void setNomRole(String nom, String role) {
        labelNomUtilisateur.setText(nom);
        labelRole.setText(role);
    }

    
    private void updatePlanning(LocalDate date) {
        currentDate = date;

        String mois = date.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        labelMois.setText(mois.substring(0, 1).toUpperCase() + mois.substring(1));

        WeekFields wf = WeekFields.of(Locale.FRANCE);
        int semaine = date.get(wf.weekOfWeekBasedYear());
        labelSemaine.setText("Semaine " + semaine);

        LocalDate lundi = date.with(DayOfWeek.MONDAY);
        for (int i = 0; i < 7; i++) {
            LocalDate jour = lundi.plusDays(i);
            String texte = jour.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH)
                           + " " + jour.getDayOfMonth();
            Label labelJour = getJourLabel(i);
            if (labelJour != null) labelJour.setText(texte);
        }
    }

    private Label getJourLabel(int index) {
        for (javafx.scene.Node node : planningGrid.getChildren()) {
            if (node instanceof Label && GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == index + 1) {
                return (Label) node;
            }
        }
        return null;
    }
   
    @FXML
    private void precedentSemaine() {
        updatePlanning(currentDate.minusWeeks(1));
        calendar.setValue(currentDate);
    }

    @FXML
    private void suivantSemaine() {
        updatePlanning(currentDate.plusWeeks(1));
        calendar.setValue(currentDate);
    }
    
	 @FXML
    private void setPlanning(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secouriste/planningAdmin.fxml"));
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
    private void setSecours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secouriste/secouristesAdmin.fxml"));
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
    private void ajoutAffectation(ActionEvent event) {
        System.out.println("Ajout d'une affectation");
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
