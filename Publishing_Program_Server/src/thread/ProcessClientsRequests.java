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
                        case GET_ALL_OBJAVLJENI_CLANAK:
                            System.out.println("Dosao u pcr");
                            ArrayList<ObjavljenClanak> list = Controller.getInstance().getAllObjavljeniClanak();
                            response.setResult(list);
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
