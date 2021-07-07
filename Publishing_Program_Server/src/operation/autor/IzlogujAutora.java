/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.autor;

import domain.classes.Autor;
import operation.GenericOperation;
import view.controller.ServerViewController;

/**
 *
 * @author hatch
 */
public class IzlogujAutora extends GenericOperation{
    
    private boolean result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if (params == null || !(params instanceof Autor)) {
            throw new Exception("Autor nije ulogovan");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ServerViewController.getInstance().logoutAuthor((Autor) params);
    }
    
    public boolean getResult() {
        return result;
    }
    
}
