/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.IngresosBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class IngresoMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        IngresosBean dat = new IngresosBean();
        
         
        
        if (rs.getString("ruta_archivo") != null) {
            dat.setARCHIVO_INGRESO(rs.getString("ruta_archivo").trim());
        } else {
            dat.setARCHIVO_INGRESO(rs.getString("ruta_archivo"));
        }

        
        
      
         
        return dat;
    }
    
}
