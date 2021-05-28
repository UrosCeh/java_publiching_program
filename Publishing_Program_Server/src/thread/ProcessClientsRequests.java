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
                            response.setResult(Controller.getInstance().ulogujAutora((Autor) request.getObject()));
                            break;
                        case KREIRAJ_NOVU_KATEGORIJU:
                            System.out.println(((Kategorija)request.getObject()).toString());
                            response.setResult(Controller.getInstance().kreirajNovuKategoriju((Kategorija) request.getObject()));
                            break;
                        case KREIRAJ_CLANAK:
                            response.setResult(Controller.getInstance().kreirajClanak((Clanak) request.getObject()));
                            break;
                        case PRONADJI_CLANKE:
                            response.setResult(Controller.getInstance().pronadjiClanke((String) request.getObject()));
                            break; 
                        case UCITAJ_OBJAVLJEN_CLANAK:
                            response.setResult(Controller.getInstance().ucitajObjavljenClanak((ObjavljenClanak) request.getObject()));
                            break; 
                        case UCITAJ_NEOBJAVLJEN_CLANAK:
                            response.setResult(Controller.getInstance().ucitajNeobjavljenClanak((NeobjavljenClanak) request.getObject()));
                            break; 
                        case AZURIRAJ_CLANAK:
                            response.setResult(Controller.getInstance().azurirajClanak((NeobjavljenClanak) request.getObject()));
                            break; 
                        case OBRISI_CLANAK:
                            response.setResult(Controller.getInstance().obrisiClanak((NeobjavljenClanak) request.getObject()));
                            break; 
                        case OBJAVI_CLANAK:
                            response.setResult(Controller.getInstance().objaviClanak((NeobjavljenClanak) request.getObject()));
                            break; 
                        case UKLONI_OBJAVLJENI_CLANAK:
                            response.setResult(Controller.getInstance().ukloniObjavljeniClanak((ObjavljenClanak) request.getObject()));
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
