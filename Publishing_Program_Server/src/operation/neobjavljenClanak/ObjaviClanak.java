/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.neobjavljenClanak;

import domain.classes.Autor;
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
    protected void preconditions(Object params, Autor autor) throws Exception {
        if(params==null || !(params instanceof NeobjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
        if (!autor.isAdmin()) {
            throw new Exception("Nemate dozvolu da izvrsite ovu operaciju!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        try {
            NeobjavljenClanak nc = (NeobjavljenClanak) params;
            boolean d = repo.delete(nc);
            ObjavljenClanak oc = new ObjavljenClanak();
                oc.setNaslov(nc.getNaslov());
                oc.setTekst(nc.getTekst());
                oc.setAutor(nc.getAutor());
                oc.setKategorija(nc.getKategorija());
            boolean a = repo.add(oc);
            result = d && a;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da objavi clanak.");
        }
        
    }
    
    public boolean getResult() {
        return result;
    }
    
}
