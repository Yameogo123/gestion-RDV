/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PatientDAO implements IDao<Patient> {

    private final Database database= new Database();
    private final String SQL_SELECT_ONE="SELECT * FROM user where role LIKE 'ROLE_PATIENT' and id = ?";
    private final String SQL_SELECT_ALL="SELECT * FROM user where role LIKE 'ROLE_PATIENT'";
    private final String SQL_INSERT="INSERT INTO user (nomComplet, login, password, code, role) VALUES (?,?,?,?,'ROLE_PATIENT')";
    
    @Override
    public int insert(Patient patient) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, patient.getNomComplet());
            database.getPs().setString(2, patient.getLogin());
            database.getPs().setString(3, patient.getPassword());
            database.getPs().setString(4, patient.getCode());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Patient t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ALL);
        try{
            ResultSet rs= database.executeSelect(SQL_SELECT_ALL);
            while(rs.next()){
                Patient patient = new Patient(rs.getInt("id"), rs.getString("nomComplet"), rs.getString("login"), rs.getString("password"),rs.getString("code"));
                patients.add(patient);
            }
        }catch(SQLException ex){
           Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient patient=null;
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ONE);
        try{
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_SELECT_ONE);
            if(rs.next()){
                patient= new Patient(rs.getInt("id"),rs.getString("nomComplet"), rs.getString("login"), rs.getString("password"),rs.getString("code"));
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return patient;
    }
    
}
