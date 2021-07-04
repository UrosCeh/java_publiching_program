/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.objavljenClanak;

import domain.classes.Autor;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.time.LocalDateTime;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UkloniObjavljeniClanak extends GenericOperation {

    private boolean result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if(params==null || !(params instanceof ObjavljenClanak)){
            throw new Exception("Podaci nisu validni!");
        }
        if (!autor.isAdmin()) {
            throw new Exception("Nemate dozvolu da izvrsite ovu operaciju!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ObjavljenClanak oc = (ObjavljenClanak) params;
        boolean d = repo.delete(oc);
        NeobjavljenClanak nc = new NeobjavljenClanak();
            nc.setNaslov(oc.getNaslov());
            nc.setTekst(oc.getTekst());
            nc.setAutor(oc.getAutor());
            nc.setKategorija(oc.getKategorija());
            nc.setDatumIVreme(LocalDateTime.MAX);
        boolean a = repo.add(nc);
        result = d && a;
    }
    
    public boolean getResult() {
        return result;
    }
    
}
