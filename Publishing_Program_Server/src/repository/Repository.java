/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domain.classes.NeobjavljenClanak;
import java.util.ArrayList;

/**
 *
 * @author hatch
 */
public interface Repository<T> {
    public ArrayList<T> getAll(T t, String where, String orderby) throws Exception;
    
    public T get(T t, String where) throws Exception;
    
    public boolean add(T t) throws Exception;
    
    public boolean update(T t)throws Exception;
    
    public boolean delete(T t)throws Exception;
}
