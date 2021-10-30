/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Antecedant;
import Entities.Patient;
import Entities.Sante;
import Service.Service;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriptionController implements Initializable {

    private final Controller ctrl=new Controller();
    private final Service service= new Service();
    private ObservableList obAntecedant;
    private final ObservableList obTableAntecedant=FXCollections.observableArrayList();
    private List<Antecedant> choices=new ArrayList();
    
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirmPass;
    @FXML
    private TextField txtCode;
    @FXML
    private ComboBox<Antecedant> cbAntecedants;
    @FXML
    private Text txtMessage;
    @FXML
    private TableView<Antecedant> tblAntecedant;
    @FXML
    private TableColumn<Antecedant, String> tblId;
    @FXML
    private TableColumn<Antecedant, String> tblLibelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCode.setText(ctrl.generateCode());
        txtCode.setDisable(true);
        loadCombo();
        loadTableView();
    }    

    @FXML
    private void handleAddAnte(MouseEvent event) {
        Antecedant antecedant=cbAntecedants.getSelectionModel().getSelectedItem();
        if(!obTableAntecedant.contains(antecedant)){
            choices.add(antecedant);
            obTableAntecedant.add(antecedant);
        }
    }

    @FXML
    private void handleInscription(ActionEvent event) {
        String nom= txtNom.getText().trim();
        String login= txtLogin.getText().trim();
        String pass=txtPassword.getText().trim();
        String passConf=txtConfirmPass.getText().trim();
        if(nom.isEmpty()){
            txtNom.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        if(login.isEmpty()){
            txtLogin.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        if(pass.isEmpty()){
            txtPassword.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        if(passConf.isEmpty()){
            txtConfirmPass.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        if(!ctrl.matchMail(login)){
            txtLogin.clear();
            txtLogin.setPromptText("mail format incorrect");
            txtLogin.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        if(pass.compareTo(passConf)!=0){
            notMatchedPassword();
        }
        if(!nom.isEmpty() && !login.isEmpty() && !pass.isEmpty() && !passConf.isEmpty()){
            System.out.println(pass);
            if(pass.compareTo(passConf)!=0){
                notMatchedPassword();
            }else{
                Patient patient =new Patient(nom, login, pass, txtCode.getText().trim());
                int id=service.creerCompte(patient);
                patient.setId(id);
                if(!choices.isEmpty()){
                    for(Antecedant ante:choices){
                        Sante sante= new Sante(ante, patient);
                        service.setSante(sante);
                    }
                }
                ctrl.render("v_patient");
                txtNom.getScene().getWindow().hide();
            }
        }
        
    }
    
    private void loadCombo(){
        obAntecedant= FXCollections.observableArrayList(service.listeAntecedant());
        cbAntecedants.setItems(obAntecedant);
    }
    
    private void loadTableView(){
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblAntecedant.setItems(obTableAntecedant);
    }

    @FXML
    private void handleVider(ActionEvent event) {
        choices.clear();
        obTableAntecedant.clear();
    }
    
    private void notMatchedPassword(){
        txtPassword.setStyle("-fx-border-color:red");
        txtConfirmPass.setStyle("-fx-border-color:red");
        txtConfirmPass.clear();
        txtConfirmPass.setPromptText("mot de passe non correspondant");
    }

    @FXML
    private void handleConnexion(ActionEvent event) {
        this.ctrl.render("connexion");
        txtCode.getScene().getWindow().hide();
    }
    
}
