package daos;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.CobeneficiarioBean;
import beans.IngresosBean;
import beans.TutorBean;
import beans.renapoBean;

import java.util.List;
import java.util.ArrayList;
import mappers.BasesMapper;
import mappers.BecasMapper;
import mappers.CCTMapper;
import mappers.CobeMapper;
import mappers.ColoniaMapper;
import mappers.EstadosMapper;
import mappers.GradosMapper;
import mappers.ParentescosMapper;
import mappers.PromediosMapper;
import mappers.RequisitoBuenoMapper;
import mappers.RequisitosMapper;
import mappers.RespuestasMapper;
import mappers.TutorMapper;
import mappers.fechasBecaMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

public class ConsultaDAOImpl extends OracleDAOFactory implements ConsultaDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    public List ConsultaBecas() throws Exception {
        String query = "SELECT ID_BECA, NOM_BECA,ACRO_BECA,ESTATUS_BECA,FECHA_INICIO,FECHA_TERMINO,POB_OBJ,IMAGEN, CASE WHEN (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO  THEN '1' ELSE '0' END AS ESTATUS_FECHA, RESTRICCION_ESC FROM  CAT_BECAS WHERE ACTIVO=1";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new BecasMapper());
        return list;
    }
    public List ConsultaFechasBeca(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT FECHA_INICIO, FECHA_TERMINO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"' AND NIVEL='"+ren.getNIVEL_AUX()+"'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new fechasBecaMapper());
        return list;
    }
   

    public List ConsultaReq(BecasBean obj) throws Exception {
        String query = "SELECT r.nom_requisito FROM tbl_beca_rec br INNER JOIN cat_requisitos r on br.id_requisito = r.id_requisito where br.id_beca='" + obj.getID_BECA_AUX() + "'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new RequisitosMapper());
        return list;
    }

    public List ConsultaBases(BecasBean obj) throws Exception {
        String query = "SELECT IDBASE.ID_BASE, NOMBASE.BASE FROM ( SELECT  ID_BASE FROM TBL_BECA_BASE WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1 AND ID_BECA='" + obj.getID_BECA_AUX() + "') AND ID_BECA='" + obj.getID_BECA_AUX() + "' ) IDBASE JOIN (SELECT * FROM CAT_BASES) NOMBASE ON IDBASE.ID_BASE=NOMBASE.ID_BASE";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new BasesMapper());
        return list;
    }

    public List ConsultaRequisitos(BecasBean obj) throws Exception {
        String query = "SELECT IDREQUISITO.ID_REQUISITO, NOMREQUISITO.NOM_REQUISITO AS REQUISITO FROM ( SELECT  ID_REQUISITO FROM TBL_BECA_REC WHERE ID_CICLO=(SELECT ID_CICLO FROM CAT_CICLOS WHERE ESTATUS=1 AND ID_BECA='" + obj.getID_BECA_AUX() + "') AND ID_BECA='" + obj.getID_BECA_AUX() + "' ) IDREQUISITO JOIN (SELECT * FROM CAT_REQUISITOS) NOMREQUISITO ON IDREQUISITO.ID_REQUISITO=NOMREQUISITO.ID_REQUISITO";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        List list = null;
        list = queryForList(query, new RequisitoBuenoMapper());
        return list;
    }

    public String ConsultaCiclo(BecasBean obj) throws Exception {
        String query = "SELECT ID_CICLO FROM CAT_CICLOS WHERE ID_BECA='" + obj.getID_BECA_AUX() + "' AND ESTATUS='1'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String ciclo = null;
        ciclo = queryStringUnCampo(query);
        return ciclo;
    }
        
        
         public String ConsultaIntervalo(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT INTERVALO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"' AND NIVEL='"+ren.getNIVEL_AUX()+"'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }
         
            public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT NIVEL FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='"+obj.getID_BECA_AUX()+"' AND ID_CICLO='"+ren.getID_CICLO()+"' AND NIVEL='"+ren.getNIVEL_AUX()+"'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }

    public String ConsultaEscParticipa(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT  CCT FROM TBL_ESCPART_CICLO WHERE ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "' AND CCT='" + ren.getCCT() + "' AND ROWNUM=1";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String participa = null;
        participa = queryStringUnCampo(query);
        return participa;
    }

    public List ConsultaEstadosCivil() throws Exception {
        String query = "SELECT ID_ESTADO_CIVIL, ESTADO_CIVIL FROM " + Constantes.TablaEstados + "";
        //System.out.println("QueryConsultaCatalogos ---> " + query);
        List list = null;
        list = queryForList(query, new EstadosMapper());
        return list;
    }

    public List ConsultaColonia(AspiranteBean obj) throws Exception {
        String query = "SELECT cp.asenta,mun.idn_mpio,mun.desc_mpio from cat_cp cp INNER JOIN cat_mpio_nal mun on cp.idn_mpio=mun.idn_mpio WHERE cp.cp='" + obj.getCP() + "'";
        Constantes.enviaMensajeConsola("CONSULTA COLONIAS ---> " + query);
        List list = null;
        list = queryForList(query, new ColoniaMapper());
        return list;
    }

    public boolean GuardaDatosPersonales(AspiranteBean objg) throws Exception {

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

    public boolean ActualizaDatosPersonales(AspiranteBean objg) throws Exception {

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

        String condicion = "WHERE ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate(Constantes.TablaAspirantes, arregloCampos, condicion);
    }

    public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception {
        String query = "";
        query = "SELECT cct,nombre,domicilio,FILTRO_PARA_TABLERO AS VERTIENTE,turno_1 FROM cat_ccts where cct='" + cct.getCCTAUX() + "' AND ROWNUM=1";
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

    public String ConsultaAspirante(AspiranteBean obj) throws Exception {
        String query = "SELECT id_aspirante  FROM " + Constantes.TablaAspirantes + " where curp_as='" + obj.getCONSULTA_CURP() + "'";
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

    public boolean ActualizaDatosAcademicos(AcademicoBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS ACADEMICOS...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("GRADO", "STRING", objg.getGRADO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("PROMEDIO", "STRING", objg.getPROMEDIO().toUpperCase());
        arregloCampos.add(temporal);

        String condicion = "where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' and ID_CICLO='" + objg.getID_CICLO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate(Constantes.TablaDatosAcademicos, arregloCampos, condicion);
    }

    public TutorBean ConsultaTutor(AcademicoBean objg) throws Exception {
        String query = "";
        query = "SELECT CURP_TU,NOMBRE_TU,APATERNO_TU,AMATERNO_TU ,TO_CHAR(FECHA_NAC_AS,'DD/MM/YYYY') as FECHA_NAC_AS,ENTIDAD_NAC,NACIONALIDAD,CP,MUNICIPIO AS ID_MUNICIPIO,(SELECT cm.desc_mpio FROM CAT_MPIO_NAL cm where cm.idn_mpio=municipio)as municipio,DOMICILIO_TU,LOCALIDAD_ATU,ENTRECALLE1_TU,ENTRECALLE2_TU,REFERENCIA_DOM_TU,TELEFONO_FIJO_TU,TELEFONO_CELULAR_TU, "
                + " CORREO_TU,ID_PARENTESCO,ID_CICLO,GENERO,ID_ESTADO_CIVIL_TU  FROM " + Constantes.TablaTutor + " where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' AND ID_CICLO='" + objg.getID_CICLO() + "'";
        Constantes.enviaMensajeConsola(" query consulta TUTOR --> " + query);
        TutorBean resu = (TutorBean) oraDaoFac.queryForObject(query, new TutorMapper());
        return resu;
    }

    public CobeneficiarioBean ConsultaCobe(AcademicoBean objg) throws Exception {
        String query = "";
        query = "SELECT curp_cob,nombre_cob,apaterno_cob,amaterno_cob,TO_CHAR(fecha_nac_cob,'DD/MM/YYYY') as fecha_nac_cob,entidad_nac,nacionalidad,id_parentesco,id_estado_civil,genero "
                + " FROM " + Constantes.TablaCobeneficiario + " where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' AND ID_CICLO='" + objg.getID_CICLO() + "'";
        Constantes.enviaMensajeConsola(" query consulta COBE --> " + query);
        CobeneficiarioBean resu = (CobeneficiarioBean) oraDaoFac.queryForObject(query, new CobeMapper());
        return resu;
    }

    public CobeneficiarioBean ConsultaCobeXcurp(AcademicoBean objg) throws Exception {
        String query = "";
        query = "SELECT curp_cob,nombre_cob,apaterno_cob,amaterno_cob,TO_CHAR(fecha_nac_cob,'DD/MM/YYYY') as fecha_nac_cob,entidad_nac,nacionalidad,id_parentesco,id_estado_civil,genero "
                + " FROM " + Constantes.TablaCobeneficiario + " where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' AND ID_CICLO='" + objg.getID_CICLO() + "' and curp_cob='"+objg.getCURP_AUX()+"'";
        Constantes.enviaMensajeConsola(" query consulta COBE --> " + query);
        CobeneficiarioBean resu = (CobeneficiarioBean) oraDaoFac.queryForObject(query, new CobeMapper());
        return resu;
    }

    public List ConsultaParentesco() throws Exception {
        String query = "SELECT ID_PARENTESCO,NOM_PARENTESCO FROM cat_parentesco";
        Constantes.enviaMensajeConsola("CONSULTA parentescos ---> " + query);
        List list = null;
        list = queryForList(query, new ParentescosMapper());
        return list;
    }

    public boolean GuardaDatosTutor(TutorBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS TUTOR...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CURP_TU", "STRING", objg.getCONSULTA_CURP_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE_TU", "STRING", objg.getNOMBRE_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APATERNO_TU", "STRING", objg.getAPATERNO_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("AMATERNO_TU", "STRING", objg.getAMATERNO_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_NAC_AS", "STRING", objg.getFEC_NAC_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", objg.getENTIDAD_NACIMINETO_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NACIONALIDAD", "STRING", objg.getNACIONALIDAD_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CP", "STRING", objg.getCP_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MUNICIPIO", "STRING", objg.getID_MUNICIPIO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("DOMICILIO_TU", "STRING", objg.getDOMICILIO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("LOCALIDAD_ATU", "STRING", objg.getCOLONIA_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTRECALLE1_TU", "STRING", objg.getCALLE1_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTRECALLE2_TU", "STRING", objg.getCALLE2_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("REFERENCIA_DOM_TU", "STRING", objg.getREFERENCIA_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELEFONO_FIJO_TU", "STRING", objg.getTELEFONO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("TELEFONO_CELULAR_TU", "STRING", objg.getCELULAR_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CORREO_TU", "STRING", objg.getEMAIL_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ESTATUS", "STRING", objg.getSTATUS().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PARENTESCO", "STRING", objg.getPARENTESCO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getID_CICLO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO_TU().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESTADO_CIVIL_TU", "STRING", objg.getID_ESTADO_CIVIL_TU().toUpperCase());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaTutor, arregloCampos);
    }

    public boolean GuardaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS COBENEFICIARIO...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("CURP_COB", "STRING", objg.getCURP_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NOMBRE_COB", "STRING", objg.getNOMBRE_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("APATERNO_COB", "STRING", objg.getAPATERNO_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("AMATERNO_COB", "STRING", objg.getAMATERNO_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FECHA_NAC_COB", "STRING", objg.getFEC_NAC_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ENTIDAD_NAC", "STRING", objg.getENTIDAD_NACIMINETO_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NACIONALIDAD", "STRING", objg.getNACIONALIDAD_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PARENTESCO", "STRING", objg.getPARENTESCO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESTADO_CIVIL", "STRING", objg.getID_ESTADO_CIVIL_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getID_CICLO_CO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaCobeneficiario, arregloCampos);
    }

    public List ConsultaRespuestas() throws Exception {
        String query = "SELECT ID_RESPUESTA,RESPUESTA FROM CAT_RESPUESTA WHERE STATUS='1'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new RespuestasMapper());
        return list;
    }

    public boolean GuardaSocioeconomico(IngresosBean objg) throws Exception {

        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS SOCIOECONOMICOS...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RESPUESTA1", "STRING", objg.getRESPUESTA1().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RESPUESTA2", "STRING", objg.getRESPUESTA2().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("MONTO", "STRING", objg.getMONTO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getID_CICLO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_BECA", "STRING", objg.getID_BECA().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RUTA_ARCHIVO", "STRING", objg.getARCHIVO_INGRESO());
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaSocioEconomico, arregloCampos);

    }

}
