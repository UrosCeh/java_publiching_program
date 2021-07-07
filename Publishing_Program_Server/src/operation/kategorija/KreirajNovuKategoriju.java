/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.kategorija;

import domain.classes.Autor;
import domain.classes.Kategorija;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class KreirajNovuKategoriju extends GenericOperation {
    private boolean result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if (params == null || !(params instanceof Kategorija)) {
            throw new Exception("Podaci nisu validni!");
        }
        if (!autor.isAdmin()) {
            throw new Exception("Nemate dozvolu da kreirate novu kategoriju!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        try {
            result = repo.add((Kategorija) params);    
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da kreira novu kategoriju.");
        }
        
    }
    
    public boolean getResult() {
        return result;
    }
    
}
