package Views;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.User;
import Service.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class ConnexionController implements Initializable {
    
    private final Controller ctrl=new Controller();
    private final Service service=new Service();
    private User user; 
    private static ConnexionController ConCtrl;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public static ConnexionController getConCtrl() {
        return ConCtrl;
    }
    
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMessage.setVisible(false);
        ConCtrl=this;
    }    

    @FXML
    private void handleAnnuler(ActionEvent event) {
        txtLogin.clear();
        txtPassword.clear();
    }

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login=txtLogin.getText().trim();
        String password=txtPassword.getText().trim();
        if(login.isEmpty()){
            txtLogin.setStyle("-fx-border-color:red");
        }
        if(password.isEmpty()){
            this.txtPassword.setStyle("-fx-border-color:red");
        }
        if(!ctrl.matchMail(login)){
            txtLogin.clear();
            txtLogin.setPromptText("mail format incorrect");
            txtLogin.setStyle("-fx-border-color:red");
            txtMessage.setVisible(true);
        }
        this.user=service.seConnecter(login, password);
        if(user==null){
            this.txtMessage.setVisible(true);
        }else{
            if(null!=user.getRole())switch (user.getRole()) {
                case "ROLE_RP":
                    this.ctrl.render("v_rp");
                    break;
                case "ROLE_SECRETAIRE":
                    this.ctrl.render("v_secretaire");
                    break;
                case "ROLE_PATIENT":
                    this.ctrl.render("v_patient");
                    break;
                case "ROLE_MEDECIN":
                    this.ctrl.render("v_medecin");
                    break;
                default:
                    break;
            }
            this.txtLogin.getScene().getWindow().hide();
        }  
    }

    @FXML
    private void handleCreation(ActionEvent event) {
        this.ctrl.render("inscription");
        this.txtLogin.getScene().getWindow().hide();
    }
    
}
