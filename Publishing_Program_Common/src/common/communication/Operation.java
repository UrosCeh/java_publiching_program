/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.communication;

import java.io.Serializable;

/**
 *
 * @author hatch
 */
public enum Operation implements Serializable {
    ULOGUJ_AUTORA,
    KREIRAJ_NOVU_KATEGORIJU,
    KREIRAJ_CLANAK,
    PRONADJI_CLANKE,
    UCITAJ_OBJAVLJEN_CLANAK,
    UCITAJ_NEOBJAVLJEN_CLANAK,
    AZURIRAJ_CLANAK,
    OBRISI_CLANAK,
    OBJAVI_CLANAK,
    UKLONI_OBJAVLJENI_CLANAK,
    UCITAJ_LISTU_KATEGORIJA,
    UCITAJ_LISTU_OBJAVLJENIH_CLANAKA,
}
