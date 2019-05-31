/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.AcademicoBean;
import beans.ValidaCurpBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class ValidaCurpMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        ValidaCurpBean dat = new ValidaCurpBean();
        
         if (rs.getString("id_aspirante") != null) {
            dat.setID_ASPIRANTE(rs.getString("id_aspirante").trim());
        } else {
            dat.setID_ASPIRANTE(rs.getString("id_aspirante"));
        }
        
        
        if (rs.getString("id_beca") != null) {
            dat.setID_BECA(rs.getString("id_beca").trim());
        } else {
            dat.setID_BECA(rs.getString("id_beca"));
        }
        
         if (rs.getString("id_ciclo") != null) {
            dat.setID_CICLO(rs.getString("id_ciclo").trim());
        } else {
            dat.setID_CICLO(rs.getString("id_ciclo"));
        }
         
        
         
         
        return dat;
    }
    
}
