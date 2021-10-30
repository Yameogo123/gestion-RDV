/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public interface IController {
    public void render(String location);
    public String generateCode();
    public boolean matchMail(String mail);
    public boolean isDate(String date);
    public void alertError(String title, String message);
    public Optional<ButtonType> alertConfirmation(String title, String message);
    public Optional<String> alertChoice(String title, String message);
    public void alertInformation(String title, String message);
    public void Deconnexion(Text value);
    
}
