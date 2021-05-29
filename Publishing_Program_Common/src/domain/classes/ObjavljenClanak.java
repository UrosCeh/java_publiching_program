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

    public ObjavljenClanak(int clanakId, String naslov, String tekst, Autor autor, Kategorija kategorija, LocalDateTime datumObjvaljivanja) {
        super(clanakId, naslov, tekst, autor, kategorija);
        this.datumObjvaljivanja = datumObjvaljivanja;
    }

    public ObjavljenClanak() {
    }
    
    public LocalDateTime getDatumIVreme() {
        return datumObjvaljivanja;
    }

    public void setDatumIVreme(LocalDateTime datumObjvaljivanja) {
        this.datumObjvaljivanja = datumObjvaljivanja;
    }

    
    public String getStringDatumIVreme() {
        return datumObjvaljivanja.format(DateTimeFormatter.ofPattern("d.M.yyyy, HH:mm"));
    }

    //////////////////////////////////////////////////////////////////////////
    
    
    @Override
    public String getTableName() {
        return "ObjavljenClanak";
    }

    @Override
    public String columnNamesForInsert() {
        return "naslov, tekst, autorId, kategorijaId, datumObjavljivanja";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(super.getNaslov()).append("',")
            .append("'").append(super.getTekst()).append("',")
            .append("'").append(super.getAutor().getAutorId()).append("',")
            .append("'").append(super.getKategorija().getKategorijaId()).append("',")
            .append("'").append(LocalDateTime.now()).append("'");
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
            Kategorija category = new Kategorija();
                category.setKategorijaId(rs.getInt("kategorijaId"));
            LocalDateTime publishDate = rs.getTimestamp("datumObjavljivanja").toLocalDateTime();
            ObjavljenClanak clanak = new ObjavljenClanak(id, title, body, autor, category, publishDate);
            clanci.add(clanak);
        }
        return new ArrayList<>(clanci);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
