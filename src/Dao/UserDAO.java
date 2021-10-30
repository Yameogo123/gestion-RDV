/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Rp;
import Entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UserDAO implements IDao<User> {
    
    private Database database= new Database();
    private final String SQL_LOGIN_PASSWORD="SELECT * FROM user WHERE login LIKE ? AND password LIKE ? ";
    
    @Override
    public int insert(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User findByLoginPassword(String login, String password){
        User user= null;
        database.openConnection();
        database.initPreparedStatement(SQL_LOGIN_PASSWORD);
        try{
            database.getPs().setString(1, login);
            database.getPs().setString(2, password);
            ResultSet rs= database.executeSelect(SQL_LOGIN_PASSWORD);
            if(rs.next()){
                user=new User(rs.getInt("id"), rs.getString("nomComplet"), rs.getString("login"),rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return user;
    }
    
}
