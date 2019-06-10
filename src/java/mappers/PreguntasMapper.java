/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.BecasBean;
import beans.PreguntasBean;
import beans.RespuestasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class PreguntasMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        PreguntasBean dat = new PreguntasBean();
        
         if (rs.getString("id_pregunta") != null) {
            dat.setID_PREGUNTA(rs.getString("id_pregunta").trim());
        } else {
            dat.setID_PREGUNTA(rs.getString("id_pregunta"));
        }
        
        
        if (rs.getString("pregunta") != null) {
            dat.setPREGUNTA(rs.getString("pregunta").trim());
        } else {
            dat.setPREGUNTA(rs.getString("pregunta"));
        }
        
         if (rs.getString("tipo_pregunta") != null) {
            dat.setTIPO_PREGUNTA(rs.getString("tipo_pregunta").trim());
        } else {
            dat.setTIPO_PREGUNTA(rs.getString("tipo_pregunta"));
        }
        
        
      
         
        return dat;
    }
    
}
