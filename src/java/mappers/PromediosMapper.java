package mappers;


import beans.GradoBean;
import beans.PromedioBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromediosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        PromedioBean dat = new PromedioBean();
        
        if (rs.getString("id_promedio") != null) {
            dat.setID_PROMEDIO(rs.getString("id_promedio").trim());
        } else {
            dat.setID_PROMEDIO(rs.getString("id_promedio"));
        }
        if (rs.getString("promedio") != null) {
            dat.setPROMEDIO(rs.getString("promedio").trim());
        } else {
            dat.setPROMEDIO(rs.getString("promedio"));
        }
         
        return dat;
    }

}
