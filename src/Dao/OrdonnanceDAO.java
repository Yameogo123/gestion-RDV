/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Consultation;
import Entities.Ordonnance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class OrdonnanceDAO implements IDao<Ordonnance>{

    private final Database database= new Database();
    private final ConsultationDAO consDao= new ConsultationDAO();
    private final String SQL_ONE="SELECT * FROM ordonnance WHERE id=?";
    @Override
    public int insert(Ordonnance t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Ordonnance t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        Ordonnance ordonnance=null;
        database.openConnection();
        database.initPreparedStatement(SQL_ONE);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_ONE);
            if(rs.next()){
                Consultation cons=consDao.findById(rs.getInt("id_consultation"));
                ordonnance=new Ordonnance(rs.getInt("id"), rs.getString("posologie"), cons);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return ordonnance;
    }
    
}
