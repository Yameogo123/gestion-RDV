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
public class Sante {
    
    private Antecedant antecedant;
    private Patient patient;

    public Sante() {
    }

    public Sante(Antecedant antecedant, Patient patient) {
        this.antecedant = antecedant;
        this.patient = patient;
    }

    public Antecedant getAntecedant() {
        return antecedant;
    }

    public void setAntecedant(Antecedant antecedant) {
        this.antecedant = antecedant;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}
