/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.classes.Autor;
import domain.classes.Clanak;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.util.ArrayList;
import operation.GenericOperation;
import operation.autor.IzlogujAutora;
import operation.autor.UlogujAutora;
import operation.autor.ucitajSveAutore;
import operation.kategorija.KreirajNovuKategoriju;
import operation.kategorija.UcitajListuKategorija;
import operation.neobjavljenClanak.AzurirajClanak;
import operation.neobjavljenClanak.KreirajClanak;
import operation.neobjavljenClanak.ObjaviClanak;
import operation.neobjavljenClanak.ObrisiClanak;
import operation.neobjavljenClanak.UcitajListuNeobjavljenihClanaka;
import operation.neobjavljenClanak.UcitajNeobjavljenClanak;
import operation.objavljenClanak.UcitajListuObjavljenihClanaka;
import operation.objavljenClanak.UcitajObjavljenClanak;
import operation.objavljenClanak.UkloniObjavljeniClanak;


/**
 *
 * @author hatch
 */
public class Controller {
        private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Autor ulogujAutora(Autor a, Autor loggedIn) throws Exception {
        GenericOperation o = new UlogujAutora();
        o.execute(a, loggedIn);
        return ((UlogujAutora) o).getResult();
    }
    
    public boolean izlogujAutora(Autor a) throws Exception {
        GenericOperation o = new IzlogujAutora();
        o.execute(a, null);
        return ((IzlogujAutora) o).getResult();
    }
    
    public ArrayList<Autor> ucitajSveAutore() throws Exception {
        GenericOperation o = new ucitajSveAutore();
        o.execute(null, null);
        return ((ucitajSveAutore) o).getResult();
    }

    public boolean kreirajNovuKategoriju(Kategorija k, Autor loggedIn) throws Exception {
        GenericOperation o = new KreirajNovuKategoriju();
        System.out.println(k.toString());
        o.execute(k, loggedIn);
        return ((KreirajNovuKategoriju) o).getResult();
    }

    public boolean kreirajClanak(Clanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new KreirajClanak();
        o.execute(c, loggedIn);
        return ((KreirajClanak) o).getResult();
    }
    
    public ArrayList<NeobjavljenClanak> ucitajListuNeobjavljenihClanaka(String s, Autor loggedIn) throws Exception {
        GenericOperation o = new UcitajListuNeobjavljenihClanaka();
        o.execute(s, loggedIn);
        return ((UcitajListuNeobjavljenihClanaka) o).getResult();
    }

    public ObjavljenClanak ucitajObjavljenClanak(ObjavljenClanak c) throws Exception {
        GenericOperation o = new UcitajObjavljenClanak();
        o.execute(c, null);
        return ((UcitajObjavljenClanak) o).getResult();
    }
    
    public NeobjavljenClanak ucitajNeobjavljenClanak(NeobjavljenClanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new UcitajNeobjavljenClanak();
        o.execute(c, loggedIn);
        return ((UcitajNeobjavljenClanak) o).getResult();
    }
    
    public boolean azurirajClanak(NeobjavljenClanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new AzurirajClanak();
        o.execute(c, loggedIn);
        return ((AzurirajClanak) o).getResult();
    }
    
    public boolean obrisiClanak(NeobjavljenClanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new ObrisiClanak();
        o.execute(c, loggedIn);
        return ((ObrisiClanak) o).getResult();
    }
    
    public boolean objaviClanak(NeobjavljenClanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new ObjaviClanak();
        o.execute(c, loggedIn);
        return ((ObjaviClanak) o).getResult();
    }
    
    public boolean ukloniObjavljeniClanak(ObjavljenClanak c, Autor loggedIn) throws Exception {
        GenericOperation o = new UkloniObjavljeniClanak();
        o.execute(c, loggedIn);
        return ((UkloniObjavljeniClanak) o).getResult();
    }
    
    public ArrayList<Kategorija> ucitajListuKategorija() throws Exception {
        GenericOperation o = new UcitajListuKategorija();
        o.execute(new Kategorija(), null);
        return ((UcitajListuKategorija) o).getResult();
    }

    public ArrayList<ObjavljenClanak> ucitajListuObjavljenihClanaka(String s) throws Exception {
        GenericOperation o = new UcitajListuObjavljenihClanaka();
        o.execute(s, null);
        return ((UcitajListuObjavljenihClanaka) o).getResult();
    }
}
