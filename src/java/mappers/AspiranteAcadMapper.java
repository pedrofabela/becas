/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.AcademicoBean;
import beans.AspiranteBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class AspiranteAcadMapper implements Mapper{
    
       public Object mapRow(ResultSet rs) throws SQLException {
           AcademicoBean dat = new AcademicoBean();
        
         if (rs.getString("GRADO") != null) {
            dat.setGRADO(rs.getString("GRADO").trim());
        } else {
            dat.setGRADO(rs.getString("GRADO"));
        }
        if (rs.getString("PROMEDIO") != null) {
            dat.setPROMEDIO(rs.getString("PROMEDIO").trim());
        } else {
            dat.setPROMEDIO(rs.getString("PROMEDIO"));
        }
      
        
        

        
      
         
        return dat;
    }
}
