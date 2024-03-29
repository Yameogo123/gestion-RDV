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
public class Medicament {
    
    private int id;
    private String code;
    private String libelle;
    private Ordonnance ordonnance;

    public Medicament(int id, String code, String libelle, Ordonnance ordonnance) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.ordonnance = ordonnance;
    }

    public Medicament() {
    }

    public Medicament(String code, String libelle, Ordonnance ordonnance) {
        this.code = code;
        this.libelle = libelle;
        this.ordonnance = ordonnance;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }

    
    
    
    
    
    
}
