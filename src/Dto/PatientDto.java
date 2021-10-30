/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Antecedant;
import Entities.Rdv;
import java.util.List;

/**
 *
 * @author user
 */
public class PatientDto {
    private int id;
    private String nomComplet;
    private String login;
    private String password;
    private String role;
    private String code;
    private List<Antecedant> antecedants;
    private List<Rdv> rdvs;

    public PatientDto() {
        this.role = "ROLE_PATIENT";
    }

    public PatientDto(int id, String nomComplet, String login, String password, String role, String code) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = "ROLE_PATIENT";
        this.code = code;
    }

    public PatientDto(String nomComplet, String login, String password, String role, String code) {
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
        this.role = "ROLE_PATIENT";
        this.code = code;
    }

    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Antecedant> getAntecedants() {
        return antecedants;
    }

    public void setAntecedants(List<Antecedant> antecedants) {
        this.antecedants = antecedants;
    }
    
    
}
