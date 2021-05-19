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

    private LocalDateTime rokZaObjavu;

    public NeobjavljenClanak(int clanakId, String naslov, String tekst, Autor autor, LocalDateTime rokZaObjavu) {
        super(clanakId, naslov, tekst, autor);
        this.rokZaObjavu = rokZaObjavu;
    }

    public NeobjavljenClanak() {
    }
    
    public LocalDateTime getRokZaObjavu() {
        return rokZaObjavu;
    }

    public void setRokZaObjavu(LocalDateTime rokZaObjavu) {
        this.rokZaObjavu = rokZaObjavu;
    }
    
    
    
    @Override
    public String getTableName() {
        return "NeobjavljenClanak";
    }
    
    @Override
    public String columnNamesForInsert() {
        return "neobjavljenClanakId, naslov, tekst, autorId, rokZaObjavu";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb  .append("'").append(super.getClanakId()).append("',")
            .append("'").append(super.getNaslov()).append("',")
            .append("'").append(super.getTekst()).append("',")
            .append("'").append(super.getAutor().getAutorId()).append("',")
            .append("'").append(rokZaObjavu.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"))).append("'");
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            LocalDateTime dueDate = rs.getTimestamp("rokZaObjavu").toLocalDateTime();
            NeobjavljenClanak clanak = new NeobjavljenClanak(id, title, body, autor, dueDate);
            clanci.add(clanak);
        }
        return new ArrayList<>(clanci);
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
