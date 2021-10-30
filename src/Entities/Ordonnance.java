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
public class Ordonnance {
    
    private int id;
    private String posologie;
    private Consultation consultation;

    public Ordonnance() {
    }

    public Ordonnance(int id, String posologie, Consultation consultation) {
        this.id = id;
        this.posologie = posologie;
        this.consultation = consultation;
    }

    public Ordonnance(String posologie, Consultation consultation) {
        this.posologie = posologie;
        this.consultation = consultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    
    @Override
    public String toString(){
        if(this.id!=0) return "oui";
        return "non";
    }
    
    
}
