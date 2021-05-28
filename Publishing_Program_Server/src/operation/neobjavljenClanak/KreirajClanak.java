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
public class KreirajClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof NeobjavljenClanak)) {
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.add((NeobjavljenClanak) params, null, null, null);
    }
    
    public boolean getResult() {
        return result;
    }
    
}
