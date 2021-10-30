/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.*;
import java.util.List;

/**
 *
 * @author user
 */
public class RdvDto {
    
    private int id;
    private String date;
    private String type;
    private Patient patient;
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    private Medecin medecin;
    private List<Prestation> pres;

    public RdvDto() {
    }

    public RdvDto(int id, String date, String type, Patient patient) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.patient = patient;
    }

    public RdvDto(String date, String type, Patient patient) {
        this.date = date;
        this.type = type;
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

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public List<Prestation> getPres() {
        return pres;
    }

    public void setPres(List<Prestation> pres) {
        this.pres = pres;
    }
    
    
}
