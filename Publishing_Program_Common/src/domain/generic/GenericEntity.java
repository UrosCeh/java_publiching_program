/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.generic;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hatch
 */
public interface GenericEntity extends Serializable {
    
    public String getTableName();
    
    public String columnNamesForInsert();

    public String getInsertValues();

    public String columnNamesForUpdate();

    public String whereCondition();

    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception;

    public void setId(int id);
}
