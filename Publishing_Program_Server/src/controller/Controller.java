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
import operation.autor.UlogujAutora;
import operation.kategorija.KreirajNovuKategoriju;
import operation.kategorija.UcitajListuKategorija;
import operation.neobjavljenClanak.AzurirajClanak;
import operation.neobjavljenClanak.KreirajClanak;
import operation.neobjavljenClanak.ObjaviClanak;
import operation.neobjavljenClanak.ObrisiClanak;
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

    public Autor ulogujAutora(Autor a) throws Exception {
        GenericOperation o = new UlogujAutora();
        o.execute(a);
        return ((UlogujAutora) o).getResult();
    }

    public boolean kreirajNovuKategoriju(Kategorija k) throws Exception {
        GenericOperation o = new KreirajNovuKategoriju();
        o.execute(k);
        return ((KreirajNovuKategoriju) o).getResult();
    }

    public boolean kreirajClanak(Clanak c) throws Exception {
        GenericOperation o = new KreirajClanak();
        o.execute(c);
        return ((KreirajClanak) o).getResult();
    }
    
    public ArrayList<NeobjavljenClanak> pronadjiClanke() {
        return null;
    }

    public ObjavljenClanak ucitajObjavljenClanak(ObjavljenClanak c) throws Exception {
        GenericOperation o = new UcitajObjavljenClanak();
        o.execute(c);
        return ((UcitajObjavljenClanak) o).getResult();
    }
    
    public NeobjavljenClanak ucitajNeobjavljenClanak(NeobjavljenClanak c) throws Exception {
        GenericOperation o = new UcitajNeobjavljenClanak();
        o.execute(c);
        return ((UcitajNeobjavljenClanak) o).getResult();
    }
    
    public boolean azurirajClanak(NeobjavljenClanak c) throws Exception {
        GenericOperation o = new AzurirajClanak();
        o.execute(c);
        return ((AzurirajClanak) o).getResult();
    }
    
    public boolean obrisiClanak(NeobjavljenClanak c) throws Exception {
        GenericOperation o = new ObrisiClanak();
        o.execute(c);
        return ((ObrisiClanak) o).getResult();
    }
    
    public boolean objaviClanak(NeobjavljenClanak c) throws Exception {
        GenericOperation o = new ObjaviClanak();
        o.execute(c);
        return ((ObjaviClanak) o).getResult();
    }
    
    public boolean ukloniObjavljeniClanak(ObjavljenClanak c) throws Exception {
        GenericOperation o = new UkloniObjavljeniClanak();
        o.execute(c);
        return ((UkloniObjavljeniClanak) o).getResult();
    }
    
    public ArrayList<Kategorija> ucitajListuKategorija() throws Exception {
        GenericOperation o = new UcitajListuKategorija();
        o.execute(new Kategorija());
        return ((UcitajListuKategorija) o).getResult();
    }
    
    
    
    
    //IZMENI
    public ArrayList<ObjavljenClanak> ucitajListuObjavljenihClanaka() throws Exception {
        GenericOperation o = new UcitajListuObjavljenihClanaka();
        o.execute(new ObjavljenClanak());
        return ((UcitajListuObjavljenihClanaka) o).getResult();
    }
}
