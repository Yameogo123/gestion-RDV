/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dto.RdvDto;
import Entities.*;
import Service.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Controller implements IController{
    
    private final Service service =new Service();

    @Override
    public void render(String location) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/"+location+".fxml"));
            
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String generateCode() {
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb=new StringBuilder();
        Random random= new Random();
        int lenght=4;
        for(int i=1; i<lenght; i++){
            int index=random.nextInt(alphabet.length());
            char caractere= alphabet.charAt(index);
            sb.append(caractere);
        }
        return String.valueOf(sb);
    }

    @Override
    public boolean matchMail(String mail) {
        return mail.matches(".+@.+\\..+");
    }

    @Override
    public void alertError(String title, String message) {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void alertInformation(String title, String message) {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public Optional<ButtonType> alertConfirmation(String title, String message) {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result=alert.showAndWait();
        return result;
    }

    @Override
    public void Deconnexion(Text txt) {
        this.render("connexion");
        ConnexionController.getConCtrl().setUser(null);
        txt.getScene().getWindow().hide();
    }

    @Override
    public boolean isDate(String date) {
        return date.matches("[0-3][0-9]/[0-1][0-9]/[0-9]{4}") || date.matches("[0-3][0-9]-[0-1][0-9]-[0-9]{4}");
    }

    @Override
    public Optional<String> alertChoice(String title, String message) {
        List<String> choices = new ArrayList<>();
        choices.add("Prestation");
        choices.add("Consultation");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Consultation", choices);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        return result;
    }
    
    
    //popUp for affectation
    public Optional<ButtonType> popUp(String title, String message, List<RdvDto> cons) {
        ObservableList ob=FXCollections.observableArrayList(cons);
        //List<String> choices = new ArrayList<>();

        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        //
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //declaration de var fxml
        TextField libelle = new TextField();
        TextField valeur = new TextField();
        Button saveButt = new Button("Save");
        Button cancelButt = new Button("Cancel");
        ComboBox<Rdv>cb= new ComboBox();
        Text txt=new Text();
        
        libelle.setPromptText("Libelle (ex: temperature)");
        valeur.setPromptText("Valeur");
        cb.setItems(ob);
        saveButt.setStyle("-fx-background-color:green");
        cancelButt.setStyle("-fx-background-color:red");
        txt.setText("affectation reussie");
        txt.setVisible(false);
        
        //charger le gridPane
        grid.add(new Label(message), 0, 0);
        grid.add(cb, 1, 0);
        grid.add(new Label("Libelle de la constante:"), 0, 1);
        grid.add(libelle, 1, 1);
        grid.add(new Label("Sa valeur:"), 0, 2);
        grid.add(valeur, 1, 2);
        grid.add(saveButt, 1, 3);
        grid.add(cancelButt, 2, 3);
        grid.add(txt, 1, 4);
        
        //charger le gridPane dans alert
        alert.getDialogPane().setContent(grid);
        
        //action du button save
        saveButt.setOnAction((ActionEvent event) -> {
            String lib=libelle.getText().trim();
            String val=valeur.getText().trim();
            Rdv rdv= cb.getSelectionModel().getSelectedItem();
            if(rdv!=null && !lib.isEmpty() && !val.isEmpty()){
                Constante constante=new Constante(lib,val);
                int id=service.addConstante(constante);
                constante.setId(id);
                Consultation consul=service.findConsultation(rdv.getId());
                Diagnostic diagnostic= new Diagnostic(constante, consul);
                int id1= service.setDiagnostic(diagnostic);
                diagnostic.setId(id1);
            }
            if(lib.isEmpty()){
                libelle.setStyle("-fx-border-color:red");
            }
            if(val.isEmpty()){
                valeur.setStyle("-fx-border-color:red");
            }
            if(rdv==null){
                cb.setStyle("-fx-border-color:red");
            }
            
            this.alertInformation("affectation", "une affectation réussie");
        });
        
        cancelButt.setOnAction((ActionEvent event)->{
            libelle.clear();
            valeur.clear();
        });

        // Traditional way to get the response value.
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
    
    //pop up for dossier medical
    public Optional<Patient> popUp1(String title, String message, List<Patient> patients) {
        ObservableList ob=FXCollections.observableArrayList(patients);

        ChoiceDialog<Patient> dialog = new ChoiceDialog<>("", ob);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        // Traditional way to get the response value.
        Optional<Patient> result = dialog.showAndWait();
        return result;
    }
    
}
