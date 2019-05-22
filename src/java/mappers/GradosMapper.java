package mappers;


import beans.GradoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        GradoBean dat = new GradoBean();
        
        if (rs.getString("id_grado") != null) {
            dat.setID_GRADO(rs.getString("id_grado").trim());
        } else {
            dat.setID_GRADO(rs.getString("id_grado"));
        }
        if (rs.getString("grado") != null) {
            dat.setGRADO(rs.getString("grado").trim());
        } else {
            dat.setGRADO(rs.getString("grado"));
        }
         
        return dat;
    }

}
