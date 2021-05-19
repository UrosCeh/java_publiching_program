/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import common.communication.Operation;
import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import domain.classes.Autor;
import domain.classes.Kategorija;
import domain.classes.ObjavljenClanak;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
        socket=new Socket("localhost", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public List<ObjavljenClanak> getAllObjavljeniClanak() throws Exception {
        Request request = new Request(Operation.GET_ALL_OBJAVLJENI_CLANAK, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (List<ObjavljenClanak>) response.getResult();
        }
        else {
            throw response.getException();
        }
        
//        Autor a1 = new Autor(1, "pera", "peric", "peric123", "peric123", true, true);
//        Autor a2 = new Autor(2, "zika", "zikic", "zikic123", "zikic123", true, false);
//        Autor a3 = new Autor(3, "mika", "mikic", "mikic123", "mikic123", false, true);
//        
//        Kategorija k1 = new Kategorija(1, "zivot");
//        Kategorija k2 = new Kategorija(2, "sport");
//        
//        ObjavljenClanak oc1 = new ObjavljenClanak(1, "naslov 1", "tekst 1", a1, LocalDateTime.of(2021, Month.MARCH, 3, 15, 0), 123, k1);
//        ObjavljenClanak oc2 = new ObjavljenClanak(2, "naslov 2", "tekst 2", a2, LocalDateTime.of(2021, Month.MARCH, 3, 15, 0), 123, k2);
//        
//        List<ObjavljenClanak> clanci = new ArrayList<>();
//        clanci.add(oc1);
//        clanci.add(oc2);
//        return clanci;

       
    }
    //operations
        //Request r = new Request(Operation.IME, prop/null)
        //sender.send(r)
        //Response response = (Response) receiver.receive();

    public Autor login(String username, String password) throws Exception {
        Autor a = new Autor();
        a.setKorisnickoIme(username);
        a.setLozinka(password);
        
        Request request = new Request(Operation.LOGIN, a);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (Autor) response.getResult();
        }
        else {
            throw response.getException();
        }
//       User user=new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        Request request=new Request(Operation.LOGIN, user);
//        sender.send(request);
//        Response response=(Response)receiver.receive();
//        if(response.getException()==null){
//            return (User)response.getResult();
//        }else{
//            throw response.getException();
//        }
    }
}
