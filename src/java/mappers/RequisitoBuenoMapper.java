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
public class RequisitoBuenoMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        BecasBean dat = new BecasBean();
        
         if (rs.getString("ID_REQUISITO") != null) {
            dat.setID_REQUISITO(rs.getString("ID_REQUISITO").trim());
        } else {
            dat.setID_REQUISITO(rs.getString("ID_REQUISITO"));
        }
        
        
        if (rs.getString("REQUISITO") != null) {
            dat.setREQUISITO(rs.getString("REQUISITO").trim());
        } else {
            dat.setREQUISITO(rs.getString("REQUISITO"));
        }
      
         
        return dat;
    }
    
}
