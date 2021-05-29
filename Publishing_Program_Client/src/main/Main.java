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
import view.coordinator.ViewCoordinator;

/**
 *
 * @author hatch
 */
public class Main {

    public static void main(String[] args) {
        try {
            //        ViewCoordinator.getInstance().openMainForm();
            Autor a = Communication.getInstance().ulogujAutora("adam13", "adam13"); //radi
            System.out.println("Autor " + a);
            
//            boolean k = Communication.getInstance().kreirajNovuKategoriju("neki treci naziv"); //radi
//            System.out.println("Kategorija " + k);
            
            NeobjavljenClanak nc = new NeobjavljenClanak();
            nc.setNaslov("Naslov novog clanka");
            nc.setTekst("Tekst novog clanka");
            nc.setAutor(a);
            
            Kategorija k = new Kategorija();
            k.setKategorijaId(3);
            nc.setKategorija(k);
            nc.setDatumIVreme(LocalDateTime.of(2021, Month.JUNE, 5, 20, 0));
            boolean c = Communication.getInstance().kreirajClanak(nc);
            System.out.println(c);

//            ObjavljenClanak oc = Communication.getInstance().ucitajObjavljeniClanak(1); //radi
//            System.out.println("ucitajOC " + oc);
//            
//            ObjavljenClanak oc = Communication.getInstance().ucitajObjavljeniClanak(2); //radi, ako nema id vraca null
//            System.out.println("ucitajOC " + oc);
//
//            boolean t = Communication.getInstance().ukloniObjavljeniClanak(oc);
//            System.out.println(t);
            
//            NeobjavljenClanak nc = Communication.getInstance().ucitajNeobjavljeniClanak(5); //radi, ako nema id vraca null
//            System.out.println("ucitajNC " + nc);
//            nc.setNaslov("Izmenjen naslov");
//            nc.setTekst("Izmenjen tekst");
//
//            boolean t = Communication.getInstance().objaviClanak(nc);
//            System.out.println(t);
            
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
