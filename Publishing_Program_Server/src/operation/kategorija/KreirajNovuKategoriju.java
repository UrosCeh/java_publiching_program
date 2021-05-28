/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.kategorija;

import domain.classes.Kategorija;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class KreirajNovuKategoriju extends GenericOperation {
    private boolean result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || (params instanceof Kategorija)) {
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.add((Kategorija) params, null, null, null);
    }
    
    public boolean getResult() {
        return result;
    }
    
}
