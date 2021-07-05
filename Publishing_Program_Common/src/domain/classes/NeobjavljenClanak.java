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
public class NeobjavljenClanak extends Clanak {

    public NeobjavljenClanak(int clanakId, String naslov, String tekst, Autor autor, Kategorija kategorija, LocalDateTime rokZaObjavu) {
        super(clanakId, naslov, tekst, autor, kategorija, rokZaObjavu);
    }
    
    public NeobjavljenClanak(String naslov, String tekst, Autor autor, Kategorija kategorija, LocalDateTime rokZaObjavu) {
        super(naslov, tekst, autor, kategorija, rokZaObjavu);
    }

    public NeobjavljenClanak() {
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    
    
    @Override
    public String getTableName() {
        return "NeobjavljenClanak";
    }
    
    @Override
    public String columnNamesForInsert() {
        return "naslov, tekst, autorId, kategorijaId, rokZaObjavu";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(super.getNaslov()).append("',")
            .append("'").append(super.getTekst()).append("',")
            .append("'").append(super.getAutor().getAutorId()).append("',")
            .append("'").append(super.getKategorija().getKategorijaId()).append("',")
            .append("'").append(super.getDbStringDatum()).append("'");
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        StringBuilder sb = new StringBuilder();
        sb  .append("naslov='").append(super.getNaslov()).append("', ")
            .append("tekst='").append(super.getTekst()).append("', ")
            .append("kategorijaID='").append(super.getKategorija().getKategorijaId()).append("', ")
            .append("rokZaObjavu='").append(super.getDbStringDatum()).append("'");
        
        return sb.toString();    
    }

    @Override
    public String whereCondition() {
        return "neobjavljenClanakID = " + super.getClanakId();
    }
    
    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<NeobjavljenClanak> clanci = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("neobjavljenClanakId");
            String title = rs.getString("naslov");
            String body = rs.getString("tekst");
            Autor autor = new Autor();
                autor.setAutorId(rs.getInt("autorId"));
            Kategorija category = new Kategorija();
                category.setKategorijaId(rs.getInt("kategorijaId"));
            LocalDateTime dueDate = rs.getTimestamp("rokZaObjavu").toLocalDateTime();
            NeobjavljenClanak clanak = new NeobjavljenClanak(id, title, body, autor, category, dueDate);
            clanci.add(clanak);
        }
        return new ArrayList<>(clanci);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
