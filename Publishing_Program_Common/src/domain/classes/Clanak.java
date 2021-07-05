/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.classes;

import domain.generic.GenericEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hatch
 */
public abstract class Clanak implements GenericEntity {
    
    private int clanakId;
    private String naslov;
    private String tekst;
    private Autor autor;
    private Kategorija kategorija;
    private LocalDateTime datum;

    public Clanak(int clanakId, String naslov, String tekst, Autor autor, Kategorija kategorija, LocalDateTime datum) {
        this.clanakId = clanakId;
        this.naslov = naslov;
        this.tekst = tekst;
        this.autor = autor;
        this.kategorija = kategorija;
        this.datum = datum;
    }
    
    public Clanak(String naslov, String tekst, Autor autor, Kategorija kategorija, LocalDateTime datum) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.autor = autor;
        this.kategorija = kategorija;
        this.datum = datum;
    }
    
    public Clanak() {
    }

    public int getClanakId() {
        return clanakId;
    }

    public void setClanakId(int clanakId) {
        this.clanakId = clanakId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
    
    public String getStringDatum() {
        return datum.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    
    public String getDbStringDatum() {
        return datum.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
        final Clanak other = (Clanak) obj;
        if (this.clanakId != other.clanakId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return autor.toString() + " | " + naslov;
    }
    
    //////////////////////////////////////////////////////////////////////////

    
    
    
}
