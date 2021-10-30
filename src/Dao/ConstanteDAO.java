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
public class ConstanteDAO implements IDao<Constante>{
    
    private final Database database= new Database();
    private final String SQL_INSERT="INSERT INTO constante(libelle, valeur) VALUES(?,?)";

    @Override
    public int insert(Constante constante) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, constante.getLibelle());
            database.getPs().setString(2, constante.getValeur());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ConstanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Constante t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Constante> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Constante findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
