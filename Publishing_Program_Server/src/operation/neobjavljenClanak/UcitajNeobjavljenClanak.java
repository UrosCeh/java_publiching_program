/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.neobjavljenClanak;

import domain.classes.NeobjavljenClanak;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UcitajNeobjavljenClanak extends GenericOperation {

    private NeobjavljenClanak result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof NeobjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (NeobjavljenClanak) repo.get((NeobjavljenClanak) params, null);
    }
    
    public NeobjavljenClanak getResult() {
        return result;
    }
    
}
