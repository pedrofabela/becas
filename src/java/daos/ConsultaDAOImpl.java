package daos;

import beans.AcademicoBean;
import beans.BecasBean;
import beans.renapoBean;

import java.util.List;
import java.util.ArrayList;
import mappers.BasesMapper;
import mappers.BecasMapper;
import mappers.CCTMapper;
import mappers.ColoniaMapper;
import mappers.EstadosMapper;
import mappers.GradosMapper;
import mappers.PromediosMapper;
import mappers.RequisitoBuenoMapper;
import mappers.RequisitosMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

public class ConsultaDAOImpl extends OracleDAOFactory implements ConsultaDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    public List ConsultaBecas() throws Exception {
        String query = "SELECT ID_BECA, NOM_BECA,ACRO_BECA,ESTATUS_BECA,FECHA_INICIO,FECHA_TERMINO,POB_OBJ,IMAGEN, CASE WHEN (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO  THEN '1' ELSE '0' END AS ESTATUS_FECHA, RESTRICCION_ESC FROM  CAT_BECAS WHERE ACTIVO=1";
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
       
       
        public String ConsultaCiclo(BecasBean obj) throws Exception {
        String query = "SELECT ID_CICLO FROM CAT_CICLOS WHERE ID_BECA='"+obj.getID_BECA_AUX()+"' AND ESTATUS=1";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String ciclo = null;
        ciclo = queryStringUnCampo(query);
        return ciclo;
    }
        
        
         public String ConsultaIntervalo(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT INTERVALO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }
         
            public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT NIVEL FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }
         
       
        public String ConsultaEscParticipa(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT  CCT FROM TBL_ESCPART_CICLO WHERE ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"' AND CCT='"+ren.getCCT()+"' AND ROWNUM=1";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String participa = null;
        participa = queryStringUnCampo(query);
        return participa;
    }
         
         
         
         
         
         
    public List ConsultaEstadosCivil() throws Exception {
        String query = "SELECT ID_ESTADO_CIVIL, ESTADO_CIVIL FROM "+Constantes.TablaEstados+"";
        //System.out.println("QueryConsultaCatalogos ---> " + query);
        List list = null;
        list = queryForList(query, new EstadosMapper());
        return list;
    }
    
         public List ConsultaColonia(renapoBean obj) throws Exception {
        String query = "SELECT cp.asenta,mun.idn_mpio,mun.desc_mpio from cat_cp cp INNER JOIN cat_mpio_nal mun on cp.idn_mpio=mun.idn_mpio WHERE cp.cp='"+obj.getCP()+"'";
        Constantes.enviaMensajeConsola("CONSULTA COLONIAS ---> " + query);
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
        temporal = new ObjPrepareStatement("CURP_AS", "STRING", objg.getCONSULTA_CURP().toUpperCase());
        arregloCampos.add(temporal);
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
        temporal = new ObjPrepareStatement("MUNICIPIO", "STRING", objg.getID_MUNICIPIO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO_AS", "STRING", objg.getDOMICILIO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD_AS", "STRING", objg.getCOLONIA().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTRECALLE1", "STRING", objg.getCALLE1().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTRECALLE2", "STRING", objg.getCALLE2().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("REFERENCIA_DOM", "STRING", objg.getREFERENCIA().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELEFONO_FIJO", "STRING", objg.getTELEFONO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELEFONO_CELULAR", "STRING", objg.getCELULAR().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CORREO", "STRING", objg.getEMAIL().toLowerCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", "1");
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESTADO_CIVIL", "STRING", objg.getID_ESTADO_CIVIL().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO().toUpperCase());
        arregloCampos.add(temporal);
        
        
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaAspirantes, arregloCampos);
    }
        
         public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception {
        String query = "";
        query = "SELECT cct,nombre,domicilio,FILTRO_PARA_TABLERO AS VERTIENTE,turno_1 FROM cat_ccts where cct='"+cct.getCCTAUX()+"' AND ROWNUM=1";
        Constantes.enviaMensajeConsola(" query consulta CCTS --> " + query);
        AcademicoBean resu = (AcademicoBean) oraDaoFac.queryForObject(query, new CCTMapper());
        return resu;
    }
         
     public List ConsultaGrados() throws Exception {
        String query = "SELECT id_grado,grado FROM cat_grados";
        Constantes.enviaMensajeConsola("CONSULTA GRADOS ---> " + query);
        List list = null;
        list = queryForList(query, new GradosMapper());
        return list;
    }     
     
     public List ConsultaPromedios() throws Exception {
        String query = "SELECT id_promedio,promedio FROM cat_promedios";
        Constantes.enviaMensajeConsola("CONSULTA promedios ---> " + query);
        List list = null;
        list = queryForList(query, new PromediosMapper());
        return list;
    }     
     
     public String ConsultaAspirante(renapoBean obj) throws Exception {
        String query = "SELECT id_aspirante  FROM "+Constantes.TablaAspirantes+" where curp_as='"+obj.getCONSULTA_CURP()+"'";
        Constantes.enviaMensajeConsola("CONSULTA aspirante ---> " + query);
        String aspirante = null;
        aspirante = queryStringUnCampo(query);
        return aspirante;
    }     
     
     public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS ACADEMICOS...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CCT", "STRING", objg.getCCT().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TURNO", "STRING", objg.getTURNO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GRADO", "STRING", objg.getGRADO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PROMEDIO", "STRING", objg.getPROMEDIO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getID_CICLO().toUpperCase());
        arregloCampos.add(temporal);
       
        
        
        

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaDatosAcademicos, arregloCampos);
    } 
  
}
