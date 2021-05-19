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
public class Autor implements GenericEntity {
    
    private int autorId;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private boolean admin;
    private boolean pisac;

    public Autor(int autorId, String ime, String prezime, String korisnickoIme, String lozinka, boolean admin, boolean pisac) {
        this.autorId = autorId;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.admin = admin;
        this.pisac = pisac;
    }
    
    public Autor() {
    }
    
    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPisac() {
        return pisac;
    }

    public void setPisac(boolean pisac) {
        this.pisac = pisac;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Autor other = (Autor) obj;
        if (this.autorId != other.autorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////
    
    @Override
    public String getTableName() {
        return "Autor";
    }

    @Override
    public String columnNamesForInsert() {
        return "autorId, ime, prezime, korisnickoIme, lozinka, admin, pisac";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(autorId).append("',")
            .append("'").append(ime).append("',")
            .append("'").append(prezime).append("',")
            .append("'").append(korisnickoIme).append("',")
            .append("'").append(lozinka).append("',")
            .append("'").append(admin).append("',")
            .append("'").append(pisac).append("'");
        return sb.toString();    
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        return "autorId = " + autorId;
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<Autor> autori = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("autorId");
            String firstname = rs.getString("ime");
            String lastname = rs.getString("prezime");
            String username = rs.getString("korisnickoIme");
            String password = rs.getString("lozinka");
            boolean admin = rs.getBoolean("admin");
            boolean pisac = rs.getBoolean("pisac");
            Autor autor = new Autor(autorId, ime, prezime, korisnickoIme, lozinka, admin, pisac);
            autori.add(autor);
        }
        return new ArrayList<>(autori);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
