/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.classes;

import domain.generic.GenericEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hatch
 */
public class Kategorija implements GenericEntity {
    
    private int kategorijaId;
    private String naziv;

    public Kategorija(int kategorijaId, String naziv) {
        this.kategorijaId = kategorijaId;
        this.naziv = naziv;
    }

    public Kategorija() {
    }

    public int getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(int kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorija other = (Kategorija) obj;
        if (this.kategorijaId != other.kategorijaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv;
    }
    

    //////////////////////////////////////////////////////////////////////////
    
    
    @Override
    public String getTableName() {
        return "Kategorija";
    }

    @Override
    public String columnNamesForInsert() {
        return "naziv";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(naziv).append("'");
        return sb.toString();    
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        return "kategorijaId = " + kategorijaId;
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<Kategorija> kategorije = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("kategorijaId");
            String title = rs.getString("naziv");
            Kategorija kategorija = new Kategorija(id, title);
            kategorije.add(kategorija);
        }
        return new ArrayList<>(kategorije);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
