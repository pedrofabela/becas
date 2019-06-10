/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.Respuesta_PreguntaBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class Preg_ResMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        Respuesta_PreguntaBean dat = new Respuesta_PreguntaBean();
        
         if (rs.getString("ID_PREGUNTA") != null) {
            dat.setID_PREGUNTA(rs.getString("ID_PREGUNTA").trim());
        } else {
            dat.setID_PREGUNTA(rs.getString("ID_PREGUNTA"));
        }        
        
        if (rs.getString("RESPUESTA") != null) {
            dat.setRESPUESTA(rs.getString("RESPUESTA").trim());
        } else {
            dat.setRESPUESTA(rs.getString("RESPUESTA"));
        }
      
         
        return dat;
    }
    
}
