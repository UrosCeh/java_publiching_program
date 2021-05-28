/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.objavljenClanak;

import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UkloniObjavljeniClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof ObjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        boolean d = repo.delete((ObjavljenClanak) params);
        boolean a = repo.add((NeobjavljenClanak) params);
        result = d && a;
    }
    
    public boolean getResult() {
        return result;
    }
    
}
