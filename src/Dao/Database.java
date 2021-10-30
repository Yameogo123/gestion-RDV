/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Database {
    
    private String url="jdbc:mysql://localhost:3306/gest_rdv";
    private String user="root";
    private String password="";
    Connection cnx=null;
    //object dexecution des requetes preparer
    PreparedStatement ps;    
    
    
    public void openConnection(){
        try {
            //1-charger driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //2-ouvrir connection
            cnx= DriverManager.getConnection(url,user, password);
            //System.out.println("connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void initPreparedStatement(String sql){
        //verifier si requete commence par
        if(sql.toLowerCase().startsWith("insert")){
            try {
                //System.out.println("insert");
                ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            try {
                ps=cnx.prepareStatement(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void closeConnection(){
        if(cnx!=null){
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int executeUpdate(String sql){
        int nbreLigne=0;
        try {
            nbreLigne = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbreLigne;
    }
    
    public ResultSet executeSelect(String sql){
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }
    
    
}
