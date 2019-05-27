/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.BecasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class fechasBecaMapper implements Mapper{
    
    public Object mapRow(ResultSet rs) throws SQLException {
        BecasBean dat = new BecasBean();
        
         if (rs.getString("FECHA_INICIO") != null) {
            dat.setFECHA_INICIO(rs.getString("FECHA_INICIO").trim());
        } else {
            dat.setFECHA_INICIO(rs.getString("FECHA_INICIO"));
        }
        
        
        if (rs.getString("FECHA_TERMINO") != null) {
            dat.setFECHA_TERMINO(rs.getString("FECHA_TERMINO").trim());
        } else {
            dat.setFECHA_TERMINO(rs.getString("FECHA_TERMINO"));
        }
      
         
        return dat;
    }
    
}
