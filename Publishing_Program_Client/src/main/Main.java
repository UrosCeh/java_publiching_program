/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import communication.Communication;
import domain.classes.Autor;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.constants.Constants;
import view.coordinator.ViewCoordinator;

/**
 *
 * @author hatch
 */
public class Main {

    public static void main(String[] args) {
        try {
//            ViewCoordinator.getInstance().openMainForm();
            Autor a = Communication.getInstance().ulogujAutora("adam13", "adam13"); //radi
            System.out.println("Autor " + a);
            ViewCoordinator.getInstance().addParam(Constants.CURRENT_AUTOR, a);

//            boolean k = Communication.getInstance().kreirajNovuKategoriju("neki cetvrti naziv"); //radi, ako ne posotji ulogovan user baca exception
//            System.out.println("Kategorija " + k);
            
//            NeobjavljenClanak nc = new NeobjavljenClanak();
//            nc.setNaslov("Naslov novog julskog clanka");
//            nc.setTekst("Tekst novog julskog clanka");
//            nc.setAutor(a);
////
//            Kategorija k = new Kategorija();
//            k.setKategorijaId(3);
//            nc.setKategorija(k);
//            nc.setDatumIVreme(LocalDateTime.of(2021, Month.AUGUST, 5, 20, 0));
//            boolean c = Communication.getInstance().kreirajClanak(nc); // radi, ako ne postoji ulogovan user baca exception
//            System.out.println(c);

//            NeobjavljenClanak nc = Communication.getInstance().ucitajNeobjavljeniClanak(11); //radi, ako nema id vraca null, ako nije ulogovan vraca exception
//            System.out.println("ucitajNC " + nc);
//            boolean t = Communication.getInstance().objaviClanak(nc);
//            System.out.println(t);

            ObjavljenClanak oc = Communication.getInstance().ucitajObjavljeniClanak(4); //radi
            System.out.println("ucitajOC " + oc);
            
//            ArrayList<ObjavljenClanak> ocLista = Communication.getInstance().ucitajListuObjavljenihClanaka("naslov"); // radi
//            for (ObjavljenClanak objavljenClanak : ocLista) {
//                System.out.println(objavljenClanak);
//            }
//            
//            ArrayList<Kategorija> katLista = Communication.getInstance().ucitajListuKategorija(); // radi
//            for (Kategorija kategorija : katLista) {
//                System.out.println(kategorija);
//            }

//            ArrayList<NeobjavljenClanak> ncLista = Communication.getInstance().pronadjiClanke("clanak"); // radi i sa adminom i sa piscom, ako user nije ulogovan baca exception
//            for (NeobjavljenClanak neobjavljenClanak : ncLista) {
//                System.out.println(neobjavljenClanak);
//            }

//            nc.setNaslov("Izmenjen naslov 11 clanka");
//            nc.setTekst("Drugi neki tekst");
//            boolean anc = Communication.getInstance().azurirajClanak(nc); // radi, ako nije piscev clanak baca exception
//            System.out.println(anc);

//            boolean dnc = Communication.getInstance().obrisiClanak(nc);
//            System.out.println(dnc);
            
            boolean uoc = Communication.getInstance().ukloniObjavljeniClanak(oc);
            System.out.println(uoc);

           
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
