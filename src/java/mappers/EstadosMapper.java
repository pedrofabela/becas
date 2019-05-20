package mappers;

import beans.EstadoCivilBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        EstadoCivilBean dat = new EstadoCivilBean();
        
        if (rs.getString("ID_ESTADO_CIVIL") != null) {
            dat.setID_ESTADO_CIVIL(rs.getString("ID_ESTADO_CIVIL").trim());
        } else {
            dat.setID_ESTADO_CIVIL(rs.getString("ID_ESTADO_CIVIL"));
        }
        if (rs.getString("ESTADO_CIVIL") != null) {
            dat.setESTADO_CIVIL(rs.getString("ESTADO_CIVIL").trim());
        } else {
            dat.setESTADO_CIVIL(rs.getString("ESTADO_CIVIL"));
        }
        
         
        return dat;
    }

}
