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
public class Rp extends User{

    public Rp() {
        this.role="ROLE_RP";
    }

    public Rp(int id, String nomComplet, String login, String password) {
        super(id, nomComplet, login, password);
        this.role="ROLE_RP";
    }

    public Rp(String nomComplet, String login, String password) {
        super(nomComplet, login, password);
        this.role="ROLE_RP";
    }
    
}
