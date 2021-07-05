/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import controller.Controller;
import domain.classes.Autor;
import domain.classes.Clanak;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hatch
 */
public class ProcessClientsRequests extends Thread {
    Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        

        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case ULOGUJ_AUTORA:
                            response.setResult(Controller.getInstance().ulogujAutora((Autor) request.getObject(), request.getAutor()));
                            break;
                        case KREIRAJ_NOVU_KATEGORIJU:
                            // da li je autor admin
                            response.setResult(Controller.getInstance().kreirajNovuKategoriju((Kategorija) request.getObject(), request.getAutor()));
                            break;
                        case KREIRAJ_CLANAK:
                            // da li je autor pisac
                            response.setResult(Controller.getInstance().kreirajClanak((Clanak) request.getObject(), request.getAutor()));
                            break;
                        case PRONADJI_CLANKE:
                            response.setResult(Controller.getInstance().pronadjiClanke((String) request.getObject(), request.getAutor()));
                            break; 
                        case UCITAJ_OBJAVLJEN_CLANAK:
                            response.setResult(Controller.getInstance().ucitajObjavljenClanak((ObjavljenClanak) request.getObject()));
                            break; 
                        case UCITAJ_NEOBJAVLJEN_CLANAK:
                            // da li je autor pisac i njegov je clanak ili je admin
                            response.setResult(Controller.getInstance().ucitajNeobjavljenClanak((NeobjavljenClanak) request.getObject(), request.getAutor()));
                            break; 
                        case AZURIRAJ_CLANAK:
                            // da li je autor pisac i njegov je clanak
                            response.setResult(Controller.getInstance().azurirajClanak((NeobjavljenClanak) request.getObject(), request.getAutor()));
                            break; 
                        case OBRISI_CLANAK:
                            // da li je autor pisac i njegov je clanak
                            response.setResult(Controller.getInstance().obrisiClanak((NeobjavljenClanak) request.getObject(), request.getAutor()));
                            break; 
                        case OBJAVI_CLANAK:
                            // da li je autor admin
                            response.setResult(Controller.getInstance().objaviClanak((NeobjavljenClanak) request.getObject(), request.getAutor()));
                            break; 
                        case UKLONI_OBJAVLJENI_CLANAK:
                            // da li je autor admin
                            response.setResult(Controller.getInstance().ukloniObjavljeniClanak((ObjavljenClanak) request.getObject(), request.getAutor()));
                            break; 
                        case UCITAJ_LISTU_KATEGORIJA:
                            response.setResult(Controller.getInstance().ucitajListuKategorija());
                            break;
                        case UCITAJ_LISTU_OBJAVLJENIH_CLANAKA:
                            response.setResult(Controller.getInstance().ucitajListuObjavljenihClanaka((String) request.getObject()));
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
