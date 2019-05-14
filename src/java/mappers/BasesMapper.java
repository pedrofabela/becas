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
public class BasesMapper implements Mapper{
     public Object mapRow(ResultSet rs) throws SQLException {
        BecasBean dat = new BecasBean();
        
         if (rs.getString("ID_BASE") != null) {
            dat.setID_BASE(rs.getString("ID_BASE").trim());
        } else {
            dat.setID_BASE(rs.getString("ID_BASE"));
        }
        
        
        if (rs.getString("BASE") != null) {
            dat.setBASE(rs.getString("BASE").trim());
        } else {
            dat.setBASE(rs.getString("BASE"));
        }
      
         
        return dat;
    }
    
}
