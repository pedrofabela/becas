/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.TutorBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class TutorMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        TutorBean dat = new TutorBean();

        if (rs.getString("CURP_TU") != null) {
            dat.setCONSULTA_CURP_TU(rs.getString("CURP_TU").trim());
        } else {
            dat.setCONSULTA_CURP_TU(rs.getString("CURP_TU"));
        }

        if (rs.getString("NOMBRE_TU") != null) {
            dat.setNOMBRE_RENAPO_TU(rs.getString("NOMBRE_TU").trim());
        } else {
            dat.setNOMBRE_RENAPO_TU(rs.getString("NOMBRE_TU"));
        }

        if (rs.getString("APATERNO_TU") != null) {
            dat.setAPATERNO_RENAPO_TU(rs.getString("APATERNO_TU").trim());
        } else {
            dat.setAPATERNO_RENAPO_TU(rs.getString("APATERNO_TU"));
        }

        if (rs.getString("AMATERNO_TU") != null) {
            dat.setAMATERNO_RENAPO_TU(rs.getString("AMATERNO_TU").trim());
        } else {
            dat.setAMATERNO_RENAPO_TU(rs.getString("AMATERNO_TU"));
        }

        if (rs.getString("FECHA_NAC_AS") != null) {
            dat.setFEC_NAC_RENAPO_TU(rs.getString("FECHA_NAC_AS").trim());
        } else {
            dat.setFEC_NAC_RENAPO_TU(rs.getString("FECHA_NAC_AS"));
        }

        if (rs.getString("ENTIDAD_NAC") != null) {
            dat.setENTIDAD_NACIMINETO_RENAPO_TU(rs.getString("ENTIDAD_NAC").trim());
        } else {
            dat.setENTIDAD_NACIMINETO_RENAPO_TU(rs.getString("ENTIDAD_NAC"));
        }

        if (rs.getString("NACIONALIDAD") != null) {
            dat.setNACIONALIDAD_RENAPO_TU(rs.getString("NACIONALIDAD").trim());
        } else {
            dat.setNACIONALIDAD_RENAPO_TU(rs.getString("NACIONALIDAD"));
        }

        if (rs.getString("CP") != null) {
            dat.setCP_TU(rs.getString("CP").trim());
        } else {
            dat.setCP_TU(rs.getString("CP"));
        }
        
        if (rs.getString("ID_MUNICIPIO") != null) {
            dat.setID_MUNICIPIO_TU(rs.getString("ID_MUNICIPIO").trim());
        } else {
            dat.setID_MUNICIPIO_TU(rs.getString("ID_MUNICIPIO"));
        }
        
        if (rs.getString("MUNICIPIO") != null) {
            dat.setMUNICIPIO_TU(rs.getString("MUNICIPIO").trim());
        } else {
            dat.setMUNICIPIO_TU(rs.getString("MUNICIPIO"));
        }

        if (rs.getString("DOMICILIO_TU") != null) {
            dat.setDOMICILIO_TU(rs.getString("DOMICILIO_TU").trim());
        } else {
            dat.setDOMICILIO_TU(rs.getString("DOMICILIO_TU"));
        }

        if (rs.getString("LOCALIDAD_ATU") != null) {
            dat.setCOLONIA_TU(rs.getString("LOCALIDAD_ATU").trim());
        } else {
            dat.setCOLONIA_TU(rs.getString("LOCALIDAD_ATU"));
        }

        if (rs.getString("ENTRECALLE1_TU") != null) {
            dat.setCALLE1_TU(rs.getString("ENTRECALLE1_TU").trim());
        } else {
            dat.setCALLE1_TU(rs.getString("ENTRECALLE1_TU"));
        }

        if (rs.getString("ENTRECALLE2_TU") != null) {
            dat.setCALLE2_TU(rs.getString("ENTRECALLE2_TU").trim());
        } else {
            dat.setCALLE2_TU(rs.getString("ENTRECALLE2_TU"));
        }

        if (rs.getString("REFERENCIA_DOM_TU") != null) {
            dat.setREFERENCIA_TU(rs.getString("REFERENCIA_DOM_TU").trim());
        } else {
            dat.setREFERENCIA_TU(rs.getString("REFERENCIA_DOM_TU"));
        }

        if (rs.getString("TELEFONO_FIJO_TU") != null) {
            dat.setTELEFONO_TU(rs.getString("TELEFONO_FIJO_TU").trim());
        } else {
            dat.setTELEFONO_TU(rs.getString("TELEFONO_FIJO_TU"));
        }

        if (rs.getString("TELEFONO_CELULAR_TU") != null) {
            dat.setCELULAR_TU(rs.getString("TELEFONO_CELULAR_TU").trim());
        } else {
            dat.setCELULAR_TU(rs.getString("TELEFONO_CELULAR_TU"));
        }

        if (rs.getString("CORREO_TU") != null) {
            dat.setEMAIL_TU(rs.getString("CORREO_TU").trim());
        } else {
            dat.setEMAIL_TU(rs.getString("CORREO_TU"));
        }

        if (rs.getString("ID_PARENTESCO") != null) {
            dat.setPARENTESCO(rs.getString("ID_PARENTESCO").trim());
        } else {
            dat.setPARENTESCO(rs.getString("ID_PARENTESCO"));
        }
        
        if (rs.getString("GENERO") != null) {
            dat.setGENERO_RENAPO_TU(rs.getString("GENERO").trim());
        } else {
            dat.setGENERO_RENAPO_TU(rs.getString("GENERO"));
        }
         if (rs.getString("ID_ESTADO_CIVIL_TU") != null) {
            dat.setID_ESTADO_CIVIL_TU(rs.getString("ID_ESTADO_CIVIL_TU").trim());
        } else {
            dat.setID_ESTADO_CIVIL_TU(rs.getString("ID_ESTADO_CIVIL_TU"));
        }

        
        return dat;
    }

}
