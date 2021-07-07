/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.neobjavljenClanak;

import domain.classes.Autor;
import domain.classes.Kategorija;
import domain.classes.NeobjavljenClanak;
import domain.classes.ObjavljenClanak;
import java.util.ArrayList;
import java.util.List;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UcitajListuNeobjavljenihClanaka extends GenericOperation {

    private ArrayList<NeobjavljenClanak> result;
    private Autor autor;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if(params==null || !(params instanceof String)){
            throw new Exception("Podaci nisu validni!");
        }
        if (!autor.isAdmin() && !autor.isPisac()) {
            throw new Exception("Nemate dozvolu da izvrsite ovu operaciju!");
        }
        this.autor = autor;
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        System.out.println(params);
        String where = " WHERE LOWER(naslov) LIKE '%" + params + "%'";
        ArrayList<NeobjavljenClanak> uaList;
        ArrayList<NeobjavljenClanak> uaListFinal = new ArrayList<>();
        uaList = repo.getAll(new NeobjavljenClanak(), where, null);
        if (uaList != null) {
            for (NeobjavljenClanak neobjavljenClanak : uaList) {
                neobjavljenClanak.setAutor((Autor) repo.get(neobjavljenClanak.getAutor(), null));
                neobjavljenClanak.setKategorija((Kategorija) repo.get(neobjavljenClanak.getKategorija(), null));
                if (autor.isAdmin() || (autor.isPisac() && neobjavljenClanak.getAutor().getAutorId() == autor.getAutorId())) {
                    uaListFinal.add(neobjavljenClanak);
                }
            }
        }
        result = uaListFinal;
    }
    
    public ArrayList<NeobjavljenClanak> getResult() {
        return result;
    }
}
