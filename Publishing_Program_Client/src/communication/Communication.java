/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import common.communication.Receiver;
import common.communication.Sender;
import java.net.Socket;

/**
 *
 * @author hatch
 */
public class Communication {
    
    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;
    
    private Communication() throws Exception{
        socket=new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    //operations
        //Request r = new Request(Operation.IME, prop/null)
        //sender.send(r)
        //Response response = (Response) receiver.receive();
}
