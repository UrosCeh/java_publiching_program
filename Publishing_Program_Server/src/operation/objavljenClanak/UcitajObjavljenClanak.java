/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.objavljenClanak;

import domain.classes.ObjavljenClanak;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UcitajObjavljenClanak extends GenericOperation {

    private ObjavljenClanak result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof ObjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (ObjavljenClanak) repo.get((ObjavljenClanak) params, null, null);
    }
    
    public ObjavljenClanak getResult() {
        return result;
    }
    
}
