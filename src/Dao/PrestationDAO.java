/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Prestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PrestationDAO implements IDao<Prestation>{

    private final Database database=new Database();
    private final String SQL_INSERT="INSERT INTO rdv(type,date,id_patient, libelle, id_consultation) VALUES('PRESTATION', ?, ?, ?, ?)";
    private final String SQL_SELECT_ONE="SELECT * FROM rdv WHERE type LIKE 'PRESTATION' AND id= ?";
    
    @Override
    public int insert(Prestation pres) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, pres.getDate());
            database.getPs().setInt(2, pres.getPatient().getId());
            database.getPs().setString(3, pres.getLibelle());
            database.getPs().setInt(4, pres.getConsultation().getId());
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
    public int update(Prestation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prestation findById(int id) {
        Prestation prestation=null;
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ONE);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs=database.executeSelect(SQL_SELECT_ONE);
            if(rs.next()){
                //prestation=new Prestation(rs.getString("libelle"),);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return prestation;
    }
    
}
