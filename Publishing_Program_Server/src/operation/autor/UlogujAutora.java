/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.autor;

import domain.classes.Autor;
import operation.GenericOperation;

/**
 *
 * @author hatch
 */
public class UlogujAutora extends GenericOperation {
    private Autor result;

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Autor)){
            throw new Exception("Podaci nisu validni!");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        String username = ((Autor) params).getKorisnickoIme();
        String password = ((Autor) params).getLozinka();
        Autor autor = (Autor) repo.get(params, null, "korisnickoIme = '" + username + "'");
        
        if (autor.getLozinka().equals(password)) {
            result = autor;
        }
        else {
            throw new Exception("Korsnicko ime i lozinka nisu tacni!");
        }
    }
 
    public Autor getResult() {
        return result;
    }
}
