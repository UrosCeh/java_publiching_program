/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.objavljenClanak;

import domain.classes.Autor;
import domain.classes.Kategorija;
import domain.classes.ObjavljenClanak;
import java.util.ArrayList;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UcitajListuObjavljenihClanaka extends GenericOperation {
    
    private ArrayList<ObjavljenClanak> result;

    @Override
    protected void preconditions(Object params, Autor autor) throws Exception {
        if(params==null || !(params instanceof String)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        String where = " WHERE LOWER(naslov) LIKE '%" + params + "%'";
        String orderby = " ORDER BY datumObjavljivanja DESC";
        result = repo.getAll(new ObjavljenClanak(), where, orderby);
        if (result != null) {
            for (ObjavljenClanak objavljenClanak : result) {
                objavljenClanak.setAutor((Autor) repo.get(objavljenClanak.getAutor(), null));
                objavljenClanak.setKategorija((Kategorija) repo.get(objavljenClanak.getKategorija(), null));
            }
        }
    }

    public ArrayList<ObjavljenClanak> getResult() {
        return result;
    }
    
}
