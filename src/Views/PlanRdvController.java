/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.*;
import Service.Service;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PlanRdvController implements Initializable {

    private final Service service=new Service();
    private final User user= ConnexionController.getConCtrl().getUser();
    private final Controller ctrl=new Controller();
    private ObservableList obCons= FXCollections.observableArrayList();
    private ObservableList obP= FXCollections.observableArrayList();
    @FXML
    private DatePicker date;
    @FXML
    private TextField libelle;
    @FXML
    private ComboBox<Patient> cbxPatient;
    @FXML
    private ComboBox<Consultation> cbxConsultations;
    @FXML
    private Text txtErreur;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadComboP();
        if("Consultation".equals(MedecinController.getResult().get())){
            libelle.setDisable(true);
            cbxConsultations.setDisable(true);
        }
    }    

    @FXML
    private void handleAnnuler(ActionEvent event) {
        ctrl.render("v_medecin");
        libelle.getParent().getScene().getWindow().hide();
    }

    @FXML
    private void handlePlan(ActionEvent event) {
        String lib=libelle.getText().trim();
        String dat=date.getValue().toString();
        Patient p= cbxPatient.getSelectionModel().getSelectedItem();
        Medecin med=service.getMedecin(user.getId());
        if(p==null || dat.isEmpty()){
            txtErreur.setVisible(true);
        }else{
            if("Consultation".equals(MedecinController.getResult().get())){
                Consultation cons=new Consultation(med,dat,p);
                service.plannifierConsultation(cons);
                handleAnnuler(event);
                ctrl.alertInformation("rdv", "consultation plannifiée avec succes");
            }else{
                Consultation consul= cbxConsultations.getSelectionModel().getSelectedItem();
                if(lib.isEmpty()){
                    txtErreur.setVisible(true);
                }else{
                    Prestation pres=new Prestation(lib,consul, dat, p);
                    service.plannifierPrestation(pres);
                    handleAnnuler(event);
                    ctrl.alertInformation("rdv", "prestation plannifiée avec succes");
                }
            }
            
        }
        
    }
    
    private void loadComboP(){
        obP= FXCollections.observableArrayList(service.listerPatient());
        cbxPatient.setItems(obP);
    }
    
    private void loadComboC(Patient p){
        //p= cbxPatient.getSelectionModel().getSelectedItem();
        if(p!=null){
            obCons=FXCollections.observableArrayList(service.consultationsParPatient(p.getId()));
            cbxConsultations.setItems(obCons);
        }
    }

    @FXML
    private void getPatient(ActionEvent event) {
        Patient p=cbxPatient.getSelectionModel().getSelectedItem();
        loadComboC(p);
    }

    private void handleAffecterConst(ActionEvent event) {
        Optional<ButtonType> resultat=ctrl.popUp("constante", "Choisir consultation associée",service.listerRDVOfMedecinByType(user.getId(), "CONSULTATION"));
        if(resultat.get()== ButtonType.OK){
            ctrl.alertInformation("succes", "affectation terminée");
        }
    }
    
}
