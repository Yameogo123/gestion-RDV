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
public class Constante {
    private int id; 
    private String libelle;
    private String valeur;

    public Constante() {
    }

    public Constante(int id, String libelle, String valeur) {
        this.id = id;
        this.libelle = libelle;
        this.valeur=valeur;
    }

    public Constante(String libelle, String valeur) {
        this.libelle = libelle;
        this.valeur=valeur;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
