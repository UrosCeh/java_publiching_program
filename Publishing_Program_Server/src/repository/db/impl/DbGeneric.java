/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import domain.classes.ObjavljenClanak;
import domain.generic.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author hatch
 */
public class DbGeneric implements DbRepository<GenericEntity> {

    @Override
    public ArrayList<GenericEntity> getAll(GenericEntity g, String where, String orderby) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(g.getTableName());
        if (where != null) {
            sb.append(where);
        }
        if (orderby != null) {
            sb.append(orderby);
        }
        
        String query = sb.toString();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        ArrayList<GenericEntity> lista = new ArrayList<>(g.getFromResultSet(rs));
        
        stmt.close();
        rs.close();
        return lista;
    }

    @Override
    public GenericEntity get(GenericEntity g, String where) throws Exception {
        //SELECT * FROM >>table<< where ID="+id
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(g.getTableName());
        sb.append(" WHERE ");
        if (where != null) sb.append(where);
        else sb.append(g.whereCondition());
        
        String query = sb.toString();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        ArrayList<GenericEntity> lista = new ArrayList<>(g.getFromResultSet(rs));
        stmt.close();
        rs.close();
        if(lista.isEmpty()) return null;
        
        return lista.get(0);
    }

    @Override
    public boolean add(GenericEntity g) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
                    sb.append(g.getTableName());
                    
                    sb.append("(");
                    sb.append(g.columnNamesForInsert());
                    sb.append(")");
                    
                    sb.append(" VALUES(");
                    sb.append(g.getInsertValues());
                    sb.append(")");
                    
            String query = sb.toString();
            // System.out.println(query);
            Statement s = connection.createStatement();
            int ar = s.executeUpdate(query);
            
            s.close();

            return ar>0;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean update(GenericEntity g) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(g.getTableName())
                    .append(" SET ");
                    sb.append(g.columnNamesForUpdate());
                    sb.append(" WHERE ");
                    sb.append(g.whereCondition());
                    
            String query = sb.toString();
            // System.out.println(query);
            Statement s = connection.createStatement();
            int ar = s.executeUpdate(query);
            
            s.close();

            return ar>0;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean delete(GenericEntity g) throws Exception {
        //"DELETE FROM director where directorID=" + director.getId()
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ");
                    sb.append(g.getTableName());
                    sb.append(" WHERE ");
                    sb.append(g.whereCondition());
                    
            String query = sb.toString();
            // System.out.println(query);
            Statement s = connection.createStatement();
            int ar = s.executeUpdate(query);
            
            s.close();

            return ar>0;
        } catch (Exception e) {
            throw e;
        }
    }
}
