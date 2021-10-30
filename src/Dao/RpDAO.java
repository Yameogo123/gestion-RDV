/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RpDAO implements IDao<Rp>{

    private Database database= new Database();
    private final String SQL_INSERT="INSERT INTO user(nomComplet,login, password,role) VALUES(?,?,?,'ROLE_RP')";
    
    @Override
    public int insert(Rp rp) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, rp.getNomComplet());
            database.getPs().setString(2, rp.getLogin());
            database.getPs().setString(3, rp.getPassword());
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
    public int update(Rp t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rp> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rp findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
