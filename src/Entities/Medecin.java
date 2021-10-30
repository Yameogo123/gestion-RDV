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
public class Medecin extends User{
    
    private String statut;
    private String disponibilite;

    public Medecin() {
        this.role="ROLE_MEDECIN";
    }

    public Medecin(int id, String nomComplet, String login, String password) {
        super(id, nomComplet, login, password);
        this.role="ROLE_MEDECIN";
    }

    public Medecin(String nomComplet, String login, String password) {
        super(nomComplet, login, password);
        this.role="ROLE_MEDECIN";
    }

    public Medecin(String statut, String disponibilite, int id, String nomComplet, String login, String password) {
        super(id, nomComplet, login, password);
        this.statut = statut;
        this.disponibilite = disponibilite;
    }

    public Medecin(String statut, String disponibilite, String nomComplet, String login, String password) {
        super(nomComplet, login, password);
        this.statut = statut;
        this.disponibilite = disponibilite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    
    @Override
    public String toString(){
        return "confirmée";
    }
    
    
}
