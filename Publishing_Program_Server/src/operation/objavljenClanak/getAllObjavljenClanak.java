/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.objavljenClanak;

import domain.classes.Autor;
import domain.classes.ObjavljenClanak;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class getAllObjavljenClanak extends GenericOperation{
    ArrayList<ObjavljenClanak> result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof ObjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        repo.getAll(new ObjavljenClanak(), null, "datumObjavljivanja", null);
        for (ObjavljenClanak objavljenClanak : result) {
            objavljenClanak.setAutor((Autor) repo.get(objavljenClanak.getAutor(), null, null));
        }
        
    }

    public ArrayList<ObjavljenClanak> getResult() {
        return result;
    }
    
}
