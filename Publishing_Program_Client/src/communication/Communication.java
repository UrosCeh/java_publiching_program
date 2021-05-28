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
import domain.classes.Clanak;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
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
    

    
//    public List<Clanak> getAllClanak()
    //operations
        //Request r = new Request(Operation.IME, prop/null)
        //sender.send(r)
        //Response response = (Response) receiver.receive();

    public Autor ulogujAutora(String username, String password) throws Exception {
        Autor a = new Autor();
        a.setKorisnickoIme(username);
        a.setLozinka(password);
        
        Request request = new Request(Operation.ULOGUJ_AUTORA, a);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (Autor) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean kreirajNovuKategoriju(String naziv) throws Exception {
        Kategorija k = new Kategorija();
        k.setNaziv(naziv);
        
        Request request = new Request(Operation.KREIRAJ_NOVU_KATEGORIJU, k);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean kreirajClanak(NeobjavljenClanak nc) throws Exception {
        Request request = new Request(Operation.KREIRAJ_CLANAK, nc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    } 
    
    public List<NeobjavljenClanak> pronadjiClanke(String s) throws Exception {
        Request request = new Request(Operation.PRONADJI_CLANKE, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (List<NeobjavljenClanak>) response.getResult();
        }
        else {
            throw response.getException();
        }      
    }
    
    public ObjavljenClanak ucitajObjavljeniClanak(int id) throws Exception {
        ObjavljenClanak oc = new ObjavljenClanak();
        oc.setId(id);
        
        Request request = new Request(Operation.UCITAJ_OBJAVLJEN_CLANAK, oc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (ObjavljenClanak) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public NeobjavljenClanak ucitajNeobjavljeniClanak(int id) throws Exception {
        NeobjavljenClanak nc = new NeobjavljenClanak();
        nc.setId(id);
        
        Request request = new Request(Operation.UCITAJ_NEOBJAVLJEN_CLANAK, nc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (NeobjavljenClanak) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean azurirajClanak(NeobjavljenClanak nc) throws Exception {
        Request request = new Request(Operation.AZURIRAJ_CLANAK, nc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean obrisiClanak(NeobjavljenClanak nc) throws Exception {
        Request request = new Request(Operation.OBRISI_CLANAK, nc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean objaviClanak(NeobjavljenClanak nc) throws Exception {
        Request request = new Request(Operation.OBJAVI_CLANAK, nc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public boolean ukloniObjavljeniClanak(ObjavljenClanak oc) throws Exception {
        Request request = new Request(Operation.UKLONI_OBJAVLJENI_CLANAK, oc);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (boolean) response.getResult();
        }
        else {
            throw response.getException();
        }
    }
    
    public List<Kategorija> ucitajListuKategorija() throws Exception {
        Request request = new Request(Operation.UCITAJ_LISTU_KATEGORIJA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (List<Kategorija>) response.getResult();
        }
        else {
            throw response.getException();
        }      
    }
    
    public List<ObjavljenClanak> ucitajListuObjavljenihClanaka(String s) throws Exception {
        Request request = new Request(Operation.UCITAJ_LISTU_OBJAVLJENIH_CLANAKA, s);
        sender.send(request);
        Response response = (Response) receiver.receive();
        
        if(response.getException() == null) {
            return (List<ObjavljenClanak>) response.getResult();
        }
        else {
            throw response.getException();
        }      
    }
}
