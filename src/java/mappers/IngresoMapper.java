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
        
         if (rs.getString("respuesta1") != null) {
            dat.setRESPUESTA1(rs.getString("respuesta1").trim());
        } else {
            dat.setRESPUESTA1(rs.getString("respuesta1"));
        }
         
          if (rs.getString("respuesta2") != null) {
            dat.setRESPUESTA2(rs.getString("respuesta2").trim());
        } else {
            dat.setRESPUESTA2(rs.getString("respuesta2"));
        }
        

        
        
        if (rs.getString("monto") != null) {
            dat.setMONTO(rs.getString("monto").trim());
        } else {
            dat.setMONTO(rs.getString("monto"));
        }
        
         if (rs.getString("ruta_archivo") != null) {
            dat.setARCHIVO_INGRESO(rs.getString("ruta_archivo").trim());
        } else {
            dat.setARCHIVO_INGRESO(rs.getString("ruta_archivo"));
        }

        
        
      
         
        return dat;
    }
    
}
