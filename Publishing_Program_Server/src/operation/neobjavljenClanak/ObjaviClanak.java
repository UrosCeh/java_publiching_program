/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.neobjavljenClanak;

import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class ObjaviClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof NeobjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        NeobjavljenClanak nc = (NeobjavljenClanak) params;
        boolean d = repo.delete(nc);
        ObjavljenClanak oc = new ObjavljenClanak();
            oc.setNaslov(nc.getNaslov());
            oc.setTekst(nc.getTekst());
            oc.setAutor(nc.getAutor());
            oc.setKategorija(nc.getKategorija());
        boolean a = repo.add(oc);
        result = d && a;
    }
    
    public boolean getResult() {
        return result;
    }
    
}
