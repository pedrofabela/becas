package mappers;

import beans.RequisitosBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequisitosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        RequisitosBean dat = new RequisitosBean();
        
        if (rs.getString("nom_requisito") != null) {
            dat.setNOM_REQUISITO(rs.getString("nom_requisito").trim());
        } else {
            dat.setNOM_REQUISITO(rs.getString("nom_requisito"));
        }
             
        return dat;
    }

}
