/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Dto.RdvDto;
import Entities.Patient;
import Entities.Rdv;
import Entities.User;
import Service.Service;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MedecinController implements Initializable {
    
    private final User user= ConnexionController.getConCtrl().getUser();
    private final Service service= new Service();
    private ObservableList obList=FXCollections.observableArrayList();
    private final Controller ctrl=new Controller();
    private static Optional<String> result;

    @FXML
    private FontAwesomeIcon openMenu;
    @FXML
    private FontAwesomeIcon closeMenu;
    @FXML
    private AnchorPane menu;
    @FXML
    private AnchorPane anchorView;
    @FXML
    private TableView<Rdv> tblRDV;
    @FXML
    private TableColumn<Rdv, String> tblType;
    @FXML
    private TableColumn<Rdv, String> tblDate;
    @FXML
    private TableColumn<Patient, String> tblPatient;
    @FXML
    private DatePicker recherche;
    @FXML
    private Text txt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt.setText("Bienvenue "+user.getNomComplet());
        loadTableView(service.listerAllRDVOfMedecin(this.user.getId()));
    }    

    @FXML
    private void handleOpenMenu(MouseEvent event) {
        openMenu.setVisible(false);
        closeMenu.setVisible(true);
        menu.setVisible(true);
        loadTableView(service.listerAllRDVOfMedecin(this.user.getId()));
        
    }

    @FXML
    private void handleCloseMenu(MouseEvent event) {
        closeMenu.setVisible(false);
        openMenu.setVisible(true);
        menu.setVisible(false);
        loadTableView(service.listerAllRDVOfMedecin(this.user.getId()));
    }

    @FXML
    private void handlePlannifier(ActionEvent event) {
        result=ctrl.alertChoice("type de rendez-vous","choisir le type de rendez-vous");
        try {
            loadView("planRdv");
        } catch (IOException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void handleDeconnexion(ActionEvent event) {
        ctrl.Deconnexion(txt);
    }

    @FXML
    private void handleGetDossier(ActionEvent event) {
        Optional<Patient> op=ctrl.popUp1("dossier médical", "Choisir le patient concerné", service.listerPatient());
        if(op.get()!=null){
            List<RdvDto> rd= service.getDossierMedical(op.get());
            loadTableView(rd);
        }
        System.out.println(op.get().getCode());
    }

    public void loadTableView(List<RdvDto> rdv){
        obList=FXCollections.observableArrayList(rdv);
        tblRDV.setItems(obList);
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
    }

    @FXML
    private void handleFiltrer(MouseEvent event) {
        String date=recherche.getValue().toString();
        if(!((date.trim()).isEmpty())){
            obList.clear();
            loadTableView(service.filtrer(date, "CONSULTATION"));
        }
    }

    @FXML
    private void handleAnnulerConsultation(ActionEvent event) {
        Rdv rdv= tblRDV.getSelectionModel().getSelectedItem();
        if(rdv!=null){
            if((rdv.getType()).compareTo("PRESTATION")==0){
                this.ctrl.alertError("Selection", "Vous ne pouvez annuler que les consultations");
            }else{
                Optional<ButtonType> btn=this.ctrl.alertConfirmation("CONSULTATION", "Confirmer annulation");
                if(btn.get()==ButtonType.OK){
                    service.annulerRDV(rdv.getId());
                    obList.clear();
                    loadTableView(service.listerAllRDVOfMedecin(this.user.getId()));
                }
            }
        }else{
            this.ctrl.alertError("Selection", "Veuillez choisir une consultation");
        }
    }
    
    public void loadView(String view)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("/Views/"+view+".fxml"));
        anchorView.getChildren().clear();
        anchorView.getChildren().add(root);
    }
    
    
    public static Optional<String> getResult() {
        return result;
    }

    @FXML
    private void handleAffecterConst(ActionEvent event) {
        Optional<ButtonType> resultat=ctrl.popUp("constante", "Choisir consultation associée",service.listerRDVOfMedecinByType(user.getId(), "CONSULTATION"));
        if(resultat.get()== ButtonType.OK){
            ctrl.alertInformation("succes", "affectation terminée");
        }
    }
}
