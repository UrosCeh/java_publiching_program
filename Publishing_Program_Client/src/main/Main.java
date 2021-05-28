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
//            Autor a = Communication.getInstance().ulogujAutora("adam13", "adam13"); //radi
//            System.out.println("Autor " + a);
            
//            boolean k = Communication.getInstance().kreirajNovuKategoriju("neki treci naziv"); //radi
//            System.out.println("Kategorija " + k);
            
//            NeobjavljenClanak nc = new NeobjavljenClanak();
//            nc.setNaslov("Naslov neki");
//            nc.setTekst("Tekst neki");
//            nc.setAutor(a);
//            nc.setKategorija();
//            boolean c = Communication.getInstance().kreirajClanak(nc)

//            ObjavljenClanak oc = Communication.getInstance().ucitajObjavljeniClanak(1); //radi
//            System.out.println("ucitajOC " + oc);
//            
//            NeobjavljenClanak nc = Communication.getInstance().ucitajNeobjavljeniClanak(3); //radi, ako nema id vraca null
//            System.out.println("ucitajNC " + nc);

            ArrayList<ObjavljenClanak> kategorije = Communication.getInstance().ucitajListuObjavljenihClanaka("CLAnak 1");
            for (ObjavljenClanak kategorija : kategorije) {
                System.out.println("kategorija: " + kategorija);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
