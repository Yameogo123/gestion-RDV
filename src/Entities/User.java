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
public class User {
    protected int id;
    protected String nomComplet;
    protected String login;
    protected String password;
    protected String role;

    public User() {
    }
    
    public User(int id, String nomComplet, String login, String password) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
    }

    public User(String nomComplet, String login, String password) {
        this.nomComplet = nomComplet;
        this.login = login;
        this.password = password;
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

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
