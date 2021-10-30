/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dto.RdvDto;
import Entities.Medecin;
import Entities.Patient;
import Entities.Rdv;
import Entities.User;
import Service.Service;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PatientController implements Initializable {

    private final User user= ConnexionController.getConCtrl().getUser();
    private final Service service= new Service();
    private final Controller ctrl=new Controller();
    private ObservableList obList=FXCollections.observableArrayList();
    private final Patient patient= service.getPatient(user.getId());
    private static Optional<String> result;
    
    @FXML
    private TableView<Rdv> tblRdv;
    @FXML
    private TableColumn<Rdv, String> tblType;
    @FXML
    private TableColumn<Rdv, String> tblDate;
    @FXML
    private TableColumn<Medecin, String> tblValidite;
    @FXML
    private Text txtMessage;
    @FXML
    private TableColumn<Rdv, String> tblLibelle;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setText("Bienvenue "+user.getNomComplet());
        loadTableView(service.getDossierMedical(patient));
    }    

    @FXML
    private void handleDeconnexion(MouseEvent event) {
        ctrl.Deconnexion(txtMessage);
    }

    @FXML
    private void handleDemandeRdv(ActionEvent event) {
        result=ctrl.alertChoice("type de rendez-vous","choisir le type de rendez-vous");
        popUp("rendez-vous", "Faites votre demande de rendez-vous", result.get());
        loadTableView(service.getDossierMedical(patient));
    }
    
    public void loadTableView(List<RdvDto> rdv){
        obList=FXCollections.observableArrayList(rdv);
        tblRdv.setItems(obList);
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblValidite.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    }
    
   
    private Optional<ButtonType> popUp(String title, String message, String resultat) {
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        
        //
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //declaration de var fxml
        DatePicker date = new DatePicker();
        TextField libelle = new TextField();
        Button saveButt = new Button("Save");
        Button cancelButt = new Button("Cancel");
        Text txt=new Text();
        
        date.setPromptText("date du rdv");
        libelle.setPromptText("libelle prestation");
        saveButt.setStyle("-fx-background-color:green");
        cancelButt.setStyle("-fx-background-color:red");
        txt.setText("affectation reussie");
        txt.setVisible(false);
        
        //charger le gridPane
        grid.add(new Label(message), 0, 0);
        grid.add(new Label("La date:"), 0, 1);
        grid.add(date, 1, 1);
        if(resultat.compareToIgnoreCase("PRESTATION")==0){
            grid.add(new Label("Libelle de la prestation:"), 0, 2);
            grid.add(libelle, 1, 2);
        }
        
        grid.add(saveButt, 1, 3);
        grid.add(cancelButt, 2, 3);
        grid.add(txt, 1, 4);
        
        //charger le gridPane dans alert
        alert.getDialogPane().setContent(grid);
        
        //action du button save
        saveButt.setOnAction((ActionEvent event) -> {
            String lib=libelle.getText().trim();
            String dat=date.getValue().toString();
            RdvDto rdv= new RdvDto();
            rdv.setPatient(patient);
            rdv.setDate(dat);
            if(resultat.compareToIgnoreCase("CONSULTATION")==0 && !dat.isEmpty()){
                rdv.setType("CONSULTATION");
            }
            if(resultat.compareToIgnoreCase("PRESTATION")==0 && !dat.isEmpty()&& !lib.isEmpty()){
                rdv.setType("PRESTATION");
                rdv.setLibelle(lib);
            }
            if(lib.isEmpty()){
                libelle.setStyle("-fx-border-color:red");
            }
            if(dat.isEmpty()){
                date.setStyle("-fx-border-color:red");
            }
            
            service.setRdv(rdv);
            ctrl.alertInformation("rdv", "un rendez-vous demandé");
        });
        
        cancelButt.setOnAction((ActionEvent event)->{
            libelle.clear();
        });
        
        Optional<ButtonType> btn= alert.showAndWait();
        return btn;
        
    }
}
