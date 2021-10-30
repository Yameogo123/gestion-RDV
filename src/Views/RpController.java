/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dto.RdvDto;
import Entities.Patient;
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
import javafx.scene.Cursor;
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
public class RpController implements Initializable {

    private final Service service=new Service();
    private final User user=ConnexionController.getConCtrl().getUser();
    private final Controller ctrl= new Controller();
    private ObservableList obList=FXCollections.observableArrayList();
    
    @FXML
    private TableView<RdvDto> tblPrestation;
    @FXML
    private TableColumn<RdvDto, String> tblLibelle;
    @FXML
    private TableColumn<RdvDto, String> tblDate;
    @FXML
    private TableColumn<Patient, String> tblPatient;
    @FXML
    private DatePicker date;
    @FXML
    private Text title;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setText(title.getText()+" "+user.getNomComplet());
        loadTableView(service.listerRDV("PRESTATION"));
    }    

    @FXML
    private void handleWindow(MouseEvent event) {
        RdvDto rd=tblPrestation.getSelectionModel().getSelectedItem();
        popUp("detail", "Détails de la prestation", rd);
        obList.clear();
        loadTableView(service.listerRDV("PRESTATION"));
    }

    @FXML
    private void handleFiltrer(MouseEvent event) {
        String dat=date.getValue().toString();
        System.out.println(date.getValue());
        if(date.getValue()!=null){
            obList.clear();
            loadTableView(service.filtrer(dat, "PRESTATION"));
        }
    }

    @FXML
    private void handleDeconnexion(MouseEvent event) {
        ctrl.Deconnexion(title);
    }
    
    public void loadTableView(List<RdvDto> rdv){
        obList=FXCollections.observableArrayList(rdv);
        tblPrestation.setItems(obList);
        tblLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
    }

    @FXML
    private void handleAllList(ActionEvent event) {
        obList.clear();
        loadTableView(service.listerRDV("PRESTATION"));
    }
    
    private Optional<ButtonType> popUp(String title, String message, RdvDto rdv) {
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        
        //
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //declaration de var fxml
        TextField dt = new TextField();
        TextField libelle = new TextField();
        TextField patient= new TextField();
        Button cancelButt = new Button("Annuler");
        
        dt.setText(rdv.getDate());
        libelle.setText(rdv.getLibelle());
        patient.setText(rdv.getPatient().toString());
        cancelButt.setStyle("-fx-background-color:red");
        cancelButt.setCursor(Cursor.HAND);
        
        //charger le gridPane
        grid.add(new Label(message), 0, 0);
        grid.add(new Label("La date:"), 0, 1);
        grid.add(dt, 1, 1);
        grid.add(new Label("Libelle de la prestation:"), 0, 2);
        grid.add(libelle, 1, 2);
        grid.add(new Label("patient concerné:"), 0, 3);
        grid.add(patient, 1, 3);
        
        //disable all
        libelle.setDisable(true);
        dt.setDisable(true);
        patient.setDisable(true);
 
        grid.add(cancelButt, 1, 4);
        
        //charger le gridPane dans alert
        alert.getDialogPane().setContent(grid);
        
        cancelButt.setOnAction((ActionEvent event)->{
            if(service.annulerRDV(rdv.getId())){
                alert.close();
            }
            
        });
        
        Optional<ButtonType> btn= alert.showAndWait();
        return btn;
        
    }
    
}
