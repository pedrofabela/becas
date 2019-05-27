/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.CobeneficiarioBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class CobeMapper implements Mapper {

    public Object mapRow(ResultSet rs) throws SQLException {
        CobeneficiarioBean dat = new CobeneficiarioBean();

        if (rs.getString("curp_cob") != null) {
            dat.setCURP_CO(rs.getString("curp_cob").trim());
        } else {
            dat.setCURP_CO(rs.getString("curp_cob"));
        }

        if (rs.getString("nombre_cob") != null) {
            dat.setNOMBRE_RENAPO_CO(rs.getString("nombre_cob").trim());
        } else {
            dat.setNOMBRE_RENAPO_CO(rs.getString("nombre_cob"));
        }

        if (rs.getString("apaterno_cob") != null) {
            dat.setAPATERNO_RENAPO_CO(rs.getString("apaterno_cob").trim());
        } else {
            dat.setAPATERNO_RENAPO_CO(rs.getString("apaterno_cob"));
        }

        if (rs.getString("amaterno_cob") != null) {
            dat.setAMATERNO_RENAPO_CO(rs.getString("amaterno_cob").trim());
        } else {
            dat.setAMATERNO_RENAPO_CO(rs.getString("amaterno_cob"));
        }

        if (rs.getString("fecha_nac_cob") != null) {
            dat.setFEC_NAC_RENAPO_CO(rs.getString("fecha_nac_cob").trim());
        } else {
            dat.setFEC_NAC_RENAPO_CO(rs.getString("fecha_nac_cob"));
        }

        if (rs.getString("entidad_nac") != null) {
            dat.setENTIDAD_NACIMINETO_RENAPO_CO(rs.getString("entidad_nac").trim());
        } else {
            dat.setENTIDAD_NACIMINETO_RENAPO_CO(rs.getString("entidad_nac"));
        }

        if (rs.getString("nacionalidad") != null) {
            dat.setNACIONALIDAD_RENAPO_CO(rs.getString("nacionalidad").trim());
        } else {
            dat.setNACIONALIDAD_RENAPO_CO(rs.getString("nacionalidad"));
        }

       

        if (rs.getString("id_parentesco") != null) {
            dat.setPARENTESCO_CO(rs.getString("id_parentesco").trim());
        } else {
            dat.setPARENTESCO_CO(rs.getString("id_parentesco"));
        }
        
        if (rs.getString("genero") != null) {
            dat.setGENERO_RENAPO_CO(rs.getString("genero").trim());
        } else {
            dat.setGENERO_RENAPO_CO(rs.getString("genero"));
        }
         if (rs.getString("id_estado_civil") != null) {
            dat.setID_ESTADO_CIVIL_CO(rs.getString("id_estado_civil").trim());
        } else {
            dat.setID_ESTADO_CIVIL_CO(rs.getString("id_estado_civil"));
        }

        
        return dat;
    }

}
