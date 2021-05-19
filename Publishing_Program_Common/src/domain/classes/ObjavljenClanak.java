/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.classes;

import domain.generic.GenericEntity;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author hatch
 */
public class ObjavljenClanak extends Clanak {
      
    private LocalDateTime datumObjvaljivanja;
    private int brojPregleda;
    private Kategorija kategorija;

    public ObjavljenClanak(int clanakId, String naslov, String tekst, Autor autor, LocalDateTime datumObjvaljivanja, int brojPregleda, Kategorija kategorija) {
        super(clanakId, naslov, tekst, autor);
        this.datumObjvaljivanja = datumObjvaljivanja;
        this.brojPregleda = brojPregleda;
        this.kategorija = kategorija;
    }

    public ObjavljenClanak() {
    }
    
    public LocalDateTime getDatumObjvaljivanja() {
        return datumObjvaljivanja;
    }

    public void setDatumObjvaljivanja(LocalDateTime datumObjvaljivanja) {
        this.datumObjvaljivanja = datumObjvaljivanja;
    }

    public int getBrojPregleda() {
        return brojPregleda;
    }

    public void setBrojPregleda(int brojPregleda) {
        this.brojPregleda = brojPregleda;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }


    //////////////////////////////////////////////////////////////////////////
    
    
    @Override
    public String getTableName() {
        return "ObjavljenClanak";
    }

    @Override
    public String columnNamesForInsert() {
        return "objavljenClanakId, naslov, tekst, autorId, datumObjavljivanja, brojPregleda, kategorijaId";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(super.getClanakId()).append("',")
            .append("'").append(super.getNaslov()).append("',")
            .append("'").append(super.getTekst()).append("',")
            .append("'").append(super.getAutor().getAutorId()).append("',")
            .append("'").append(datumObjvaljivanja.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"))).append("',")
            .append("'").append(brojPregleda).append("',")
            .append("'").append(kategorija.getKategorijaId()).append("'");
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        return "objavljenClanakID = " + super.getClanakId();
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<ObjavljenClanak> clanci = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("objavljenClanakId");
            String title = rs.getString("naslov");
            String body = rs.getString("tekst");
            Autor autor = new Autor();
                autor.setAutorId(rs.getInt("autorId"));
            LocalDateTime publishDate = rs.getTimestamp("datumObjavljivanja").toLocalDateTime();
            int views = rs.getInt("brojPregleda");
            Kategorija category = new Kategorija();
                category.setKategorijaId(rs.getInt("kategorijaId"));
            ObjavljenClanak clanak = new ObjavljenClanak(id, title, body, autor, publishDate, views, category);
            clanci.add(clanak);
        }
        return new ArrayList<>(clanci);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
