/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Medecin;
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
public class MedecinDAO implements IDao<Medecin>{
    private final Database database= new Database();
    private final String SQL_ALL="SELECT * FROM user WHERE role LIKE 'ROLE_MEDECIN'";
    private final String SQL_ONE="SELECT * FROM user WHERE role LIKE 'ROLE_MEDECIN' AND id=?";

    @Override
    public int insert(Medecin medecin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin medecin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> medecins=new ArrayList();
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_ALL);
            ResultSet rs=database.executeSelect(SQL_ALL);
            while(rs.next()){
                Medecin medecin=new Medecin(rs.getString("statut"),rs.getString("disponibilite"),rs.getInt("id"),
                        rs.getString("nomComplet"),rs.getString("login"),rs.getString("password"));
                medecins.add(medecin);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return medecins;
    }

    @Override
    public Medecin findById(int id) {
        Medecin medecin=null;
        database.openConnection();
        database.initPreparedStatement(SQL_ONE);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_ONE);
            if(rs.next()){
                medecin=new Medecin(rs.getString("statut"),rs.getString("disponibilite"),rs.getInt("id"),
                        rs.getString("nomComplet"),rs.getString("login"),rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return medecin;
    }

    
    
}
