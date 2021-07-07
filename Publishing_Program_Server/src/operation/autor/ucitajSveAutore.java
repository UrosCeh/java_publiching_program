/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.autor;

import domain.classes.Autor;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class ucitajSveAutore extends GenericOperation{

    private ArrayList<Autor> result;
    
    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(new Autor(), null, null);
    }
    
    public ArrayList<Autor> getResult() {
        return result;
    }
    
}
