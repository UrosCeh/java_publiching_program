/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.classes.Autor;
import domain.classes.ObjavljenClanak;
import java.util.ArrayList;
import operation.GenericOperation;
import operation.autor.LoginAutor;
import operation.objavljenClanak.getAllObjavljenClanak;


/**
 *
 * @author hatch
 */
public class Controller {
        private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public ArrayList<ObjavljenClanak> getAllObjavljeniClanak() throws Exception {
        GenericOperation o = new getAllObjavljenClanak();
        o.execute(new ObjavljenClanak());
        return ((getAllObjavljenClanak) o).getResult();
    }

    public Autor login(Autor a) throws Exception {
        GenericOperation o = new LoginAutor();
        o.execute(a);
        return ((LoginAutor) o).getResult();
    }
}
