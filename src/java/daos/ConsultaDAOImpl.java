package daos;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.CobeneficiarioBean;
import beans.FolioBean;
import beans.IngresosBean;
import beans.Respuesta_PreguntaBean;
import beans.TutorBean;
import beans.renapoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;
import mappers.AcademicoMapper;
import mappers.AspiranteAcadMapper;
import mappers.AspitanteMapper;
import mappers.BasesMapper;
import mappers.BecasMapper;
import mappers.CCTMapper;
import mappers.CobeMapper;
import mappers.ColoniaMapper;
import mappers.EstadosMapper;
import mappers.GradosMapper;
import mappers.IngresoMapper;
import mappers.ParentescosMapper;
import mappers.Preg_ResMapper;
import mappers.PreguntasMapper;
import mappers.PromediosMapper;
import mappers.RequisitoBuenoMapper;
import mappers.RequisitosMapper;
import mappers.RespuestasMapper;
import mappers.TutorMapper;
import mappers.ValidaCurpMapper;
import mappers.fechasBecaMapper;
import utilidades.Constantes;
import utilidades.ObjPrepareStatement;

public class ConsultaDAOImpl extends OracleDAOFactory implements ConsultaDAO {

    OracleDAOFactory oraDaoFac = new OracleDAOFactory();

    public Connection crearConexion() throws Exception {

        Connection conne;
        conne = createConnection();
        Constantes.enviaMensajeConsola("conexion abierta.........");
        return conne;

    }

    //creando statement
    public Statement crearStatement(Connection cone) throws Exception {
        Statement stei;
        stei = createStatement2(cone);
        return stei;
    }
    
    

    public List ConsultaBecas() throws Exception {
        String query = "SELECT ID_BECA, NOM_BECA,ACRO_BECA,ESTATUS_BECA,FECHA_INICIO,FECHA_TERMINO,POB_OBJ,IMAGEN, CASE WHEN (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO  THEN '1' ELSE '0' END AS ESTATUS_FECHA, RESTRICCION_ESC FROM  CAT_BECAS WHERE ACTIVO=1";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new BecasMapper());
        return list;
    }

    public List ConsultaFechasBeca(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT FECHA_INICIO, FECHA_TERMINO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "' AND NIVEL='" + ren.getNIVEL_AUX() + "'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new fechasBecaMapper());
        return list;
    }

    public List ConsultaAspirante(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT ID_ASPIRANTE, CURP_AS AS CONSULTA_CURP, NOMBRE_AS AS NOMBRE_RENAPO, APATERNO_AS AS APATERNO_RENAPO, AMATERNO_AS AS AMATERNO_RENAPO, TO_CHAR(FECHA_NAC_AS,'DD/MM/YYYY') AS  FEC_NAC_RENAPO, ENTIDAD_NAC AS  ENTIDAD_NACIMINETO_RENAPO, NACIONALIDAD AS NACIONALIDAD_RENAPO, CP, MUNICIPIO, DOMICILIO_AS AS DOMICILIO,NUM_INTERIOR,NUM_EXTERIOR,LOCALIDAD_AS AS LOCALIDAD, ENTRECALLE1 AS CALLE1, ENTRECALLE2 AS CALLE2, REFERENCIA_DOM AS REFERENCIA, TELEFONO_FIJO AS TELEFONO, TELEFONO_CELULAR AS CELULAR, CORREO AS EMAIL, ID_ESTADO_CIVIL, GENERO AS GENERO_RENAPO FROM TBL_ASPIRANTES WHERE CURP_AS='" + ren.getCONSULTA_CURP().toUpperCase() + "'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new AspitanteMapper());
        return list;

    }

    public List ConsultaDatosAca(AspiranteBean obj, AcademicoBean acad) throws Exception {
        String query = "select GRADO, PROMEDIO from TBL_DATOS_ACADEMICOS where ID_ASPIRANTE='" + obj.getID_ASPIRANTE() + "' and ID_CICLO='" + obj.getID_CICLO() + "' AND CCT='" + acad.getCCT() + "'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new AspiranteAcadMapper());
        return list;

    }

    public List ConsultaFechasBecaTodos(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT FECHA_INICIO, FECHA_TERMINO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "' AND ROWNUM=1";
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
        String query = "SELECT INTERVALO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "' AND NIVEL='" + ren.getNIVEL_AUX() + "'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }

    public String ConsultaIntervaloSinNivel(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT INTERVALO FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "'";
        Constantes.enviaMensajeConsola("ListaReq ---> " + query);
        String intervalo = null;
        intervalo = queryStringUnCampo(query);
        return intervalo;
    }

    public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception {
        String query = "SELECT NIVEL FROM VALIDA_LETRA_REG WHERE (SELECT SYSDATE FROM DUAL) <= FECHA_TERMINO AND (SELECT SYSDATE FROM DUAL)>=FECHA_INICIO AND ID_BECA='" + obj.getID_BECA_AUX() + "' AND ID_CICLO='" + ren.getID_CICLO() + "' AND NIVEL='" + ren.getNIVEL_AUX() + "'";
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
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUM_INTERIOR", "STRING", objg.getNUM_INT());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUM_EXTERIOR", "STRING", objg.getNUM_EXT());
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
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUM_INTERIOR", "STRING", objg.getNUM_INT());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("NUM_EXTERIOR", "STRING", objg.getNUM_EXT());
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

    public List ConsultaGrados(String nivel) throws Exception {
        String query = "SELECT id_grado,grado FROM cat_grados where nivel='"+nivel+"' and status='1' ORDER BY ORDEN ASC";
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
        temporal = new ObjPrepareStatement("GRADO", "STRING", objg.getID_GRADO().toUpperCase());
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
        temporal = new ObjPrepareStatement("GRADO", "STRING", objg.getID_GRADO().toUpperCase());
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

    public List ConsultaCobeXcurp(AcademicoBean objg) throws Exception {
        String query = "";
        query = "SELECT curp_cob,nombre_cob,apaterno_cob,amaterno_cob,TO_CHAR(fecha_nac_cob,'DD/MM/YYYY') as fecha_nac_cob,entidad_nac,nacionalidad,id_parentesco,id_estado_civil,genero "
                + " FROM " + Constantes.TablaCobeneficiario + " where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' AND ID_CICLO='" + objg.getID_CICLO() + "' and curp_cob='" + objg.getCURP_AUX() + "'";
        Constantes.enviaMensajeConsola(" query consulta COBE --> " + query);
        List list = null;
        list = queryForList(query, new CobeMapper());
        return list;
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

    public boolean ActualizaDatosTutor(TutorBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS TUTOR...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar    
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
        temporal = new ObjPrepareStatement("ID_PARENTESCO", "STRING", objg.getPARENTESCO().toUpperCase());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_ESTADO_CIVIL_TU", "STRING", objg.getID_ESTADO_CIVIL_TU().toUpperCase());
        arregloCampos.add(temporal);

        String condicion = "where ID_ASPIRANTE='" + objg.getID_ASPIRANTE_TU() + "' AND ID_CICLO='" + objg.getID_CICLO_TU() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate(Constantes.TablaTutor, arregloCampos, condicion);
    }

    public boolean ActualizaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception {

//Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS COBENEFICIARIO...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
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
        temporal = new ObjPrepareStatement("GENERO", "STRING", objg.getGENERO_RENAPO_CO().toUpperCase());
        arregloCampos.add(temporal);

        String Condicion = "where ID_ASPIRANTE='" + objg.getID_ASPIRANTE_CO() + "' AND ID_CICLO='" + objg.getID_CICLO_CO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate(Constantes.TablaCobeneficiario, arregloCampos, Condicion);
    }

    public List ConsultaPreguntas(String idbeca) throws Exception {
        String query = "SELECT cp.id_pregunta,cp.pregunta,cp.tipo_pregunta "
                + "FROM tbl_preg_beca TPB INNER JOIN cat_preguntas cp ON tpb.id_pregunta=cp.id_pregunta WHERE tpb.id_beca='" + idbeca + "' AND cp.status='1'";
        Constantes.enviaMensajeConsola("QueryConsultaPreguntas ---> " + query);
        List list = null;
        list = queryForList(query, new PreguntasMapper());
        return list;
    }

    public List ConsultaRespuestas() throws Exception {
        String query = "SELECT ID_RESPUESTA,RESPUESTA FROM CAT_RESPUESTA WHERE STATUS='1'";
        Constantes.enviaMensajeConsola("QueryConsultaRespuestas ---> " + query);
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

    public boolean GuardaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception {

//modifica euipamiento
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_BECA", "STRING", objg.getID_BECA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_PREGUNTA", "STRING", objg.getID_PREGUNTA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("RESPUESTA", "STRING", objg.getRESPUESTA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getCICLO());
        arregloCampos.add(temporal);

        return oraDaoFac.queryInsertTransaccion(conn, stat, Constantes.TablaRespuesta_Preguntas, arregloCampos);
    }

    public IngresosBean consultaSocioEconomico(String ID, String Ciclo) throws Exception {
        String query = "";
        query = "SELECT ruta_archivo FROM " + Constantes.TablaSocioEconomico + " WHERE id_aspirante='" + ID + "' and id_ciclo='" + Ciclo + "'";
        Constantes.enviaMensajeConsola(" query consulta socioeconomico --> " + query);
        IngresosBean resu = (IngresosBean) oraDaoFac.queryForObject(query, new IngresoMapper());
        return resu;
    }

    public List ConsultaRes_Preg(String idaspirante, String Ciclo) throws Exception {
        String query = "SELECT ID_PREGUNTA,RESPUESTA FROM " + Constantes.TablaRespuesta_Preguntas + " WHERE ID_ASPIRANTE='" + idaspirante + "' AND ID_CICLO='" + Ciclo + "'";
        Constantes.enviaMensajeConsola("QueryConsultaPregunta_Respuestas ---> " + query);
        List list = null;
        list = queryForList(query, new Preg_ResMapper());
        return list;
    }

    public boolean ActualizaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception {

//modifica euipamiento
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
        ObjPrepareStatement temporal;

        temporal = new ObjPrepareStatement("RESPUESTA", "STRING", objg.getRESPUESTA());
        arregloCampos.add(temporal);

        String where = "";
        where = " WHERE ID_ASPIRANTE = " + objg.getID_ASPIRANTE() + " and ID_CICLO = " + objg.getCICLO() + " and ID_PREGUNTA='"+objg.getID_PREGUNTA()+"'";
        return oraDaoFac.queryUpdateTransaccion(conn, stat, Constantes.TablaRespuesta_Preguntas, arregloCampos, where);
    }

    public boolean ActualizaSocioeconomico(IngresosBean objg) throws Exception {

        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS SOCIOECONOMICOS...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("RUTA_ARCHIVO", "STRING", objg.getARCHIVO_INGRESO());
        arregloCampos.add(temporal);

        String condiciones = " where ID_ASPIRANTE='" + objg.getID_ASPIRANTE() + "' and ID_CICLO='" + objg.getID_CICLO() + "'";

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryUpdate(Constantes.TablaSocioEconomico, arregloCampos, condiciones);

    }

    public AcademicoBean ObtenDatosAcademicosAspirante(String ID, String Ciclo) throws Exception {
        String query = "";
        query = "SELECT cc.cct,cc.nombre,cc.domicilio,cc.FILTRO_PARA_TABLERO AS VERTIENTE,cc.turno_1,ta.grado,ta.promedio FROM cat_ccts cc INNER JOIN tbl_datos_academicos ta on cc.cct=ta.cct where ta.id_aspirante='" + ID + "' and ta.id_ciclo='" + Ciclo + "'";
        Constantes.enviaMensajeConsola(" query consulta socioeconomico --> " + query);
        AcademicoBean resu = (AcademicoBean) oraDaoFac.queryForObject(query, new AcademicoMapper());
        return resu;
    }

    public int consultaSecuencia(String secuencia) throws Exception {
        Integer numreg;
        String query = "select (" + secuencia + ".NextVal)  From " + Constantes.tablaDual;
        //Constantes.enviaMensajeConsola(" query num SECUENCIA: " + query);
        System.out.println("query num SECUENCIA:" + query);
        numreg = oraDaoFac.queryInteger(query);
        return numreg;
    }

    public boolean GuardaFolio(FolioBean objg) throws Exception {

        //Crear un ArrayList para agregar los campos a insertar
        ArrayList<ObjPrepareStatement> arregloCampos = new ArrayList<ObjPrepareStatement>();
//Crear un objeto de tipo ObjPrepareStatement
        ObjPrepareStatement temporal;
//imprimiendo los valores del objeto tipo CCT...........
        Constantes.enviaMensajeConsola("Entre al DAO del INSERT DATOS SOCIOECONOMICOS...................................");

//En el objeto temporal settear el campo de la tabla, el tipo de dato y el valor a insertar
        temporal = new ObjPrepareStatement("ID_ASPIRANTE", "STRING", objg.getID_ASPIRANTE());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_BECA", "STRING", objg.getID_BECA());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("FOLIO", "STRING", objg.getFOLIO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("ID_CICLO", "STRING", objg.getID_CICLO());
        arregloCampos.add(temporal);
        temporal = new ObjPrepareStatement("STATUS", "STRING", "1");
        arregloCampos.add(temporal);

//Se terminan de adicionar a nuesto ArrayLis los objetos
//Ejecutar la funcion del OracleDAOFactory queryInsert, se deber pasar como parmetros la tabla en donde se insertara
        return oraDaoFac.queryInsert(Constantes.TablaFolio, arregloCampos);

    }

    public String verificaFolio(String idaspirante, String idbeca, String idciclo) throws Exception {
        String query = "SELECT Folio  FROM " + Constantes.TablaFolio + " where id_aspirante='" + idaspirante + "' and id_beca='" + idbeca + "' and id_ciclo='" + idciclo + "'";
        Constantes.enviaMensajeConsola("CONSULTA folio ---> " + query);
        String folio = null;
        folio = queryStringUnCampo(query);
        return folio;
    }

    public List VerificaCurp(String curp) throws Exception {
        String query = "SELECT ta.id_aspirante ,tf.id_beca, tf.id_ciclo FROM tbl_aspirantes ta INNER JOIN tbl_folios_becas tf on tf.id_aspirante=ta.id_aspirante INNER JOIN CAT_CICLOS tc on tf.id_ciclo=tc.id_ciclo WHERE ta.curp_as='" + curp + "' and tc.estatus='1'";
        Constantes.enviaMensajeConsola("QueryConsultaBecas ---> " + query);
        List list = null;
        list = queryForList(query, new ValidaCurpMapper());
        return list;
    }

}
