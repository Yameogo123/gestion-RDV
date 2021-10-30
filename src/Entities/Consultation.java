
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
public class Consultation extends Rdv{
    private Medecin medecin;

    public Consultation() {
        this.type="CONSULTATION";
    }

    public Consultation(Medecin medecin) {
        this.medecin = medecin;
    }

    public Consultation(Medecin medecin, int id, String date, Patient patient) {
        super(id, date, patient);
        this.medecin = medecin;
        this.type="CONSULTATION";
    }
    

    public Consultation(Medecin medecin, String date, Patient patient) {
        super(date, patient);
        this.medecin = medecin;
        this.type="CONSULTATION";
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString(){
        return "consultation du "+date;
    }
    
    
}
