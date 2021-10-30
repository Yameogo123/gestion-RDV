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
public class Prestation extends Rdv{
    private String libelle;
    private Consultation consultation;

    public Prestation(String libelle, Consultation consultation) {
        this.libelle = libelle;
        this.consultation = consultation;
        this.type="PRESTATION";
    }

    public Prestation() {
        this.type="PRESTATION";
    }

    public Prestation(String libelle, Consultation consultation, int id, String date,Patient patient) {
        super(id, date, patient);
        this.libelle = libelle;
        this.consultation = consultation;
        this.type="PRESTATION";
    }

    public Prestation(String libelle, Consultation consultation, String date,Patient patient) {
        super(date, patient);
        this.libelle = libelle;
        this.consultation = consultation;
        this.type="PRESTATION";
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
    
}
