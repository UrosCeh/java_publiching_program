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
public class AzurirajClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if(params==null || !(params instanceof NeobjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
        NeobjavljenClanak nc = (NeobjavljenClanak) repo.get((NeobjavljenClanak) params, null);
        if (nc.getAutor().getAutorId() != autor.getAutorId()) {
            throw new Exception("Nemate dozvolu da izmenite dati clanak!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        try {
            result = repo.update((NeobjavljenClanak) params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da izmeni clanak.");
        }
        
    }
    
    public boolean getResult() {
        return result;
    }
    
}
