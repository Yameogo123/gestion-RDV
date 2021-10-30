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
public class Patient extends User{
    private String code;

    public Patient() {
        this.role="ROLE_PATIENT";
    }

    public Patient(int id, String nomComplet, String login, String password, String code) {
        super(id, nomComplet, login, password);
        this.role="ROLE_PATIENT";
        this.code=code;
    }

    public Patient(String nomComplet, String login, String password,String code) {
        super(nomComplet, login, password);
        this.role="ROLE_PATIENT";
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString(){
        return this.nomComplet+"("+this.code+")";
    }
    
}
