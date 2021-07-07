/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domain.classes.Autor;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hatch
 */
public class AuthorTableModel extends AbstractTableModel {
    
    private final ArrayList<Autor> autori;
    private final String[] columnNames = new String[]{"Korisnicko ime", "Ime", "Prezime", "Ulogovan"};
    Map<String,String> map;

    public AuthorTableModel(ArrayList<Autor> autori, Map<String,String> map) {
        this.autori = autori;
        this.map = map;
    }
    
    @Override
    public int getRowCount() {
        if (autori != null) {
            return autori.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        Autor a = autori.get(i);
        switch(j) {
            case 0:
                return a.getKorisnickoIme();
            case 1:
                return a.getIme();
            case 2:
                return a.getPrezime();
            case 3:
                return map.get(a.getKorisnickoIme());
            default:
                return "n/a";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void loggedInAuthor(Autor a) {
        map.put(a.getKorisnickoIme(), "online");
        fireTableDataChanged();
    }

    public void loggedOutAuthor(Autor a) {
        map.put(a.getKorisnickoIme(), "offline");
        fireTableDataChanged();
    }

    public boolean isAuthorLogged(Autor a) {
        String s = map.get(a.getKorisnickoIme());
        if (s.equals("online")) {
            return true;
        } 
        return false;
    }
    
}
