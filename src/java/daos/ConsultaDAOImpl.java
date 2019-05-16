package daos;

import beans.BecasBean;

import java.util.List;
import java.util.ArrayList;
import mappers.BasesMapper;
import mappers.BecasMapper;
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
        String query = "SELECT IDBASE.ID_BASE, NOMBASE.BASE FROM ( SELECT  ID_BASE FROM TBL_BECA_BASE WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1) AND ID_BECA='"+obj.getID_BECA_AUX()+"' ) IDBASE JOIN (SELECT * FROM CAT_BASES) NOMBASE ON IDBASE.ID_BASE=NOMBASE.ID_BASE";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new BasesMapper());
        return list;
    }

       public List ConsultaRequisitos(BecasBean obj) throws Exception {
        String query = "SELECT IDREQUISITO.ID_REQUISITO, NOMREQUISITO.NOM_REQUISITO AS REQUISITO FROM ( SELECT  ID_REQUISITO FROM TBL_BECA_REC WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1) AND ID_BECA='"+obj.getID_BECA_AUX()+"' ) IDREQUISITO JOIN (SELECT * FROM CAT_REQUISITOS) NOMREQUISITO ON IDREQUISITO.ID_REQUISITO=NOMREQUISITO.ID_REQUISITO";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new RequisitoBuenoMapper());
        return list;
    }
  
}
