/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Diagnostic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DiagnosticDAO implements IDao<Diagnostic>{

    private final Database database= new Database();
    private final String SQL_INSERT="INSERT INTO diagnostic(id_constante, id_consultation) VALUES(?,?)";
    
    @Override
    public int insert(Diagnostic diag) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setInt(1, diag.getConstante().getId());
            database.getPs().setInt(2, diag.getConsultation().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(DiagnosticDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Diagnostic t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Diagnostic> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Diagnostic findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
