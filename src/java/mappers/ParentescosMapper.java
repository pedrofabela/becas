package mappers;

import beans.ParentezcoBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentescosMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        ParentezcoBean dat = new ParentezcoBean();
        
         if (rs.getString("ID_PARENTESCO") != null) {
            dat.setID_PARENTESCO(rs.getString("ID_PARENTESCO").trim());
        } else {
            dat.setID_PARENTESCO(rs.getString("ID_PARENTESCO"));
        }
        if (rs.getString("NOM_PARENTESCO") != null) {
            dat.setNOM_PARENTESCO(rs.getString("NOM_PARENTESCO").trim());
        } else {
            dat.setNOM_PARENTESCO(rs.getString("NOM_PARENTESCO"));
        }

         
        return dat;
    }

}
