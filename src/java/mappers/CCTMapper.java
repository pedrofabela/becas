/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.AcademicoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class CCTMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        AcademicoBean dat = new AcademicoBean();
        
         if (rs.getString("cct") != null) {
            dat.setCCT(rs.getString("cct").trim());
        } else {
            dat.setCCT(rs.getString("cct"));
        }
        
        
        if (rs.getString("nombre") != null) {
            dat.setNOMCCT(rs.getString("nombre").trim());
        } else {
            dat.setNOMCCT(rs.getString("nombre"));
        }
        
         if (rs.getString("domicilio") != null) {
            dat.setDOMCCT(rs.getString("domicilio").trim());
        } else {
            dat.setDOMCCT(rs.getString("domicilio"));
        }
         
         if (rs.getString("vertiente") != null) {
            dat.setNIVELCCT(rs.getString("vertiente").trim());
        } else {
            dat.setNIVELCCT(rs.getString("vertiente"));
        }
         
         if (rs.getString("turno_1") != null) {
            dat.setTURNO(rs.getString("turno_1").trim());
        } else {
            dat.setTURNO(rs.getString("turno_1"));
        }    
        return dat;
    }
    
}
