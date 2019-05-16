package mappers;

import beans.BecasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BecasMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        BecasBean dat = new BecasBean();
        
        if (rs.getString("ID_BECA") != null) {
            dat.setID_BECA(rs.getString("ID_BECA").trim());
        } else {
            dat.setID_BECA(rs.getString("ID_BECA"));
        }
        if (rs.getString("NOM_BECA") != null) {
            dat.setNOM_BECA(rs.getString("NOM_BECA").trim());
        } else {
            dat.setNOM_BECA(rs.getString("NOM_BECA"));
        }
        if (rs.getString("ACRO_BECA") != null) {
            dat.setACRO_BECA(rs.getString("ACRO_BECA").trim());
        } else {
            dat.setACRO_BECA(rs.getString("ACRO_BECA"));
        }
        if (rs.getString("ESTATUS_BECA") != null) {
            dat.setESTATUS_BECA(rs.getString("ESTATUS_BECA").trim());
        } else {
            dat.setESTATUS_BECA(rs.getString("ESTATUS_BECA"));
        }
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
         if (rs.getString("POB_OBJ") != null) {
            dat.setPOB_OBJ(rs.getString("POB_OBJ").trim());
        } else {
            dat.setPOB_OBJ(rs.getString("POB_OBJ"));
        }
         if (rs.getString("IMAGEN") != null) {
            dat.setIMAGEN(rs.getString("IMAGEN").trim());
        } else {
            dat.setIMAGEN(rs.getString("IMAGEN"));
        } 
         if (rs.getString("ESTATUS_FECHA") != null) {
            dat.setESTATUS_FECHA(rs.getString("ESTATUS_FECHA").trim());
        } else {
            dat.setESTATUS_FECHA(rs.getString("ESTATUS_FECHA"));
        } 
         
        return dat;
    }

}
