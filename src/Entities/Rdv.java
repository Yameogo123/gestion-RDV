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
public class Rdv {
    protected int id;
    protected String date;
    protected String type;
    protected Patient patient;

    public Rdv(int id, String date, String type, Patient patient) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.patient = patient;
    }

    public Rdv() {
    }

    public Rdv(String date, String type, Patient patient) {
        this.date = date;
        this.type = type;
        this.patient = patient;
    }
    
    public Rdv(String date, Patient patient) {
        this.date = date;
        this.patient = patient;
    }
    
    public Rdv(int id, String date, Patient patient) {
        this.id = id;
        this.date = date;
        this.patient = patient;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    @Override
    public String toString(){
        return this.type+" du "+this.date+"";
    }
    
    
}
