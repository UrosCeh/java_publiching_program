/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.neobjavljenClanak;

import domain.classes.Autor;
import domain.classes.NeobjavljenClanak;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class KreirajClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if (params == null || !(params instanceof NeobjavljenClanak)) {
            throw new Exception("Podaci nisu validni!");
        }
        if (!autor.isPisac()) {
            throw new Exception("Nemate dozvolu da izvrsite ovu operaciju!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.add((NeobjavljenClanak) params);
    }
    
    public boolean getResult() {
        return result;
    }
    
}
