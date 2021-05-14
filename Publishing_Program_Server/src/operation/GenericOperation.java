/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbGeneric;

/**
 *
 * @author hatch
 */
public abstract class GenericOperation {
    protected Repository repo;

    public GenericOperation() {
        repo = new DbGeneric();
    }
    
    public final void execute(Object params) throws Exception{
        try {
            startTransaction();
            preconditions(params);
            executeOperation(params);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } finally {
            disconnect();
        }
        
    }
    
    public final void executeWithoutCommit(Object params) throws Exception{
        try {
            startTransaction();
            preconditions(params);
            executeOperation(params);
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }
        
    }
    
    private void startTransaction() throws Exception {
        ((DbRepository) repo).connect();
    }

    private void commitTransaction() throws Exception {
        ((DbRepository) repo).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repo).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repo).disconnect();
    }

    protected abstract void preconditions(Object params) throws Exception;

    protected abstract void executeOperation(Object params)throws Exception;
}
