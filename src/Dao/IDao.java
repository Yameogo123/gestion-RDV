/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;

/**
 *
 * @author user
 */
public interface IDao<T> {
    
    
    public int insert(T t);
    public int update(T t);
    public int delete(int id);

    // interrogation
    public List<T> findAll();
    public T findById(int id);
    
}
