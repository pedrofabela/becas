package daos;

import beans.BecasBean;
import beans.renapoBean;

import java.util.List;
import java.util.ArrayList;
import mappers.BasesMapper;
import mappers.BecasMapper;
import mappers.ColoniaMapper;
import mappers.EstadosMapper;
import mappers.RequisitoBuenoMapper;
import mappers.RequisitosMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

public class ConsultaDAOImpl extends OracleDAOFactory implements ConsultaDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    public List ConsultaBecas() throws Exception {
        String query = "SELECT ID_BECA, NOM_BECA,ACRO_BECA,ESTATUS_BECA,FECHA_INICIO,FECHA_TERMINO,POB_OBJ,IMAGEN, CASE WHEN (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO  THEN '1' ELSE '0' END AS ESTATUS_FECHA FROM  CAT_BECAS";
        //System.out.println("QueryConsultaCatalogos ---> " + query);
        List list = null;
        list = queryForList(query, new BecasMapper());
        return list;
    }
    
    public List ConsultaReq(BecasBean obj) throws Exception {
        String query = "SELECT r.nom_requisito FROM tbl_beca_rec br INNER JOIN cat_requisitos r on br.id_requisito = r.id_requisito where br.id_beca='"+obj.getID_BECA_AUX()+"'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new RequisitosMapper());
        return list;
    }

      public List ConsultaBases(BecasBean obj) throws Exception {
        String query = "SELECT IDBASE.ID_BASE, NOMBASE.BASE FROM ( SELECT  ID_BASE FROM TBL_BECA_BASE WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1 AND ID_BECA='"+obj.getID_BECA_AUX()+"') AND ID_BECA='"+obj.getID_BECA_AUX()+"' ) IDBASE JOIN (SELECT * FROM CAT_BASES) NOMBASE ON IDBASE.ID_BASE=NOMBASE.ID_BASE";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new BasesMapper());
        return list;
    }

       public List ConsultaRequisitos(BecasBean obj) throws Exception {
        String query = "SELECT IDREQUISITO.ID_REQUISITO, NOMREQUISITO.NOM_REQUISITO AS REQUISITO FROM ( SELECT  ID_REQUISITO FROM TBL_BECA_REC WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1 AND ID_BECA='"+obj.getID_BECA_AUX()+"') AND ID_BECA='"+obj.getID_BECA_AUX()+"' ) IDREQUISITO JOIN (SELECT * FROM CAT_REQUISITOS) NOMREQUISITO ON IDREQUISITO.ID_REQUISITO=NOMREQUISITO.ID_REQUISITO";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new RequisitoBuenoMapper());
        return list;
    }
       
    public List ConsultaEstadosCivil() throws Exception {
        String query = "SELECT ID_ESTADO_CIVIL, ESTADO_CIVIL FROM "+Constantes.TablaEstados+"";
        //System.out.println("QueryConsultaCatalogos ---> " + query);
        List list = null;
        list = queryForList(query, new EstadosMapper());
        return list;
    }
    
         public List ConsultaColonia(renapoBean obj) throws Exception {
        String query = "SELECT cp.asenta,mun.desc_mpio from cat_cp cp INNER JOIN cat_mpio_nal mun on cp.idn_mpio=mun.idn_mpio WHERE cp.cp='"+obj.getCP()+"'";
        //System.out.println("QueryConsultaCatalogos ---> " + query);
        List list = null;
        list = queryForList(query, new ColoniaMapper());
        return list;
    }
    
        public boolean GuardaDatosPersonales(renapoBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
//        temporal = new ObjPrepareStatement("CURP_AS", "STRING", objg.getCONSULTA_CURP().toUpperCase());
//        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE_AS", "STRING", objg.getNOMBRE_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APATERNO_AS", "STRING", objg.getAPATERNO_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("AMATERNO_AS", "STRING", objg.getAMATERNO_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_NAC_AS", "STRING", objg.getFEC_NAC_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", objg.getENTIDAD_NACIMINETO_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NACIONALIDAD", "STRING", objg.getNACIONALIDAD_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", objg.getCP().toUpperCase());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaAspirantes, arregloCampos);
    }
  
}
