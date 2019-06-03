/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.BecasBean;
import beans.AspiranteBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class AspitanteMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
        AspiranteBean dat = new AspiranteBean();
        
         if (rs.getString("ID_ASPIRANTE") != null) {
            dat.setID_ASPIRANTE(rs.getString("ID_ASPIRANTE").trim());
        } else {
            dat.setID_ASPIRANTE(rs.getString("ID_ASPIRANTE"));
        }
        
      
           if (rs.getString("CONSULTA_CURP") != null) {
            dat.setCONSULTA_CURP(rs.getString("CONSULTA_CURP").trim());
        } else {
            dat.setCONSULTA_CURP(rs.getString("CONSULTA_CURP"));
        }
         
           if (rs.getString("NOMBRE_RENAPO") != null) {
            dat.setNOMBRE_RENAPO(rs.getString("NOMBRE_RENAPO").trim());
        } else {
            dat.setNOMBRE_RENAPO(rs.getString("NOMBRE_RENAPO"));
        }
         
             if (rs.getString("APATERNO_RENAPO") != null) {
            dat.setAPATERNO_RENAPO(rs.getString("APATERNO_RENAPO").trim());
        } else {
            dat.setAPATERNO_RENAPO(rs.getString("APATERNO_RENAPO"));
        }
          
                if (rs.getString("AMATERNO_RENAPO") != null) {
            dat.setAMATERNO_RENAPO(rs.getString("AMATERNO_RENAPO").trim());
        } else {
            dat.setAMATERNO_RENAPO(rs.getString("AMATERNO_RENAPO"));
        }
         
             if (rs.getString("FEC_NAC_RENAPO") != null) {
            dat.setFEC_NAC_RENAPO(rs.getString("FEC_NAC_RENAPO").trim());
        } else {
            dat.setAMATERNO_RENAPO(rs.getString("FEC_NAC_RENAPO"));
        }       
          
          if (rs.getString("ENTIDAD_NACIMINETO_RENAPO") != null) {
            dat.setENTIDAD_NACIMINETO_RENAPO(rs.getString("ENTIDAD_NACIMINETO_RENAPO").trim());
        } else {
            dat.setENTIDAD_NACIMINETO_RENAPO(rs.getString("ENTIDAD_NACIMINETO_RENAPO"));
        }            
             
                
             
           if (rs.getString("NACIONALIDAD_RENAPO") != null) {
            dat.setNACIONALIDAD_RENAPO(rs.getString("NACIONALIDAD_RENAPO").trim());
        } else {
            dat.setNACIONALIDAD_RENAPO(rs.getString("NACIONALIDAD_RENAPO"));
        }      

              if (rs.getString("CP") != null) {
            dat.setCP(rs.getString("CP").trim());
        } else {
            dat.setCP(rs.getString("CP"));
        }             
        
           if (rs.getString("MUNICIPIO") != null) {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO").trim());
        } else {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO"));
        }             
           
          if (rs.getString("DOMICILIO") != null) {
            dat.setDOMICILIO(rs.getString("DOMICILIO").trim());
        } else {
            dat.setDOMICILIO(rs.getString("DOMICILIO"));
        }     
          
         if (rs.getString("NUM_INTERIOR") != null) {
            dat.setNUM_INT(rs.getString("NUM_INTERIOR").trim());
        } else {
            dat.setNUM_INT(rs.getString("NUM_INTERIOR"));
        }    
         
         if (rs.getString("NUM_EXTERIOR") != null) {
            dat.setNUM_EXT(rs.getString("NUM_EXTERIOR").trim());
        } else {
            dat.setNUM_EXT(rs.getString("NUM_EXTERIOR"));
        }     
          
           if (rs.getString("LOCALIDAD") != null) {
            dat.setLOCALIDAD(rs.getString("LOCALIDAD").trim());
        } else {
            dat.setLOCALIDAD(rs.getString("LOCALIDAD"));
        }        
           
              
           
                if (rs.getString("CALLE1") != null) {
            dat.setCALLE1(rs.getString("CALLE1").trim());
        } else {
            dat.setCALLE1(rs.getString("CALLE1"));
        }        
              
               if (rs.getString("CALLE2") != null) {
            dat.setCALLE2(rs.getString("CALLE2").trim());
        } else {
            dat.setCALLE2(rs.getString("CALLE2"));
        }            
           
          if (rs.getString("REFERENCIA") != null) {
            dat.setREFERENCIA(rs.getString("REFERENCIA").trim());
        } else {
            dat.setREFERENCIA(rs.getString("REFERENCIA"));
        }             
               
             if (rs.getString("TELEFONO") != null) {
            dat.setTELEFONO(rs.getString("TELEFONO").trim());
        } else {
            dat.setTELEFONO(rs.getString("TELEFONO"));
        }       
             
             
          if (rs.getString("CELULAR") != null) {
            dat.setCELULAR(rs.getString("CELULAR").trim());
        } else {
            dat.setCELULAR(rs.getString("CELULAR"));
        }  
          
           if (rs.getString("EMAIL") != null) {
            dat.setEMAIL(rs.getString("EMAIL").trim());
        } else {
            dat.setEMAIL(rs.getString("EMAIL"));
        }      
         
            if (rs.getString("ID_ESTADO_CIVIL") != null) {
            dat.setID_ESTADO_CIVIL(rs.getString("ID_ESTADO_CIVIL").trim());
        } else {
            dat.setID_ESTADO_CIVIL(rs.getString("ID_ESTADO_CIVIL"));
        }      
            
              if (rs.getString("GENERO_RENAPO") != null) {
            dat.setGENERO_RENAPO(rs.getString("GENERO_RENAPO").trim());
        } else {
            dat.setGENERO_RENAPO(rs.getString("GENERO_RENAPO"));
        }     
             
             
        

        
      
         
        return dat;
    }
    
    
}
