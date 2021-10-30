/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Antecedant;
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
public class AntecedantDAO implements IDao<Antecedant>{

    private Database database = new Database();
    private final String SQL_SELECT_ALL="SELECT *FROM antecedant";
    @Override
    public int insert(Antecedant t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Antecedant t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Antecedant> findAll() {
        List<Antecedant> antecedants= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ALL);
        try{
            ResultSet rs=database.executeSelect(SQL_SELECT_ALL);
            while(rs.next()){
                Antecedant antecedant=new Antecedant(rs.getInt("id"), rs.getString("libelle"));
                antecedants.add(antecedant);
            }
        }catch(SQLException ex){
           Logger.getLogger(AntecedantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        
        return antecedants;
    }

    @Override
    public Antecedant findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
