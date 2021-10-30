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
public class Diagnostic {
    private int id;
    private Constante constante;
    private Consultation consultation;

    public Diagnostic() {
    }

    public Diagnostic(int id, Constante constante, Consultation consultation) {
        this.id = id;
        this.constante = constante;
        this.consultation = consultation;
    }

    public Diagnostic(Constante constante, Consultation consultation) {
        this.constante = constante;
        this.consultation = consultation;
    }

    public Constante getConstante() {
        return constante;
    }

    public void setConstante(Constante constante) {
        this.constante = constante;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
