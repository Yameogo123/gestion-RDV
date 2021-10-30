/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class Secretaire extends User{

    public Secretaire() {
        this.role="ROLE_SECRETAIRE";
    }

    public Secretaire(int id, String nomComplet, String login, String password) {
        super(id, nomComplet, login, password);
        this.role="ROLE_SECRETAIRE";
    }

    public Secretaire(String nomComplet, String login, String password) {
        super(nomComplet, login, password);
        this.role="ROLE_SECRETAIRE";
    }
    
    
    
    
}
