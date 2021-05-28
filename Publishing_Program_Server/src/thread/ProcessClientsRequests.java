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
                            response.setResult(Controller.getInstance().kreirajNovuKategoriju((Kategorija) request.getObject()));
                            break;
                        case KREIRAJ_CLANAK:
                            response.setResult(Controller.getInstance().kreirajClanak((Clanak) request.getObject()));
                            break;
                        case PRONADJI_CLANKE:
                            
                            break; 
                        case UCITAJ_OBJAVLJEN_CLANAK:
                            response.setResult(Controller.getInstance().getObjavljenClanak((ObjavljenClanak) request.getObject()));
                            break; 
                        case UCITAJ_NEOBJAVLJEN_CLANAK:
                            response.setResult(Controller.getInstance().getObjavljenClanak((ObjavljenClanak) request.getObject()));
                            break; 
                        case AZURIRAJ_CLANAK:
                            response.setResult(Controller.getInstance().);
                            break; 
                        case OBRISI_CLANAK:
                            break; 
                        case OBJAVI_CLANAK:
                            break; 
                        case UKLONI_OBJAVLJENI_CLANAK:
                            break; 
                        case UCITAJ_LISTU_KATEGORIJA:
                            break;
                        case UCITAJ_LISTU_OBJAVLJENIH_CLANAKA:
                            ArrayList<ObjavljenClanak> list = Controller.getInstance().ucitajListuObjavljenihClanaka();
                            response.setResult(list);
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
