/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.BecasBean;
import beans.RespuestasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class RespuestasMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        RespuestasBean dat = new RespuestasBean();
        
         if (rs.getString("ID_RESPUESTA") != null) {
            dat.setID_RESPUESTA(rs.getString("ID_RESPUESTA").trim());
        } else {
            dat.setID_RESPUESTA(rs.getString("ID_RESPUESTA"));
        }
        
        
        if (rs.getString("RESPUESTA") != null) {
            dat.setRESPUESTA(rs.getString("RESPUESTA").trim());
        } else {
            dat.setRESPUESTA(rs.getString("RESPUESTA"));
        }
      
         
        return dat;
    }
    
}
