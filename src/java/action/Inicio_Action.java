package action;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.CobeneficiarioBean;
import beans.ColoniasBean;
import beans.EstadoCivilBean;
import beans.FolioBean;
import beans.GradoBean;
import beans.IngresosBean;
import beans.ParentezcoBean;
import beans.PreguntasBean;
import beans.PromedioBean;
import beans.RequisitosBean;
import beans.Respuesta_PreguntaBean;
import beans.RespuestasBean;
import beans.TutorBean;
import beans.ValidaCurpBean;

import beans.moduloAuxBean;
import beans.moduloBean;
import beans.renapoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

import utilidades.Constantes;

/*
import mx.gob.edomex.dgsei.ws.ConsultaRenapoPorCurp;
import mx.gob.edomex.dgsei.ws.ConsultaDatosRenapo;
import mx.gob.edomex.dgsei.ws.PersonasDTO;*/
public class Inicio_Action extends ActionSupport {

    //INSTANCIA A LOS BEANS//
    BecasBean objdatos = new BecasBean();
    renapoBean objRenapo = new renapoBean();
    AspiranteBean objAspirante = new AspiranteBean();
    AcademicoBean objDatosA = new AcademicoBean();
    TutorBean objDatosP = new TutorBean();
    CobeneficiarioBean objDatosC = new CobeneficiarioBean();
    IngresosBean objDatosE = new IngresosBean();
    FolioBean objFolio = new FolioBean();
    ValidaCurpBean objvalidaC= new ValidaCurpBean();
    Respuesta_PreguntaBean objRespuesta=new Respuesta_PreguntaBean();

    //************************************
    //VARIABLES REQUERIDAS//
    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    private List<BecasBean> ListaBecas = new ArrayList<BecasBean>();
    private List<RequisitosBean> ListaReq = new ArrayList<RequisitosBean>();
    private List<BecasBean> ListaBases = new ArrayList<BecasBean>();
    private List<BecasBean> ListaRequisitos = new ArrayList<BecasBean>();
    private List<EstadoCivilBean> ListaEstadosCivil = new ArrayList<EstadoCivilBean>();
    private List<ColoniasBean> ListaColonia = new ArrayList<ColoniasBean>();
    private List<GradoBean> ListaGrados = new ArrayList<GradoBean>();
    private List<PromedioBean> ListaPromedios = new ArrayList<PromedioBean>();
    private List<ParentezcoBean> ListaParentesco = new ArrayList<ParentezcoBean>();
    private List<RespuestasBean> ListaRespuestas = new ArrayList<RespuestasBean>();
    private List<AspiranteBean> ListaActualizaAspirante = new ArrayList<AspiranteBean>();
    private List<AcademicoBean> ListaDatosAcad = new ArrayList<AcademicoBean>();
    private List<CobeneficiarioBean> VerificaCobe = new ArrayList<CobeneficiarioBean>();
    private List<BecasBean> ListaFechas = new ArrayList<BecasBean>();
    private List<ValidaCurpBean> validacurp = new ArrayList<ValidaCurpBean>();
    private List<PreguntasBean> ListaPreguntas= new ArrayList<PreguntasBean>();
    private List<Respuesta_PreguntaBean> ListaContestados=new ArrayList<Respuesta_PreguntaBean>();

    private boolean banColonia = false;
    private boolean banColoniaP = false;
    private boolean banFormAca = false;
    private boolean banFormP = false;
    private boolean banFormCobe = false;
    private boolean banMuestraCobe = false;

    private boolean banConCct = false;
    private boolean banConCurp = false;
    private boolean banFormIngresos = false;
    private boolean banT = false;
    private boolean banActualiza = false;
    private boolean banGuarda = false;
    private boolean banActualizaP = false;
    private boolean banGuardaP = false;
    private boolean banGuardaE = false;
    private boolean banActualizaE = false;
    private boolean banMuestraArchivo = false;

    private String VALCOB;
    private String valingreso;

    private String folioMax;
    private String folioSolicitud;

    private File archi;
    private String archiFileName;

    //instancias para web service//
/*    ConsultaDatosRenapo service = null;
    ConsultaRenapoPorCurp port;
    PersonasDTO personas;*/
    //****************************//
    // xxxxxxxxxxxxxxxxxxxxxSESIONxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private usuarioBean usuariocons;
    private Map session = ActionContext.getContext().getSession();
    private String nivelUsuario;
    
    Statement objConexion;
    PreparedStatement objPreConexion;
    Connection conecta;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }
    //xxxxxxxxxxxxxxxxxxxxFIN SESIONxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

    //******************************************METODOS DE CAPTURA*****************************************************
    public String MuestraBecas() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            ListaBecas = (ArrayList<BecasBean>) con.ConsultaBecas();
            Constantes.enviaMensajeConsola("lista becas: " + ListaBecas);

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCct() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            String CctParticipa = "";

            Constantes.enviaMensajeConsola("ID_BECA_AUX: " + objdatos.getID_BECA_AUX());
            Constantes.enviaMensajeConsola("RESTRICCION ESCUELA " + objdatos.getRESTRICCION_ESC());
            Constantes.enviaMensajeConsola("ID DEL CICLO ESCOLAR " + objRenapo.getID_CICLO());
            ListaBecas = (ArrayList<BecasBean>) con.ConsultaBecas();

            if (objdatos.getRESTRICCION_ESC().equals("1")) {

                CctParticipa = con.ConsultaEscParticipa(objdatos, objRenapo);

                Constantes.enviaMensajeConsola("Tamaño participa" + CctParticipa.length());

                if (CctParticipa.length() > 0) {

                    Constantes.enviaMensajeConsola("LA ESCUELA SI PARTICIPA");

                    objDatosA.setCCTAUX(CctParticipa);

                    objDatosA = con.ConsultaCCT(objDatosA);

                    objRenapo.setNIVEL_AUX(objDatosA.getNIVELCCT());

                    objRenapo.setINTERVALO(con.ConsultaIntervalo(objdatos, objRenapo));

                    objRenapo.setNIVEL(con.ConsultaNivel(objdatos, objRenapo));

                    if (objRenapo.getINTERVALO().length() == 0 && objRenapo.getNIVEL().length() == 0) {

                        objRenapo.setINTERVALO(con.ConsultaIntervaloSinNivel(objdatos, objRenapo));

                    }

                    ListaFechas = (ArrayList<BecasBean>) con.ConsultaFechasBeca(objdatos, objRenapo);

                    Iterator LF = ListaFechas.iterator();

                    BecasBean obj2;

                    while (LF.hasNext()) {
                        obj2 = (BecasBean) LF.next();

                        objdatos.setFECHA_INICIO(obj2.getFECHA_INICIO());

                        objdatos.setFECHA_TERMINO(obj2.getFECHA_TERMINO());

                    }

                    if (ListaFechas.size() > 0) {

                        objRenapo.setEN_PERIODO("SI");

                    } else {

                        objRenapo.setEN_PERIODO("NO");
                    }

                    banConCct = false;
                    banConCurp = true;

                } else {

                    banConCct = true;
                    banConCurp = false;

                    addFieldError("NOPARTICIPA", "La escuela no participa en está Beca");

                    Constantes.enviaMensajeConsola("LA ESCUELA NO PARTICIPA EN ESTA BECA");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaReq() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            Constantes.enviaMensajeConsola("ID_BECA_AUX EN METODO ConsultaREQ: " + objdatos.getID_BECA_AUX());

            MuestraBecas();
            ListaBases = (ArrayList<BecasBean>) con.ConsultaBases(objdatos);
            ListaRequisitos = (ArrayList<BecasBean>) con.ConsultaRequisitos(objdatos);

            //System.out.println("auxiliar de beca:"+ objdatos.getID_BECA_AUX());
            objRenapo.setID_CICLO(con.ConsultaCiclo(objdatos));

            banConCct = true;
            objDatosA = null;
            banConCurp = false;

            // System.out.println("id de l cilclo es:"+ con.ConsultaCiclo(objdatos));
            Constantes.enviaMensajeConsola("lista Req: " + ListaRequisitos.size());

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String Registro() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();
            consultaRenapo renapo = new consultaRenapo();

            validacurp = con.VerificaCurp(objRenapo.getCONSULTA_CURP());

            if (validacurp.size() > 0) {
                
                Iterator vc= validacurp.iterator();
                
                ValidaCurpBean objg1;
                
                while (vc.hasNext()) {
                    objg1 = (ValidaCurpBean) vc.next();
                    
                    objvalidaC.setID_ASPIRANTE(objg1.getID_ASPIRANTE());
                    objvalidaC.setID_BECA(objg1.getID_BECA());
                    objvalidaC.setID_CICLO(objg1.getID_CICLO());                 
                }
                
                    Constantes.enviaMensajeConsola("id_beca jsp: "+objdatos.getID_BECA_AUX());
                    Constantes.enviaMensajeConsola("id_ciclo jsp: "+objRenapo.getID_CICLO());
                    
                     Constantes.enviaMensajeConsola("id_beca con: "+objvalidaC.getID_BECA());
                    Constantes.enviaMensajeConsola("id_ciclo con: "+objvalidaC.getID_CICLO());
                    
                if (objvalidaC.getID_BECA().equals(objdatos.getID_BECA_AUX()) && objvalidaC.getID_CICLO().equals(objRenapo.getID_CICLO())) {
                    
                    // si el alumno que intenta registrar es igual a la misma beca y al mismo ciclo y ademas pasa las restricciones de letra y nivel lo deja entrar
                    
                    Constantes.enviaMensajeConsola("alumno ya registrado para le mismo programa educativo");
                    
                     ListaEstadosCivil = con.ConsultaEstadosCivil();

                Constantes.enviaMensajeConsola("curp aspirante " + objRenapo.getCONSULTA_CURP());

                //consulta a tabla de aspirantes antes de renapo
                objAspirante.setID_CICLO(objRenapo.getID_CICLO());

                Constantes.enviaMensajeConsola("id_ciclo: " + objAspirante.getID_CICLO());

                ListaActualizaAspirante = con.ConsultaAspirante(objdatos, objRenapo);

                if (ListaActualizaAspirante.size() > 0) {
                    Iterator LAA = ListaActualizaAspirante.iterator();

                    AspiranteBean obj3;

                    while (LAA.hasNext()) {
                        obj3 = (AspiranteBean) LAA.next();

                        objAspirante.setCONSULTA_CURP(obj3.getCONSULTA_CURP());
                        objAspirante.setID_ASPIRANTE(obj3.getID_ASPIRANTE());
                        objAspirante.setNOMBRE_RENAPO(obj3.getNOMBRE_RENAPO());
                        objAspirante.setAMATERNO_RENAPO(obj3.getAMATERNO_RENAPO());
                        objAspirante.setAPATERNO_RENAPO(obj3.getAPATERNO_RENAPO());
                        objAspirante.setFEC_NAC_RENAPO(obj3.getFEC_NAC_RENAPO());
                        objAspirante.setNACIONALIDAD_RENAPO(obj3.getNACIONALIDAD_RENAPO());
                        objAspirante.setENTIDAD_NACIMINETO_RENAPO(obj3.getENTIDAD_NACIMINETO_RENAPO());
                        objAspirante.setGENERO_RENAPO(obj3.getGENERO_RENAPO());
                        objAspirante.setID_ESTADO_CIVIL(obj3.getID_ESTADO_CIVIL());
                        objAspirante.setDOMICILIO(obj3.getDOMICILIO());
                        objAspirante.setNUM_EXT(obj3.getNUM_EXT());
                        objAspirante.setNUM_INT(obj3.getNUM_INT());
                        objAspirante.setCALLE1(obj3.getCALLE1());
                        objAspirante.setCALLE2(obj3.getCALLE2());
                        objAspirante.setREFERENCIA(obj3.getREFERENCIA());
                        objAspirante.setCP(obj3.getCP());
                        ListaColonia = con.ConsultaColonia(objAspirante);
                        
                        Iterator LC = ListaColonia.iterator();
                        ColoniasBean objg;

                        while (LC.hasNext()) {
                            objg = (ColoniasBean) LC.next();

                            objAspirante.setMUNICIPIO(objg.getMUNICIPIO());
                            objAspirante.setID_MUNICIPIO(objg.getID_MUNICIPIO());

                        }

                        objAspirante.setTELEFONO(obj3.getTELEFONO());
                        objAspirante.setCELULAR(obj3.getCELULAR());
                        objAspirante.setEMAIL(obj3.getEMAIL());

                    }

                    banColonia = true;
                    banActualiza = true;
                    banFormAca = true;

                    ListaDatosAcad = con.ConsultaDatosAca(objAspirante, objDatosA);
                    
                   
                    objDatosA.setNIVELCCT(objRenapo.getNIVEL());
                     Constantes.enviaMensajeConsola("Nivel que llega de timeline: "+objDatosA.getNIVELCCT());

                    ListaGrados = con.ConsultaGrados(objDatosA.getNIVELCCT());
                    ListaPromedios = con.ConsultaPromedios();

                    Iterator LDA = ListaDatosAcad.iterator();

                    AcademicoBean obj4;

                    while (LDA.hasNext()) {
                        obj4 = (AcademicoBean) LDA.next();

                        objDatosA.setID_GRADO(obj4.getGRADO());
                        objDatosA.setPROMEDIO(obj4.getPROMEDIO());

                    }

                } else {
                    banGuarda = true;
                    objRenapo = renapo.consultaRenapo(objRenapo.getCONSULTA_CURP().toUpperCase());

                    objAspirante.setCONSULTA_CURP(objRenapo.getCONSULTA_CURP());
                    objAspirante.setNOMBRE_RENAPO(objRenapo.getNOMBRE_RENAPO());
                    objAspirante.setAPATERNO_RENAPO(objRenapo.getAPATERNO_RENAPO());
                    objAspirante.setAMATERNO_RENAPO(objRenapo.AMATERNO_RENAPO);
                    objAspirante.setFEC_NAC_RENAPO(objRenapo.getFEC_NAC_RENAPO());
                    objAspirante.setNACIONALIDAD_RENAPO(objRenapo.getNACIONALIDAD_RENAPO());
                    objAspirante.setENTIDAD_NACIMINETO_RENAPO(objRenapo.getENTIDAD_NACIMINETO_RENAPO());
                    objAspirante.setGENERO_RENAPO(objRenapo.getGENERO_RENAPO());

                    /*   //***********************consulta renapo anterior *****************
            
                  System.out.println ("MICURP ES: " + objRenapo.getCONSULTA_CURP());
                    service = new ConsultaDatosRenapo();
                    port = service.getConsultaRenapoPorCurpPort();
                    personas = port.consultaPorCurp(objRenapo.getCONSULTA_CURP());


            objAspirante.setCONSULTA_CURP(personas.getCurp());
            objAspirante.setNOMBRE_RENAPO(personas.getNombre());
            objAspirante.setAPATERNO_RENAPO(personas.getApellidoPaterno());
            objAspirante.setAMATERNO_RENAPO(personas.getApellidoMaterno());
            objAspirante.setFEC_NAC_RENAPO(personas.getFechaNacimientoAxu());
            objAspirante.setNACIONALIDAD_RENAPO(personas.getNacionalidad());
            objAspirante.setENTIDAD_NACIMINETO_RENAPO(personas.getCveEntidadNacimiento());
            
             if (personas.getSexo().equals("H")) {

                objAspirante.setGENERO_RENAPO("hombre");
            }

            if (personas.getSexo().equals("M")) {

                objAspirante.setGENERO_RENAPO("mujer");
            }

            
                     */
                    //consulta cct*********************
                    objDatosA.setCCTAUX(objDatosA.getCCT());

                    objDatosA = con.ConsultaCCT(objDatosA);

                    if (objDatosA == null) {

                        banFormAca = false;
                        addFieldError("ERRORCCT", "LA CCT QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

                    } else {
                        banFormAca = true;
                         Constantes.enviaMensajeConsola("Nivel que llega de timeline"+objDatosA.getNIVELCCT());
                        ListaGrados = con.ConsultaGrados(objDatosA.getNIVELCCT());
                        ListaPromedios = con.ConsultaPromedios();
                    }

                    Constantes.enviaMensajeConsola("ID_BECA_AUX: " + objdatos.getID_BECA_AUX());
                    Constantes.enviaMensajeConsola("lista Req: " + ListaReq.size());
                    Constantes.enviaMensajeConsola("lista ESTADOS: " + ListaEstadosCivil.size());
                }
                    
                    
                    
                    
                    
                    
                } else {
                     /* si el alumno que intenta registrar es diferente a la  beca y al  ciclo y si pasa las restricciones de letra y nivel 
                     no lo deja entrar por que ya esta registrado para otro */
                     addFieldError("AlumYaRegistrado", "la Curp que ingreso ya se encuentra registrada en otro programa ");
                     Constantes.enviaMensajeConsola("alumno ya registrado en otro programa ");
                     
                     return "ERROR";
                }
                
                
                

            } else {

                ListaEstadosCivil = con.ConsultaEstadosCivil();

                Constantes.enviaMensajeConsola("curp aspirante " + objRenapo.getCONSULTA_CURP());

                //consulta a tabla de aspirantes antes de renapo
                objAspirante.setID_CICLO(objRenapo.getID_CICLO());

                Constantes.enviaMensajeConsola("id_ciclo: " + objAspirante.getID_CICLO());

                ListaActualizaAspirante = con.ConsultaAspirante(objdatos, objRenapo);

                if (ListaActualizaAspirante.size() > 0) {
                    Iterator LAA = ListaActualizaAspirante.iterator();

                    AspiranteBean obj3;

                    while (LAA.hasNext()) {
                        obj3 = (AspiranteBean) LAA.next();

                        objAspirante.setCONSULTA_CURP(obj3.getCONSULTA_CURP());
                        objAspirante.setID_ASPIRANTE(obj3.getID_ASPIRANTE());
                        objAspirante.setNOMBRE_RENAPO(obj3.getNOMBRE_RENAPO());
                        objAspirante.setAMATERNO_RENAPO(obj3.getAMATERNO_RENAPO());
                        objAspirante.setAPATERNO_RENAPO(obj3.getAPATERNO_RENAPO());
                        objAspirante.setFEC_NAC_RENAPO(obj3.getFEC_NAC_RENAPO());
                        objAspirante.setNACIONALIDAD_RENAPO(obj3.getNACIONALIDAD_RENAPO());
                        objAspirante.setENTIDAD_NACIMINETO_RENAPO(obj3.getENTIDAD_NACIMINETO_RENAPO());
                        objAspirante.setGENERO_RENAPO(obj3.getGENERO_RENAPO());
                        objAspirante.setID_ESTADO_CIVIL(obj3.getID_ESTADO_CIVIL());
                        objAspirante.setDOMICILIO(obj3.getDOMICILIO());
                        objAspirante.setCALLE1(obj3.getCALLE1());
                        objAspirante.setCALLE2(obj3.getCALLE2());
                        objAspirante.setREFERENCIA(obj3.getREFERENCIA());
                        objAspirante.setCP(obj3.getCP());
                        ListaColonia = con.ConsultaColonia(objAspirante);
                        Iterator LC = ListaColonia.iterator();
                        ColoniasBean objg;

                        while (LC.hasNext()) {
                            objg = (ColoniasBean) LC.next();

                            objAspirante.setMUNICIPIO(objg.getMUNICIPIO());
                            objAspirante.setID_MUNICIPIO(objg.getID_MUNICIPIO());

                        }

                        objAspirante.setTELEFONO(obj3.getTELEFONO());
                        objAspirante.setCELULAR(obj3.getCELULAR());
                        objAspirante.setEMAIL(obj3.getEMAIL());

                    }

                    banColonia = true;
                    banActualiza = true;
                    banFormAca = true;

                    ListaDatosAcad = con.ConsultaDatosAca(objAspirante, objDatosA);

                    ListaGrados = con.ConsultaGrados(objDatosA.getNIVELCCT());
                    ListaPromedios = con.ConsultaPromedios();

                    Iterator LDA = ListaDatosAcad.iterator();

                    AcademicoBean obj4;

                    while (LDA.hasNext()) {
                        obj4 = (AcademicoBean) LDA.next();

                        objDatosA.setID_GRADO(obj4.getGRADO());
                        objDatosA.setPROMEDIO(obj4.getPROMEDIO());

                    }

                } else {
                    banGuarda = true;
                    objRenapo = renapo.consultaRenapo(objRenapo.getCONSULTA_CURP().toUpperCase());

                    objAspirante.setCONSULTA_CURP(objRenapo.getCONSULTA_CURP());
                    objAspirante.setNOMBRE_RENAPO(objRenapo.getNOMBRE_RENAPO());
                    objAspirante.setAPATERNO_RENAPO(objRenapo.getAPATERNO_RENAPO());
                    objAspirante.setAMATERNO_RENAPO(objRenapo.AMATERNO_RENAPO);
                    objAspirante.setFEC_NAC_RENAPO(objRenapo.getFEC_NAC_RENAPO());
                    objAspirante.setNACIONALIDAD_RENAPO(objRenapo.getNACIONALIDAD_RENAPO());
                    objAspirante.setENTIDAD_NACIMINETO_RENAPO(objRenapo.getENTIDAD_NACIMINETO_RENAPO());
                    objAspirante.setGENERO_RENAPO(objRenapo.getGENERO_RENAPO());

                    /*   //***********************consulta renapo anterior *****************
            
                  System.out.println ("MICURP ES: " + objRenapo.getCONSULTA_CURP());
                    service = new ConsultaDatosRenapo();
                    port = service.getConsultaRenapoPorCurpPort();
                    personas = port.consultaPorCurp(objRenapo.getCONSULTA_CURP());


            objAspirante.setCONSULTA_CURP(personas.getCurp());
            objAspirante.setNOMBRE_RENAPO(personas.getNombre());
            objAspirante.setAPATERNO_RENAPO(personas.getApellidoPaterno());
            objAspirante.setAMATERNO_RENAPO(personas.getApellidoMaterno());
            objAspirante.setFEC_NAC_RENAPO(personas.getFechaNacimientoAxu());
            objAspirante.setNACIONALIDAD_RENAPO(personas.getNacionalidad());
            objAspirante.setENTIDAD_NACIMINETO_RENAPO(personas.getCveEntidadNacimiento());
            
             if (personas.getSexo().equals("H")) {

                objAspirante.setGENERO_RENAPO("hombre");
            }

            if (personas.getSexo().equals("M")) {

                objAspirante.setGENERO_RENAPO("mujer");
            }

            
                     */
                    //consulta cct*********************
                    objDatosA.setCCTAUX(objDatosA.getCCT());

                    objDatosA = con.ConsultaCCT(objDatosA);

                    if (objDatosA == null) {

                        banFormAca = false;
                        addFieldError("ERRORCCT", "LA CCT QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

                    } else {
                        banFormAca = true;
                        ListaGrados = con.ConsultaGrados(objDatosA.getNIVELCCT());
                        ListaPromedios = con.ConsultaPromedios();
                    }

                    Constantes.enviaMensajeConsola("ID_BECA_AUX: " + objdatos.getID_BECA_AUX());
                    Constantes.enviaMensajeConsola("lista Req: " + ListaReq.size());
                    Constantes.enviaMensajeConsola("lista ESTADOS: " + ListaEstadosCivil.size());
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCP() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            ListaColonia = con.ConsultaColonia(objAspirante);

            if (ListaColonia.size() > 0) {

                banColonia = true;

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    objAspirante.setMUNICIPIO(objg.getMUNICIPIO());
                    objAspirante.setID_MUNICIPIO(objg.getID_MUNICIPIO());

                }

            } else {
                banColonia = false;
                addFieldError("NoCP", "Codigo Postal No encontrado favor de verificar");
            }

            Constantes.enviaMensajeConsola("lista Req: " + ListaRequisitos.size());

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String GuardaAspirante() {

        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            boolean NOMBRE_RENAPO = false;
            boolean APATERNO_RENAPO = false;
            boolean AMATERNO_RENAPO = false;
            boolean GENERO_RENAPO = false;
            boolean ENTIDAD_NACIMINETO_RENAPO = false;
            boolean FEC_NAC_RENAPO = false;
            boolean NACIONALIDAD_RENAPO = false;
            boolean CP = false;
            boolean MUNICIPIO = false;
            boolean ID_ESTADO_CIVIL = false;
            boolean DOMICILIO = false;
            boolean CALLE1 = false;
            boolean CALLE2 = false;
            boolean REFERENCIA = false;
            boolean COLONIA = false;
            boolean TELEFONO = false;
            boolean CELULAR = false;
            boolean EMAIL = false;
            boolean GRADO = false;
            boolean PROMEDIO = false;

            if (objAspirante.getNOMBRE_RENAPO().length() > 0) {
                NOMBRE_RENAPO = true;

            } else {
                NOMBRE_RENAPO = false;
                addFieldError("NOMA", "Debe registrar el nombre del alumno");
            }

            if (objAspirante.getAPATERNO_RENAPO().length() > 0) {
                APATERNO_RENAPO = true;

            } else {
                APATERNO_RENAPO = false;
                addFieldError("APA", "Debe registrar el apellido paterno del alumno");
            }

            if (objAspirante.getAMATERNO_RENAPO().length() > 0) {
                AMATERNO_RENAPO = true;

            } else {
                AMATERNO_RENAPO = false;
                addFieldError("AMA", "Debe registrar el apellido materno del alumno");
            }
            if (objAspirante.getFEC_NAC_RENAPO().length() > 0) {
                FEC_NAC_RENAPO = true;

            } else {
                FEC_NAC_RENAPO = false;
                addFieldError("FECNAN", "Debe registrar la fecha de nacimiento del alumno");
            }

            if (objAspirante.getNACIONALIDAD_RENAPO().length() > 0) {
                NACIONALIDAD_RENAPO = true;

            } else {
                NACIONALIDAD_RENAPO = false;
                addFieldError("NAC", "Debe registrar la nacionalidad del alumno");
            }
            if (objAspirante.getENTIDAD_NACIMINETO_RENAPO().length() > 0) {
                ENTIDAD_NACIMINETO_RENAPO = true;

            } else {
                ENTIDAD_NACIMINETO_RENAPO = false;
                addFieldError("ENTIDADNAC", "Debe registrar la entidad de nacimiento");
            }

            if (objAspirante.getGENERO_RENAPO().length() > 0) {
                GENERO_RENAPO = true;

            } else {
                GENERO_RENAPO = false;
                addFieldError("GENERO", "Debe registrar el genero del alumno");
            }

            if (objAspirante.getID_ESTADO_CIVIL().length() > 0) {
                ID_ESTADO_CIVIL = true;

            } else {
                ID_ESTADO_CIVIL = false;
                addFieldError("IDESTADO", "Debe Seleccionar un estado civil del alumno");
            }
            if (objAspirante.getDOMICILIO().length() > 0) {
                DOMICILIO = true;

            } else {
                DOMICILIO = false;
                addFieldError("DOMICILIO", "Debe registrar el domicilio del alumno");
            }

            if (objAspirante.getCALLE1().length() > 0) {
                CALLE1 = true;

            } else {
                CALLE1 = false;
                addFieldError("CALLE1", "Debe registrar entre calle del domicilio del alumno");
            }
            if (objAspirante.getCALLE2().length() > 0) {
                CALLE2 = true;

            } else {
                CALLE2 = false;
                addFieldError("CALLE2", "Debe registrar Y calle del domicilio del alumno");
            }

            if (objAspirante.getREFERENCIA().length() > 0) {
                REFERENCIA = true;

            } else {
                REFERENCIA = false;
                addFieldError("REFERENCIA", "Debe registrar otra referencia del domicilio del alumno");
            }

            if (objAspirante.getCP().length() > 0) {
                CP = true;

            } else {
                CP = false;
                addFieldError("CP", "Debe registrar un codigo postal del alumno");
            }
            if (objAspirante.getCOLONIA().length() > 0) {
                COLONIA = true;

            } else {
                COLONIA = false;
                addFieldError("COLONIA", "Debe registrar una colonia del alumno");
            }
            if (objAspirante.getMUNICIPIO().length() > 0) {
                MUNICIPIO = true;

            } else {
                MUNICIPIO = false;
                addFieldError("MUNICIPIO", "Debe registrar el municipio del alumno");
            }
            if (objAspirante.getTELEFONO().length() > 0) {
                TELEFONO = true;

            } else {
                TELEFONO = false;
                addFieldError("TEL", "Debe registrar un número telefonico del alumno,padre, madre o tutor.");
            }

            if (objAspirante.getCELULAR().length() > 0) {
                CELULAR = true;

            } else {
                CELULAR = false;
                addFieldError("CEL", "Debe registrar un número celular del alumno,padre, madre o tutor.");
            }

            if (objAspirante.getEMAIL().length() > 0) {
                EMAIL = true;

            } else {
                EMAIL = false;
                addFieldError("EMAIL", "Debe registrar un correo electrónico del alumno, padre, madre o tutor");
            }

            if (objDatosA.getID_GRADO().length() > 0) {
                GRADO = true;

            } else {
                GRADO = false;
                addFieldError("GRADO", "Debe registrar el grado que cursa el alumno");
            }

            if (objDatosA.getPROMEDIO().length() > 0) {
                PROMEDIO = true;

            } else {
                PROMEDIO = false;
                addFieldError("PROMEDIO", "Debe registrar el promedio del alumno");
            }

            if (NOMBRE_RENAPO && APATERNO_RENAPO && AMATERNO_RENAPO && GENERO_RENAPO && ENTIDAD_NACIMINETO_RENAPO && FEC_NAC_RENAPO && NACIONALIDAD_RENAPO && CP && MUNICIPIO
                    && ID_ESTADO_CIVIL && DOMICILIO && CALLE1 && CALLE2 && REFERENCIA && COLONIA && TELEFONO && CELULAR && EMAIL && GRADO && PROMEDIO) {

                con.GuardaDatosPersonales(objAspirante);

                objAspirante.setID_ASPIRANTE(con.ConsultaAspirante(objAspirante));

                objDatosA.setID_ASPIRANTE(objAspirante.getID_ASPIRANTE());
                objDatosA.setID_CICLO(objAspirante.getID_CICLO());

                con.GuardaDatosAcademicos(objDatosA);

            } else {
                return "ERROR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ActualizaAspirante() {

        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            boolean NOMBRE_RENAPO = false;
            boolean APATERNO_RENAPO = false;
            boolean AMATERNO_RENAPO = false;
            boolean GENERO_RENAPO = false;
            boolean ENTIDAD_NACIMINETO_RENAPO = false;
            boolean FEC_NAC_RENAPO = false;
            boolean NACIONALIDAD_RENAPO = false;
            boolean CP = false;
            boolean MUNICIPIO = false;
            boolean ID_ESTADO_CIVIL = false;
            boolean DOMICILIO = false;
            boolean CALLE1 = false;
            boolean CALLE2 = false;
            boolean REFERENCIA = false;
            boolean COLONIA = false;
            boolean TELEFONO = false;
            boolean CELULAR = false;
            boolean EMAIL = false;
            boolean GRADO = false;
            boolean PROMEDIO = false;

            if (objAspirante.getNOMBRE_RENAPO().length() > 0) {
                NOMBRE_RENAPO = true;

            } else {
                NOMBRE_RENAPO = false;
                addFieldError("NOMA", "Debe registrar el nombre del alumno");
            }

            if (objAspirante.getAPATERNO_RENAPO().length() > 0) {
                APATERNO_RENAPO = true;

            } else {
                APATERNO_RENAPO = false;
                addFieldError("APA", "Debe registrar el apellido paterno del alumno");
            }

            if (objAspirante.getAMATERNO_RENAPO().length() > 0) {
                AMATERNO_RENAPO = true;

            } else {
                AMATERNO_RENAPO = false;
                addFieldError("AMA", "Debe registrar el apellido materno del alumno");
            }
            if (objAspirante.getFEC_NAC_RENAPO().length() > 0) {
                FEC_NAC_RENAPO = true;

            } else {
                FEC_NAC_RENAPO = false;
                addFieldError("FECNAN", "Debe registrar la fecha de nacimiento del alumno");
            }

            if (objAspirante.getNACIONALIDAD_RENAPO().length() > 0) {
                NACIONALIDAD_RENAPO = true;

            } else {
                NACIONALIDAD_RENAPO = false;
                addFieldError("NAC", "Debe registrar la nacionalidad del alumno");
            }
            if (objAspirante.getENTIDAD_NACIMINETO_RENAPO().length() > 0) {
                ENTIDAD_NACIMINETO_RENAPO = true;

            } else {
                ENTIDAD_NACIMINETO_RENAPO = false;
                addFieldError("ENTIDADNAC", "Debe registrar la entidad de nacimiento");
            }

            if (objAspirante.getGENERO_RENAPO().length() > 0) {
                GENERO_RENAPO = true;

            } else {
                GENERO_RENAPO = false;
                addFieldError("GENERO", "Debe registrar el genero del alumno");
            }

            if (objAspirante.getID_ESTADO_CIVIL().length() > 0) {
                ID_ESTADO_CIVIL = true;

            } else {
                ID_ESTADO_CIVIL = false;
                addFieldError("IDESTADO", "Debe Seleccionar un estado civil del alumno");
            }
            if (objAspirante.getDOMICILIO().length() > 0) {
                DOMICILIO = true;

            } else {
                DOMICILIO = false;
                addFieldError("DOMICILIO", "Debe registrar el domicilio del alumno");
            }

            if (objAspirante.getCALLE1().length() > 0) {
                CALLE1 = true;

            } else {
                CALLE1 = false;
                addFieldError("CALLE1", "Debe registrar entre calle del domicilio del alumno");
            }
            if (objAspirante.getCALLE2().length() > 0) {
                CALLE2 = true;

            } else {
                CALLE2 = false;
                addFieldError("CALLE2", "Debe registrar Y calle del domicilio del alumno");
            }

            if (objAspirante.getREFERENCIA().length() > 0) {
                REFERENCIA = true;

            } else {
                REFERENCIA = false;
                addFieldError("REFERENCIA", "Debe registrar otra referencia del domicilio del alumno");
            }

            if (objAspirante.getCP().length() > 0) {
                CP = true;

            } else {
                CP = false;
                addFieldError("CP", "Debe registrar un codigo postal del alumno");
            }
            if (objAspirante.getCOLONIA().length() > 0) {
                COLONIA = true;

            } else {
                COLONIA = false;
                addFieldError("COLONIA", "Debe registrar una colonia del alumno");
            }
            if (objAspirante.getMUNICIPIO().length() > 0) {
                MUNICIPIO = true;

            } else {
                MUNICIPIO = false;
                addFieldError("MUNICIPIO", "Debe registrar el municipio del alumno");
            }
            if (objAspirante.getTELEFONO().length() > 0) {
                TELEFONO = true;

            } else {
                TELEFONO = false;
                addFieldError("TEL", "Debe registrar un número telefonico del alumno,padre, madre o tutor.");
            }

            if (objAspirante.getCELULAR().length() > 0) {
                CELULAR = true;

            } else {
                CELULAR = false;
                addFieldError("CEL", "Debe registrar un número celular del alumno,padre, madre o tutor.");
            }

            if (objAspirante.getEMAIL().length() > 0) {
                EMAIL = true;

            } else {
                EMAIL = false;
                addFieldError("EMAIL", "Debe registrar un correo electrónico del alumno, padre, madre o tutor");
            }

            if (objDatosA.getID_GRADO().length() > 0) {
                GRADO = true;

            } else {
                GRADO = false;
                addFieldError("GRADO", "Debe registrar el grado que cursa el alumno");
            }

            if (objDatosA.getPROMEDIO().length() > 0) {
                PROMEDIO = true;

            } else {
                PROMEDIO = false;
                addFieldError("PROMEDIO", "Debe registrar el promedio del alumno");
            }

            if (NOMBRE_RENAPO && APATERNO_RENAPO && AMATERNO_RENAPO && GENERO_RENAPO && ENTIDAD_NACIMINETO_RENAPO && FEC_NAC_RENAPO && NACIONALIDAD_RENAPO && CP && MUNICIPIO
                    && ID_ESTADO_CIVIL && DOMICILIO && CALLE1 && CALLE2 && REFERENCIA && COLONIA && TELEFONO && CELULAR && EMAIL && GRADO && PROMEDIO) {

                con.ActualizaDatosPersonales(objAspirante);

                objDatosA.setID_ASPIRANTE(objAspirante.getID_ASPIRANTE());
                objDatosA.setID_CICLO(objAspirante.getID_CICLO());

                con.ActualizaDatosAcademicos(objDatosA);

                ListaParentesco = con.ConsultaParentesco();

                objDatosP = con.ConsultaTutor(objDatosA);

                objDatosC = con.ConsultaCobe(objDatosA);

                if (objDatosP != null) {

                    banFormP = true;
                    banColoniaP = true;
                    banFormCobe = true;
                    banMuestraCobe = true;
                    banActualizaP = true;
                    VALCOB = "true";
                    objDatosC.setVALIDACHECK("true");

                }

            } else {
                return "ERROR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCurpP() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();
            consultaRenapo renapo = new consultaRenapo();

            Constantes.enviaMensajeConsola("cctaux: " + objDatosP.getCURPAUX());

            if (objDatosP.getCURPAUX().length() > 0 && objDatosP.getCURPAUX().length() == 18) {

                objRenapo = renapo.consultaRenapo(objDatosP.getCURPAUX());

                if (objRenapo == null) {

                    banFormP = false;
                    addFieldError("NOCURP", "LA CURP QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

                } else {

                    objDatosP.setCONSULTA_CURP_TU(objRenapo.getCONSULTA_CURP());
                    objDatosP.setNOMBRE_RENAPO_TU(objRenapo.getNOMBRE_RENAPO());
                    objDatosP.setAPATERNO_RENAPO_TU(objRenapo.getAPATERNO_RENAPO());
                    objDatosP.setAMATERNO_RENAPO_TU(objRenapo.getAMATERNO_RENAPO());
                    objDatosP.setFEC_NAC_RENAPO_TU(objRenapo.getFEC_NAC_RENAPO());
                    objDatosP.setGENERO_RENAPO_TU(objRenapo.getGENERO_RENAPO());
                    objDatosP.setNACIONALIDAD_RENAPO_TU(objRenapo.getNACIONALIDAD_RENAPO());
                    objDatosP.setENTIDAD_NACIMINETO_RENAPO_TU(objRenapo.getENTIDAD_NACIMINETO_RENAPO());

                    ListaParentesco = con.ConsultaParentesco();
                    ListaEstadosCivil = con.ConsultaEstadosCivil();

                    banFormP = true;
                    banGuardaP = true;

                }

            } else {

                addFieldError("ERRORCURPAUX", "POR FAVOR INGRESE UNA CURP VALIDA");

            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCPP() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();

            objAspirante.setCP(objDatosP.getCP_TU());

            ListaColonia = con.ConsultaColonia(objAspirante);

            if (ListaColonia.size() > 0) {

                banColoniaP = true;

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    objDatosP.setMUNICIPIO_TU(objg.getMUNICIPIO());
                    objDatosP.setID_MUNICIPIO_TU(objg.getID_MUNICIPIO());

                }

            } else {
                banColoniaP = false;
                addFieldError("NoCPP", "Codigo Postal No encontrado favor de verificar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String muestraFormCobe() {
        try {
            //validando session***********************************************************************

            Constantes.enviaMensajeConsola("si entro ");

            VALCOB = objDatosC.getVALIDACHECK();

            Constantes.enviaMensajeConsola("validacheck: " + VALCOB);

            if (objDatosC.getVALIDACHECK().equals("true")) {

                Constantes.enviaMensajeConsola("entro al if");
                banFormCobe = true;

            } else {
                banFormCobe = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCurpC() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();
            consultaRenapo renapo = new consultaRenapo();

            Constantes.enviaMensajeConsola("cctaux: " + objDatosC.getCURPAUX_CO());

            if (objDatosC.getCURPAUX_CO().length() > 0 && objDatosC.getCURPAUX_CO().length() == 18) {

                objDatosA.setID_CICLO(objAspirante.getID_CICLO());
                objDatosA.setCURP_AUX(objDatosC.getCURPAUX_CO());

                Constantes.enviaMensajeConsola("id aspirante: " + objDatosA.getID_ASPIRANTE());
                Constantes.enviaMensajeConsola("id ciclo: " + objDatosA.getID_CICLO());
                Constantes.enviaMensajeConsola("id curp: " + objDatosA.getCURP_AUX());

                VerificaCobe = con.ConsultaCobeXcurp(objDatosA);

                Constantes.enviaMensajeConsola("salio de la consulta" + VerificaCobe);

                if (VerificaCobe.size() > 0) {
                    Constantes.enviaMensajeConsola("entro al if : " + objDatosC.getCURPAUX_CO());

                    Iterator VC = VerificaCobe.iterator();

                    CobeneficiarioBean objg;

                    while (VC.hasNext()) {
                        objg = (CobeneficiarioBean) VC.next();

                        objDatosC.setCURP_CO(objg.getCURP_CO());
                        objDatosC.setNOMBRE_RENAPO_CO(objg.getNOMBRE_RENAPO_CO());
                        objDatosC.setAPATERNO_RENAPO_CO(objg.getAPATERNO_RENAPO_CO());
                        objDatosC.setAMATERNO_RENAPO_CO(objg.getAMATERNO_RENAPO_CO());
                        objDatosC.setFEC_NAC_RENAPO_CO(objg.getFEC_NAC_RENAPO_CO());
                        objDatosC.setGENERO_RENAPO_CO(objg.getGENERO_RENAPO_CO());
                        objDatosC.setNACIONALIDAD_RENAPO_CO(objg.getNACIONALIDAD_RENAPO_CO());
                        objDatosC.setENTIDAD_NACIMINETO_RENAPO_CO(objg.getENTIDAD_NACIMINETO_RENAPO_CO());
                        objDatosC.setID_ESTADO_CIVIL_CO(objg.getID_ESTADO_CIVIL_CO());
                        objDatosC.setPARENTESCO_CO(objg.getPARENTESCO_CO());

                    }

                    ListaParentesco = con.ConsultaParentesco();
                    ListaEstadosCivil = con.ConsultaEstadosCivil();

                    banMuestraCobe = true;

                } else {

                    Constantes.enviaMensajeConsola("entro a buscar a renapo con curp: " + objDatosC.getCURPAUX_CO());

                    // objDatosC.setCURPAUX_CO(objDatosA.getCURP_AUX());
                    objRenapo = renapo.consultaRenapo(objDatosC.getCURPAUX_CO());

                    objDatosC.setID_ESTADO_CIVIL_CO("");
                    objDatosC.setPARENTESCO_CO("");

                    Constantes.enviaMensajeConsola("regresa de renapo: " + objRenapo);

                    if (objRenapo != null) {

                        Constantes.enviaMensajeConsola("curp: " + objRenapo.getCONSULTA_CURP());
                        Constantes.enviaMensajeConsola("nombre: " + objRenapo.getNOMBRE_RENAPO());
                        Constantes.enviaMensajeConsola("app: " + objRenapo.getAPATERNO_RENAPO());
                        Constantes.enviaMensajeConsola("apm: " + objRenapo.getAMATERNO_RENAPO());
                        Constantes.enviaMensajeConsola("fec: " + objRenapo.getFEC_NAC_RENAPO());
                        Constantes.enviaMensajeConsola("genero: " + objRenapo.getGENERO_RENAPO());
                        Constantes.enviaMensajeConsola("nac: " + objRenapo.getNACIONALIDAD_RENAPO());
                        Constantes.enviaMensajeConsola("entidad: " + objRenapo.getENTIDAD_NACIMINETO_RENAPO());

                        objDatosC.setCURP_CO(objRenapo.getCONSULTA_CURP());
                        objDatosC.setNOMBRE_RENAPO_CO(objRenapo.getNOMBRE_RENAPO());
                        objDatosC.setAPATERNO_RENAPO_CO(objRenapo.getAPATERNO_RENAPO());
                        objDatosC.setAMATERNO_RENAPO_CO(objRenapo.getAMATERNO_RENAPO());
                        objDatosC.setFEC_NAC_RENAPO_CO(objRenapo.getFEC_NAC_RENAPO());
                        objDatosC.setGENERO_RENAPO_CO(objRenapo.getGENERO_RENAPO());
                        objDatosC.setNACIONALIDAD_RENAPO_CO(objRenapo.getNACIONALIDAD_RENAPO());
                        objDatosC.setENTIDAD_NACIMINETO_RENAPO_CO(objRenapo.getENTIDAD_NACIMINETO_RENAPO());

                        Constantes.enviaMensajeConsola("paso asignacion " + objDatosC.getCURP_CO());

                        ListaParentesco = con.ConsultaParentesco();
                        ListaEstadosCivil = con.ConsultaEstadosCivil();

                        banMuestraCobe = true;

                    } else {

                        banMuestraCobe = false;
                        addFieldError("NOCURP", "LA CURP QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

                    }

                }

            } else {

                addFieldError("ERRORCURPAUX", "POR FAVOR INGRESE UNA CURP VALIDA");

            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String GuardaTutorCobe() {

        try {
            //validando session***********************************************************************

            Constantes.enviaMensajeConsola("entro a metodo de guardar");

            ConsultasBusiness con = new ConsultasBusiness();

            boolean NOMBRE_TU = false;
            boolean APATERNO_TU = false;
            boolean AMATERNO_TU = false;
            boolean GENERO_TU = false;
            boolean ENTIDAD_NACIMINETO_TU = false;
            boolean FEC_NAC_TU = false;
            boolean NACIONALIDAD_TU = false;
            boolean CP_TU = false;
            boolean MUNICIPIO_TU = false;
            boolean ID_PARENTESCO_TU = false;
            boolean DOMICILIO_TU = false;
            boolean CALLE1_TU = false;
            boolean CALLE2_TU = false;
            boolean REFERENCIA_TU = false;
            boolean COLONIA_TU = false;
            boolean TELEFONO_TU = false;
            boolean CELULAR_TU = false;
            boolean EMAIL_TU = false;
            boolean ID_ESTADO_CIVIL_TU = false;

            boolean NOMBRE_CO = false;
            boolean APATERNO_CO = false;
            boolean AMATERNO_CO = false;
            boolean GENERO_CO = false;
            boolean ENTIDAD_NACIMINETO_CO = false;
            boolean FEC_NAC_CO = false;
            boolean NACIONALIDAD_CO = false;
            boolean ID_PARENTESCO_CO = false;
            boolean ID_ESTADO_CIVIL_CO = false;

            if (objDatosP.getNOMBRE_RENAPO_TU().length() > 0) {
                NOMBRE_TU = true;

            } else {
                NOMBRE_TU = false;
                addFieldError("NOMT", "Debe registrar el nombre del tutor");
            }

            if (objDatosP.getAPATERNO_RENAPO_TU().length() > 0) {
                APATERNO_TU = true;

            } else {
                APATERNO_TU = false;
                addFieldError("APT", "Debe registrar el apellido paterno del tutor");
            }

            if (objDatosP.getAMATERNO_RENAPO_TU().length() > 0) {
                AMATERNO_TU = true;

            } else {
                AMATERNO_TU = false;
                addFieldError("AMT", "Debe registrar el apellido materno del tutor");
            }
            if (objDatosP.getFEC_NAC_RENAPO_TU().length() > 0) {
                FEC_NAC_TU = true;

            } else {
                FEC_NAC_TU = false;
                addFieldError("FECNANT", "Debe registrar la fecha de nacimiento del tutor");
            }

            if (objDatosP.getNACIONALIDAD_RENAPO_TU().length() > 0) {
                NACIONALIDAD_TU = true;

            } else {
                NACIONALIDAD_TU = false;
                addFieldError("NACT", "Debe registrar la nacionalidad del tutor");
            }
            if (objDatosP.getENTIDAD_NACIMINETO_RENAPO_TU().length() > 0) {
                ENTIDAD_NACIMINETO_TU = true;

            } else {
                ENTIDAD_NACIMINETO_TU = false;
                addFieldError("ENTIDADNACT", "Debe registrar la entidad de nacimiento del tutor");
            }

            if (objDatosP.getGENERO_RENAPO_TU().length() > 0) {
                GENERO_TU = true;

            } else {
                GENERO_TU = false;
                addFieldError("GENEROT", "Debe registrar el genero del tutor");
            }

            if (objDatosP.getPARENTESCO().length() > 0) {
                ID_PARENTESCO_TU = true;

            } else {
                ID_PARENTESCO_TU = false;
                addFieldError("PARENTESCOT", "Debe registrar el parentesco del tutor con el alumno");
            }

            if (objDatosP.getDOMICILIO_TU().length() > 0) {
                DOMICILIO_TU = true;

            } else {
                DOMICILIO_TU = false;
                addFieldError("DOMICILIOT", "Debe registrar el domicilio del tutor");
            }

            if (objDatosP.getCALLE1_TU().length() > 0) {
                CALLE1_TU = true;

            } else {
                CALLE1_TU = false;
                addFieldError("CALLE1T", "Debe registrar entre calle del domicilio del tutor");
            }
            if (objDatosP.getCALLE2_TU().length() > 0) {
                CALLE2_TU = true;

            } else {
                CALLE2_TU = false;
                addFieldError("CALLE2T", "Debe registrar Y calle del domicilio del tutor");
            }

            if (objDatosP.getREFERENCIA_TU().length() > 0) {
                REFERENCIA_TU = true;

            } else {
                REFERENCIA_TU = false;
                addFieldError("REFERENCIAT", "Debe registrar otra referencia del domicilio del tutor");
            }

            if (objDatosP.getCP_TU().length() > 0) {
                CP_TU = true;

            } else {
                CP_TU = false;
                addFieldError("CPT", "Debe registrar un codigo postal del tutor");
            }

            if (objDatosP.getCOLONIA_TU().length() > 0) {
                COLONIA_TU = true;

            } else {
                COLONIA_TU = false;
                addFieldError("COLONIAT", "Debe registrar una colonia del tutor");
            }
            Constantes.enviaMensajeConsola("hasta aqui si");

            if (objDatosP.getMUNICIPIO_TU().length() > 0) {
                MUNICIPIO_TU = true;

            } else {
                MUNICIPIO_TU = false;
                addFieldError("MUNICIPIOT", "Debe registrar el municipio del tutor");
            }
            if (objDatosP.getTELEFONO_TU().length() > 0) {
                TELEFONO_TU = true;

            } else {
                TELEFONO_TU = false;
                addFieldError("TELT", "Debe registrar un número telefonico del tutor");
            }

            if (objDatosP.getCELULAR_TU().length() > 0) {
                CELULAR_TU = true;

            } else {
                CELULAR_TU = false;
                addFieldError("CELT", "Debe registrar un número celular del tutor");
            }

            if (objDatosP.getEMAIL_TU().length() > 0) {
                EMAIL_TU = true;

            } else {
                EMAIL_TU = false;
                addFieldError("EMAILT", "Debe registrar un correo electronico del tutor");
            }

            if (objDatosP.getID_ESTADO_CIVIL_TU().length() > 0) {
                ID_ESTADO_CIVIL_TU = true;

            } else {
                ID_ESTADO_CIVIL_TU = false;
                addFieldError("IDESTADOP", "Debe seleccionar un estado civil del tutor");
            }

            if (NOMBRE_TU && APATERNO_TU && AMATERNO_TU && GENERO_TU && ENTIDAD_NACIMINETO_TU && FEC_NAC_TU && NACIONALIDAD_TU && CP_TU && MUNICIPIO_TU
                    && ID_PARENTESCO_TU && DOMICILIO_TU && CALLE1_TU && CALLE2_TU && REFERENCIA_TU && COLONIA_TU && TELEFONO_TU && CELULAR_TU && EMAIL_TU && ID_ESTADO_CIVIL_TU) {

                if (objDatosC.getVALIDACHECK().equals("true")) {

                    Constantes.enviaMensajeConsola("entro a if de true");

                    if (objDatosC.getNOMBRE_RENAPO_CO().length() > 0) {
                        NOMBRE_CO = true;

                    } else {
                        NOMBRE_CO = false;
                        addFieldError("NOMC", "Debe registrar el nombre del cobeneficiario");
                    }

                    if (objDatosC.getAPATERNO_RENAPO_CO().length() > 0) {
                        APATERNO_CO = true;

                    } else {
                        APATERNO_CO = false;
                        addFieldError("APAC", "Debe registrar el apellido paterno del cobeneficiario");
                    }

                    if (objDatosC.getAMATERNO_RENAPO_CO().length() > 0) {
                        AMATERNO_CO = true;

                    } else {
                        AMATERNO_CO = false;
                        addFieldError("AMAC", "Debe registrar el apellido materno del cobeneficiario");
                    }
                    if (objDatosC.getFEC_NAC_RENAPO_CO().length() > 0) {
                        FEC_NAC_CO = true;

                    } else {
                        FEC_NAC_CO = false;
                        addFieldError("FECNANT", "Debe registrar la fecha de nacimiento del cobeneficiario");
                    }

                    if (objDatosC.getNACIONALIDAD_RENAPO_CO().length() > 0) {
                        NACIONALIDAD_CO = true;

                    } else {
                        NACIONALIDAD_CO = false;
                        addFieldError("NACC", "Debe registrar la nacionalidad del cobeneficiario");
                    }
                    if (objDatosC.getENTIDAD_NACIMINETO_RENAPO_CO().length() > 0) {
                        ENTIDAD_NACIMINETO_CO = true;

                    } else {
                        ENTIDAD_NACIMINETO_CO = false;
                        addFieldError("ENTIDADNACC", "Debe registrar la entidad de nacimiento del cobeneficiario");
                    }

                    if (objDatosC.getGENERO_RENAPO_CO().length() > 0) {
                        GENERO_CO = true;

                    } else {
                        GENERO_CO = false;
                        addFieldError("GENEROC", "Debe registrar el genero del cobeneficiario");
                    }

                    if (objDatosC.getPARENTESCO_CO().length() > 0) {
                        ID_PARENTESCO_CO = true;

                    } else {
                        ID_PARENTESCO_CO = false;
                        addFieldError("PARENTESCOC", "Debe registrar el parentesco del cobeneficiario con el alumno");
                    }

                    if (objDatosC.getID_ESTADO_CIVIL_CO().length() > 0) {
                        ID_ESTADO_CIVIL_CO = true;

                    } else {
                        ID_ESTADO_CIVIL_CO = false;
                        addFieldError("IDESTADOC", "Debe registrar el estado civil del cobeneficiarion");
                    }

                    if (NOMBRE_CO && APATERNO_CO && AMATERNO_CO && GENERO_CO && ENTIDAD_NACIMINETO_CO && FEC_NAC_CO && NACIONALIDAD_CO && ID_PARENTESCO_CO && ID_ESTADO_CIVIL_CO) {

                        objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                        objDatosP.setID_ASPIRANTE_TU(objDatosA.getID_ASPIRANTE());
                        objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                        objDatosP.setID_CICLO_TU(objAspirante.getID_CICLO());
                        objDatosP.setSTATUS("1");

                        Constantes.enviaMensajeConsola("llego a los metodos de guardar");

                        con.GuardaDatosTutor(objDatosP);
                        con.GuardaDatosCobeneficiario(objDatosC);

                        banGuardaE = true;

                    } else {
                        return "ERROR";
                    }

                } else {

                    objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                    objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                    objDatosC.setCURP_CO(objDatosP.getCONSULTA_CURP_TU());
                    objDatosC.setNOMBRE_RENAPO_CO(objDatosP.getNOMBRE_RENAPO_TU());
                    objDatosC.setAPATERNO_RENAPO_CO(objDatosP.getAPATERNO_RENAPO_TU());
                    objDatosC.setAMATERNO_RENAPO_CO(objDatosP.getAMATERNO_RENAPO_TU());
                    objDatosC.setFEC_NAC_RENAPO_CO(objDatosP.getFEC_NAC_RENAPO_TU());
                    objDatosC.setENTIDAD_NACIMINETO_RENAPO_CO(objDatosP.getENTIDAD_NACIMINETO_RENAPO_TU());
                    objDatosC.setNACIONALIDAD_RENAPO_CO(objDatosP.getNACIONALIDAD_RENAPO_TU());
                    objDatosC.setPARENTESCO_CO(objDatosP.getPARENTESCO());
                    objDatosC.setID_ESTADO_CIVIL_CO(objDatosP.getID_ESTADO_CIVIL_TU());
                    objDatosC.setID_CICLO_CO(objDatosP.getID_CICLO_TU());
                    objDatosC.setGENERO_RENAPO_CO(objDatosP.getGENERO_RENAPO_TU());

                    objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                    objDatosP.setID_ASPIRANTE_TU(objDatosA.getID_ASPIRANTE());
                    objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                    objDatosP.setID_CICLO_TU(objAspirante.getID_CICLO());
                    objDatosP.setSTATUS("1");

                    con.GuardaDatosTutor(objDatosP);
                    con.GuardaDatosCobeneficiario(objDatosC);

                    banGuardaE = true;

                }
                
                
                ListaPreguntas=con.ConsultaPreguntas(objdatos.getID_BECA_AUX());

                ListaRespuestas = con.ConsultaRespuestas();

            } else {
                return "ERROR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ActualizaDatosTutor() {

        try {
            //validando session***********************************************************************

            Constantes.enviaMensajeConsola("entro a metodo de guardar");

            ConsultasBusiness con = new ConsultasBusiness();

            boolean NOMBRE_TU = false;
            boolean APATERNO_TU = false;
            boolean AMATERNO_TU = false;
            boolean GENERO_TU = false;
            boolean ENTIDAD_NACIMINETO_TU = false;
            boolean FEC_NAC_TU = false;
            boolean NACIONALIDAD_TU = false;
            boolean CP_TU = false;
            boolean MUNICIPIO_TU = false;
            boolean ID_PARENTESCO_TU = false;
            boolean DOMICILIO_TU = false;
            boolean CALLE1_TU = false;
            boolean CALLE2_TU = false;
            boolean REFERENCIA_TU = false;
            boolean COLONIA_TU = false;
            boolean TELEFONO_TU = false;
            boolean CELULAR_TU = false;
            boolean EMAIL_TU = false;
            boolean ID_ESTADO_CIVIL_TU = false;

            boolean NOMBRE_CO = false;
            boolean APATERNO_CO = false;
            boolean AMATERNO_CO = false;
            boolean GENERO_CO = false;
            boolean ENTIDAD_NACIMINETO_CO = false;
            boolean FEC_NAC_CO = false;
            boolean NACIONALIDAD_CO = false;
            boolean ID_PARENTESCO_CO = false;
            boolean ID_ESTADO_CIVIL_CO = false;

            if (objDatosP.getNOMBRE_RENAPO_TU().length() > 0) {
                NOMBRE_TU = true;

            } else {
                NOMBRE_TU = false;
                addFieldError("NOMT", "Debe registrar el nombre del tutor");
            }

            if (objDatosP.getAPATERNO_RENAPO_TU().length() > 0) {
                APATERNO_TU = true;

            } else {
                APATERNO_TU = false;
                addFieldError("APT", "Debe registrar el apellido paterno del tutor");
            }

            if (objDatosP.getAMATERNO_RENAPO_TU().length() > 0) {
                AMATERNO_TU = true;

            } else {
                AMATERNO_TU = false;
                addFieldError("AMT", "Debe registrar el apellido materno del tutor");
            }
            if (objDatosP.getFEC_NAC_RENAPO_TU().length() > 0) {
                FEC_NAC_TU = true;

            } else {
                FEC_NAC_TU = false;
                addFieldError("FECNANT", "Debe registrar la fecha de nacimiento del tutor");
            }

            if (objDatosP.getNACIONALIDAD_RENAPO_TU().length() > 0) {
                NACIONALIDAD_TU = true;

            } else {
                NACIONALIDAD_TU = false;
                addFieldError("NACT", "Debe registrar la nacionalidad del tutor");
            }
            if (objDatosP.getENTIDAD_NACIMINETO_RENAPO_TU().length() > 0) {
                ENTIDAD_NACIMINETO_TU = true;

            } else {
                ENTIDAD_NACIMINETO_TU = false;
                addFieldError("ENTIDADNACT", "Debe registrar la entidad de nacimiento del tutor");
            }

            if (objDatosP.getGENERO_RENAPO_TU().length() > 0) {
                GENERO_TU = true;

            } else {
                GENERO_TU = false;
                addFieldError("GENEROT", "Debe registrar el genero del tutor");
            }

            if (objDatosP.getPARENTESCO().length() > 0) {
                ID_PARENTESCO_TU = true;

            } else {
                ID_PARENTESCO_TU = false;
                addFieldError("PARENTESCOT", "Debe registrar el parentesco del tutor con el alumno");
            }

            if (objDatosP.getDOMICILIO_TU().length() > 0) {
                DOMICILIO_TU = true;

            } else {
                DOMICILIO_TU = false;
                addFieldError("DOMICILIOT", "Debe registrar el domicilio del tutor");
            }

            if (objDatosP.getCALLE1_TU().length() > 0) {
                CALLE1_TU = true;

            } else {
                CALLE1_TU = false;
                addFieldError("CALLE1T", "Debe registrar entre calle del domicilio del tutor");
            }
            if (objDatosP.getCALLE2_TU().length() > 0) {
                CALLE2_TU = true;

            } else {
                CALLE2_TU = false;
                addFieldError("CALLE2T", "Debe registrar Y calle del domicilio del tutor");
            }

            if (objDatosP.getREFERENCIA_TU().length() > 0) {
                REFERENCIA_TU = true;

            } else {
                REFERENCIA_TU = false;
                addFieldError("REFERENCIAT", "Debe registrar otra referencia del domicilio del tutor");
            }

            if (objDatosP.getCP_TU().length() > 0) {
                CP_TU = true;

            } else {
                CP_TU = false;
                addFieldError("CPT", "Debe registrar un codigo postal del tutor");
            }

            if (objDatosP.getCOLONIA_TU().length() > 0) {
                COLONIA_TU = true;

            } else {
                COLONIA_TU = false;
                addFieldError("COLONIAT", "Debe registrar una colonia del tutor");
            }
            Constantes.enviaMensajeConsola("hasta aqui si");

            if (objDatosP.getMUNICIPIO_TU().length() > 0) {
                MUNICIPIO_TU = true;

            } else {
                MUNICIPIO_TU = false;
                addFieldError("MUNICIPIOT", "Debe registrar el municipio del tutor");
            }
            if (objDatosP.getTELEFONO_TU().length() > 0) {
                TELEFONO_TU = true;

            } else {
                TELEFONO_TU = false;
                addFieldError("TELT", "Debe registrar un número telefonico del tutor");
            }

            if (objDatosP.getCELULAR_TU().length() > 0) {
                CELULAR_TU = true;

            } else {
                CELULAR_TU = false;
                addFieldError("CELT", "Debe registrar un número celular del tutor");
            }

            if (objDatosP.getEMAIL_TU().length() > 0) {
                EMAIL_TU = true;

            } else {
                EMAIL_TU = false;
                addFieldError("EMAILT", "Debe registrar un correo electronico del tutor");
            }

            if (objDatosP.getID_ESTADO_CIVIL_TU().length() > 0) {
                ID_ESTADO_CIVIL_TU = true;

            } else {
                ID_ESTADO_CIVIL_TU = false;
                addFieldError("IDESTADOP", "Debe seleccionar un estado civil del tutor");
            }

            if (NOMBRE_TU && APATERNO_TU && AMATERNO_TU && GENERO_TU && ENTIDAD_NACIMINETO_TU && FEC_NAC_TU && NACIONALIDAD_TU && CP_TU && MUNICIPIO_TU
                    && ID_PARENTESCO_TU && DOMICILIO_TU && CALLE1_TU && CALLE2_TU && REFERENCIA_TU && COLONIA_TU && TELEFONO_TU && CELULAR_TU && EMAIL_TU && ID_ESTADO_CIVIL_TU) {

                if (objDatosC.getVALIDACHECK().equals("true")) {

                    Constantes.enviaMensajeConsola("entro a if de true");

                    if (objDatosC.getNOMBRE_RENAPO_CO().length() > 0) {
                        NOMBRE_CO = true;

                    } else {
                        NOMBRE_CO = false;
                        addFieldError("NOMC", "Debe registrar el nombre del cobeneficiario");
                    }

                    if (objDatosC.getAPATERNO_RENAPO_CO().length() > 0) {
                        APATERNO_CO = true;

                    } else {
                        APATERNO_CO = false;
                        addFieldError("APAC", "Debe registrar el apellido paterno del cobeneficiario");
                    }

                    if (objDatosC.getAMATERNO_RENAPO_CO().length() > 0) {
                        AMATERNO_CO = true;

                    } else {
                        AMATERNO_CO = false;
                        addFieldError("AMAC", "Debe registrar el apellido materno del cobeneficiario");
                    }
                    if (objDatosC.getFEC_NAC_RENAPO_CO().length() > 0) {
                        FEC_NAC_CO = true;

                    } else {
                        FEC_NAC_CO = false;
                        addFieldError("FECNANT", "Debe registrar la fecha de nacimiento del cobeneficiario");
                    }

                    if (objDatosC.getNACIONALIDAD_RENAPO_CO().length() > 0) {
                        NACIONALIDAD_CO = true;

                    } else {
                        NACIONALIDAD_CO = false;
                        addFieldError("NACC", "Debe registrar la nacionalidad del cobeneficiario");
                    }
                    if (objDatosC.getENTIDAD_NACIMINETO_RENAPO_CO().length() > 0) {
                        ENTIDAD_NACIMINETO_CO = true;

                    } else {
                        ENTIDAD_NACIMINETO_CO = false;
                        addFieldError("ENTIDADNACC", "Debe registrar la entidad de nacimiento del cobeneficiario");
                    }

                    if (objDatosC.getGENERO_RENAPO_CO().length() > 0) {
                        GENERO_CO = true;

                    } else {
                        GENERO_CO = false;
                        addFieldError("GENEROC", "Debe registrar el genero del cobeneficiario");
                    }

                    if (objDatosC.getPARENTESCO_CO().length() > 0) {
                        ID_PARENTESCO_CO = true;

                    } else {
                        ID_PARENTESCO_CO = false;
                        addFieldError("PARENTESCOC", "Debe registrar el parentesco del cobeneficiario con el alumno");
                    }

                    if (objDatosC.getID_ESTADO_CIVIL_CO().length() > 0) {
                        ID_ESTADO_CIVIL_CO = true;

                    } else {
                        ID_ESTADO_CIVIL_CO = false;
                        addFieldError("IDESTADOC", "Debe registrar el estado civil del cobeneficiarion");
                    }

                    if (NOMBRE_CO && APATERNO_CO && AMATERNO_CO && GENERO_CO && ENTIDAD_NACIMINETO_CO && FEC_NAC_CO && NACIONALIDAD_CO && ID_PARENTESCO_CO && ID_ESTADO_CIVIL_CO) {

                        objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                        objDatosP.setID_ASPIRANTE_TU(objDatosA.getID_ASPIRANTE());
                        objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                        objDatosP.setID_CICLO_TU(objAspirante.getID_CICLO());
                        objDatosP.setSTATUS("1");

                        Constantes.enviaMensajeConsola("llego a los metodos de guardar");

                        con.ActualizaDatosTutor(objDatosP);
                        con.ActualizaDatosCobeneficiario(objDatosC);

                    } else {
                        return "ERROR";
                    }

                } else {

                    objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                    objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                    objDatosC.setCURP_CO(objDatosP.getCONSULTA_CURP_TU());
                    objDatosC.setNOMBRE_RENAPO_CO(objDatosP.getNOMBRE_RENAPO_TU());
                    objDatosC.setAPATERNO_RENAPO_CO(objDatosP.getAPATERNO_RENAPO_TU());
                    objDatosC.setAMATERNO_RENAPO_CO(objDatosP.getAMATERNO_RENAPO_TU());
                    objDatosC.setFEC_NAC_RENAPO_CO(objDatosP.getFEC_NAC_RENAPO_TU());
                    objDatosC.setENTIDAD_NACIMINETO_RENAPO_CO(objDatosP.getENTIDAD_NACIMINETO_RENAPO_TU());
                    objDatosC.setNACIONALIDAD_RENAPO_CO(objDatosP.getNACIONALIDAD_RENAPO_TU());
                    objDatosC.setPARENTESCO_CO(objDatosP.getPARENTESCO());
                    objDatosC.setID_ESTADO_CIVIL_CO(objDatosP.getID_ESTADO_CIVIL_TU());
                    objDatosC.setID_CICLO_CO(objDatosP.getID_CICLO_TU());
                    objDatosC.setGENERO_RENAPO_CO(objDatosP.getGENERO_RENAPO_TU());

                    objDatosC.setID_ASPIRANTE_CO(objDatosA.getID_ASPIRANTE());
                    objDatosP.setID_ASPIRANTE_TU(objDatosA.getID_ASPIRANTE());
                    objDatosC.setID_CICLO_CO(objAspirante.getID_CICLO());
                    objDatosP.setID_CICLO_TU(objAspirante.getID_CICLO());
                    objDatosP.setSTATUS("1");

                    con.ActualizaDatosTutor(objDatosP);
                    con.ActualizaDatosCobeneficiario(objDatosC);
                }

                objDatosE = con.consultaSocioEconomico(objDatosA.getID_ASPIRANTE(), objAspirante.getID_CICLO());
                ListaRespuestas = con.ConsultaRespuestas();
                ListaPreguntas=con.ConsultaPreguntas(objdatos.getID_BECA_AUX());
                ListaContestados=con.ConsultaRes_Preg(objDatosA.getID_ASPIRANTE(), objAspirante.getID_CICLO());

                if (objDatosE != null) {

                    Constantes.enviaMensajeConsola("esto es lo que trae archivo: " + objDatosE.getARCHIVO_INGRESO());

                    if (objDatosE.getARCHIVO_INGRESO().equals("SINARCHIVO")) {
                        Constantes.enviaMensajeConsola("entro al else de archivo");
                        valingreso = "false";
                        objDatosE.setVALIDACHECK("false");
                        banFormIngresos = false;

                    } else {
                        Constantes.enviaMensajeConsola("Entro al if archivo lleno");
                        banMuestraArchivo = true;

                    }

                    banActualizaE = true;

                } else {
                    banGuardaE = true;
                }

            } else {
                return "ERROR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String muestraFormIngreso() {
        try {
            //validando session***********************************************************************

            Constantes.enviaMensajeConsola("si entro ");

            valingreso = objDatosE.getVALIDACHECK();

            if (objDatosE.getVALIDACHECK().equals("true")) {

                Constantes.enviaMensajeConsola("entro al if");
                banFormIngresos = true;

            } else {
                banFormIngresos = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public void validate2() {
        try {
//agregando la validacion de tipo de archivo...

            if (archiFileName != null) {
                Constantes.enviaMensajeConsola("--EL ARCHIVO ES .... " + archiFileName);
//Constantes.enviaMensajeConsola("--entre a validar el tipo de arcivo.... " + sitio.getTIP_MSJ());
                if (!(archiFileName.contains(".pdf"))) {
                    archiFileName = "";
                    addFieldError("archi", "*** La extension del archivo no es aceptada debe ser (pdf) ***");
                    banT = true;

                }
                if (archiFileName.contains(" ")) {

                    addFieldError("archi", "*** No se permiten espacios en el nombre del archivo, favor de remplazarlos por _ (guion bajo) ***");
                    banT = true;

                }
                //if (archiFileName.length() > 2097152 ) 

                if (5097152 <= FileUtils.sizeOf(archi)) {
                    addFieldError("archi", "*** No se permiten archivos que pesen mas de 5Mbs de subir un archivo menor de 5Mbs ***");
                    banT = true;

                }

            } else {

                banT = true;
                addFieldError("archi", "*** Debe Seleccionar un archivo PDF. ***");

            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);

        }

    }

    public String GuardaSocioEconomico() {

        try {

            utilidades.Constantes.enviaMensajeConsola("LLEGA AL METODO guardardocumento");

            ConsultasBusiness con = new ConsultasBusiness();

            String ruta = null;
            
            
            Constantes.enviaMensajeConsola("lista contestados: "+ListaContestados.size());

            boolean archivo;
            boolean RESPUESTA;
            
            Constantes.enviaMensajeConsola("lista contestados: "+ListaContestados.size());
             int contador=0;

            for (int i = 0; i < ListaContestados.size(); i++) {
                
                objRespuesta.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                objRespuesta.setRESPUESTA(ListaContestados.get(i).getRESPUESTA());
                
                Constantes.enviaMensajeConsola("pregunta: "+objRespuesta.getID_PREGUNTA());
                 Constantes.enviaMensajeConsola("respuesta: "+objRespuesta.getRESPUESTA());
                

                if (objRespuesta.getRESPUESTA().length() == 0 || objRespuesta.getRESPUESTA().contains("-1")) {
                    contador = contador + 1;
                    break;
                }
                         
            }
            
            if (contador > 0) {
                RESPUESTA = false;
                addFieldError("ERRORPREG", "Debes responder todas las preguntas");
                return "ERROR";

            } else {
                RESPUESTA = true;

            }

            if (objDatosE.getVALIDACHECK().equals("true")) {

                validate2();
                utilidades.Constantes.enviaMensajeConsola("sali de validate");

                if (banT) {

                    archivo = false;

                } else {
                    archivo = true;
                }

            } else {
                Constantes.enviaMensajeConsola("entro a validacheck= false");
                banT = true;
                archivo = true;
            }

            if (RESPUESTA && archivo) {

                Constantes.enviaMensajeConsola("PASO VALIDACIONES");

                objDatosE.setID_ASPIRANTE(objDatosA.getID_ASPIRANTE());
                objDatosE.setID_CICLO(objAspirante.getID_CICLO());
                objDatosE.setID_BECA(objdatos.getID_BECA_AUX());
                
                objRespuesta.setID_ASPIRANTE(objDatosA.getID_ASPIRANTE());
                objRespuesta.setCICLO(objAspirante.getID_CICLO());
                objRespuesta.setID_BECA(objdatos.getID_BECA_AUX());
                
                
                  conecta = con.crearConexion();
                //statement
                objConexion = con.crearStatement(conecta);
                        
                for (int i = 0; i < ListaContestados.size(); i++) {              
                    objRespuesta.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                    objRespuesta.setRESPUESTA(ListaContestados.get(i).getRESPUESTA()); 
                    
                    con.GuardaRespuestas(conecta, objPreConexion, objRespuesta);

                }

                Constantes.enviaMensajeConsola("paso asignaciones");

                if (banT == false) {
                    Constantes.enviaMensajeConsola("entro a banT=false");

                    if (archiFileName != null) {

                        archiFileName = "prueba" + archiFileName;
                        objDatosE.setARCHIVO_INGRESO(archiFileName);
                        ruta = Constantes.rutaArch + archiFileName;
                        Constantes.enviaMensajeConsola(ruta);
                        File newarch = new File(ruta);

                        FileUtils.copyFile(archi, newarch);

                        //FileUtils.sizeOf(archi)
                        //AQUI VA METODO GUARDAR
                        con.GuardaSocioeconomico(objDatosE);
                        ConsultaTodo(objDatosA.getID_ASPIRANTE(), objAspirante.getCONSULTA_CURP(), objAspirante.ID_CICLO, objdatos.getID_BECA_AUX());

                        Constantes.enviaMensajeConsola("guardado con archivo");

                        archiFileName = null;
                    }
                } else {
                    Constantes.enviaMensajeConsola("guarda sin archivo");
                    objDatosE.setARCHIVO_INGRESO("SINARCHIVO");
                    con.GuardaSocioeconomico(objDatosE);
                    ConsultaTodo(objDatosA.getID_ASPIRANTE(), objAspirante.getCONSULTA_CURP(), objAspirante.ID_CICLO, objdatos.getID_BECA_AUX());

                    return "SUCCESS";
                }
                cierraConexiones();

            } else {
                return "ERROR";

            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";

        }

        return "SUCCESS";

    }

    public String ActualizaSocioEconomico() {

        try {

            utilidades.Constantes.enviaMensajeConsola("LLEGA AL METODO guardardocumento");

            ConsultasBusiness con = new ConsultasBusiness();

            String ruta = null;

            boolean archivo;
            boolean RESPUESTA;
            
            Constantes.enviaMensajeConsola("lista contestados: "+ListaContestados.size());
             int contador=0;

            for (int i = 0; i < ListaContestados.size(); i++) {
                
                objRespuesta.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                objRespuesta.setRESPUESTA(ListaContestados.get(i).getRESPUESTA());
                
                Constantes.enviaMensajeConsola("pregunta: "+objRespuesta.getID_PREGUNTA());
                 Constantes.enviaMensajeConsola("respuesta: "+objRespuesta.getRESPUESTA());
                

                if (objRespuesta.getRESPUESTA().length() == 0 || objRespuesta.getRESPUESTA().contains("-1")) {
                    contador = contador + 1;
                    break;
                }
                         
            }
            
            if (contador > 0) {
                RESPUESTA = false;
                addFieldError("ERRORPREG", "Debes responder todas las preguntas");
                return "ERROR";

            } else {
                RESPUESTA = true;

            }

            
            

            

            if (objDatosE.getVALIDACHECK().equals("true")) {

                validate2();

                utilidades.Constantes.enviaMensajeConsola("sali de validate");

                if (banT) {
                    archivo = false;

                } else {
                    archivo = true;
                }

            } else {
                Constantes.enviaMensajeConsola("entro a validacheck = false");
                banT = true;
                archivo = true;
            }

            if ( RESPUESTA && archivo) {

                Constantes.enviaMensajeConsola("PASO VALIDACIONES");
                
                                //abriendo la conexion.....
               
                objRespuesta.setID_ASPIRANTE(objDatosA.getID_ASPIRANTE());
                objRespuesta.setCICLO(objAspirante.getID_CICLO());
                objRespuesta.setID_BECA(objdatos.getID_BECA_AUX());
                
                objDatosE.setID_ASPIRANTE(objDatosA.getID_ASPIRANTE());
                objDatosE.setID_CICLO(objAspirante.getID_CICLO());
                objDatosE.setID_BECA(objdatos.getID_BECA_AUX());
                
                
                 conecta = con.crearConexion();
                //statement
                objConexion = con.crearStatement(conecta);
                        
                for (int i = 0; i < ListaContestados.size(); i++) {     
                    
                    objRespuesta.setID_PREGUNTA(ListaContestados.get(i).getID_PREGUNTA());
                    objRespuesta.setRESPUESTA(ListaContestados.get(i).getRESPUESTA()); 
                    
                    con.ActualizaRespuestas(conecta, objPreConexion, objRespuesta);

                }

                Constantes.enviaMensajeConsola("paso asignaciones");

                if (banT == false) {
                    Constantes.enviaMensajeConsola("entro a banT=false");

                    if (archiFileName != null) {

                        archiFileName = "prueba" + archiFileName;

                        objDatosE.setARCHIVO_INGRESO(archiFileName);
                        ruta = Constantes.rutaArch + archiFileName;
                        Constantes.enviaMensajeConsola(ruta);
                        File newarch = new File(ruta);

                        FileUtils.copyFile(archi, newarch);

                        con.ActualizaSocioeconomico(objDatosE);

                        ConsultaTodo(objDatosA.getID_ASPIRANTE(), objAspirante.getCONSULTA_CURP(), objAspirante.ID_CICLO, objdatos.getID_BECA_AUX());

                        Constantes.enviaMensajeConsola("guardado con archivo");

                        archiFileName = null;
                    }
                } else {

                    if (objDatosE.getARCHIVO_INGRESO().length() > 0) {
                        Constantes.enviaMensajeConsola("guarda sin modificar archivo registrado");

                        con.ActualizaSocioeconomico(objDatosE);

                    } else {
                        Constantes.enviaMensajeConsola("guarda sin archivo");
                        objDatosE.setARCHIVO_INGRESO("SINARCHIVO");
                        con.ActualizaSocioeconomico(objDatosE);
                    }

                    ConsultaTodo(objDatosA.getID_ASPIRANTE(), objAspirante.getCONSULTA_CURP(), objAspirante.ID_CICLO, objdatos.getID_BECA_AUX());

                    return "SUCCESS";
                }
                
                cierraConexiones();

            } else {
                Constantes.enviaMensajeConsola("NO PASO VALIDACIONES");
                return "ERROR";
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";

        }
        return "SUCCESS";
    }

    public String ConsultaTodo(String idaspirante, String curp, String idciclo, String idbeca) {

        try {
            ConsultasBusiness con = new ConsultasBusiness();
            ListaEstadosCivil = con.ConsultaEstadosCivil();

            objRenapo.setCONSULTA_CURP(curp);
            ListaActualizaAspirante = con.ConsultaAspirante(objdatos, objRenapo);
            ListaGrados = con.ConsultaGrados(objDatosA.getNIVELCCT());
            ListaPromedios = con.ConsultaPromedios();
            ListaParentesco = con.ConsultaParentesco();

            if (ListaActualizaAspirante.size() > 0) {
                Iterator LAA = ListaActualizaAspirante.iterator();

                AspiranteBean obj3;

                while (LAA.hasNext()) {
                    obj3 = (AspiranteBean) LAA.next();

                    objAspirante.setCONSULTA_CURP(obj3.getCONSULTA_CURP());
                    objAspirante.setID_ASPIRANTE(obj3.getID_ASPIRANTE());
                    objAspirante.setNOMBRE_RENAPO(obj3.getNOMBRE_RENAPO());
                    objAspirante.setAMATERNO_RENAPO(obj3.getAMATERNO_RENAPO());
                    objAspirante.setAPATERNO_RENAPO(obj3.getAPATERNO_RENAPO());
                    objAspirante.setFEC_NAC_RENAPO(obj3.getFEC_NAC_RENAPO());
                    objAspirante.setNACIONALIDAD_RENAPO(obj3.getNACIONALIDAD_RENAPO());
                    objAspirante.setENTIDAD_NACIMINETO_RENAPO(obj3.getENTIDAD_NACIMINETO_RENAPO());
                    objAspirante.setGENERO_RENAPO(obj3.getGENERO_RENAPO());
                    objAspirante.setID_ESTADO_CIVIL(obj3.getID_ESTADO_CIVIL());
                    objAspirante.setDOMICILIO(obj3.getDOMICILIO());
                    objAspirante.setCALLE1(obj3.getCALLE1());
                    objAspirante.setCALLE2(obj3.getCALLE2());
                    objAspirante.setREFERENCIA(obj3.getREFERENCIA());
                    objAspirante.setCP(obj3.getCP());
                    ListaColonia = con.ConsultaColonia(objAspirante);
                    Iterator LC = ListaColonia.iterator();
                    ColoniasBean objg;

                    while (LC.hasNext()) {
                        objg = (ColoniasBean) LC.next();

                        objAspirante.setMUNICIPIO(objg.getMUNICIPIO());
                        objAspirante.setID_MUNICIPIO(objg.getID_MUNICIPIO());

                    }

                    objAspirante.setTELEFONO(obj3.getTELEFONO());
                    objAspirante.setCELULAR(obj3.getCELULAR());
                    objAspirante.setEMAIL(obj3.getEMAIL());

                }

            }

            objDatosA = con.ObtenDatosAcademicosAspirante(idaspirante, idciclo);

            objDatosA.setID_ASPIRANTE(idaspirante);
            objDatosA.setID_CICLO(idciclo);

            objDatosP = con.ConsultaTutor(objDatosA);

            objDatosC = con.ConsultaCobe(objDatosA);

            objDatosE = con.consultaSocioEconomico(idaspirante, idciclo);
            
            ListaContestados=con.ConsultaRes_Preg(idaspirante, idciclo);

            objFolio.setFOLIO(con.verificaFolio(idaspirante, idbeca, idciclo));

            if (objDatosE.getARCHIVO_INGRESO().length() > 0) {
                banMuestraArchivo = true;
            } else {
                banMuestraArchivo = false;
            }

            objRenapo.setCONSULTA_CURP(curp);

            Constantes.enviaMensajeConsola("ID CICLO: " + objAspirante.getID_CICLO());

            objRenapo.setID_CICLO(idciclo);

        } catch (Exception ex) {
            Logger.getLogger(Inicio_Action.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "SUCCESS";
    }

    public String GenerarFolio() {

        try {
            ConsultasBusiness con = new ConsultasBusiness();

            if (objFolio.getFOLIO().length() > 0) {
                objFolio.setID_ASPIRANTEAUX(Integer.valueOf(objAspirante.getID_ASPIRANTE()));
                Constantes.enviaMensajeConsola("ID_ASPIRANTE: " + objFolio.getID_ASPIRANTEAUX());
                objFolio.setID_CICLO(objAspirante.getID_CICLO());
                Constantes.enviaMensajeConsola("ciclo en folio: "+objFolio.getID_CICLO());
                return "SUCCESS";

            } else {
                obtenerFolioPro();

                objFolio.setID_ASPIRANTE(objAspirante.getID_ASPIRANTE());
                objFolio.setID_BECA(objdatos.getID_BECA_AUX());
                objFolio.setID_CICLO(objAspirante.getID_CICLO());
                Constantes.enviaMensajeConsola("ciclo en folio: "+objFolio.getID_CICLO());
                objFolio.setID_ASPIRANTEAUX(Integer.valueOf(objAspirante.getID_ASPIRANTE()));

                con.GuardaFolio(objFolio);

            }

        } catch (Exception ex) {
            Logger.getLogger(Inicio_Action.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "SUCCESS";
    }

    private void obtenerFolioPro() {

        try {
            ConsultasBusiness objg = new ConsultasBusiness();
            //**********************************************************************************

            folioMax = Integer.toString(objg.consultaSecuencia("TBL_FOLIOS_BECAS_SEQ"));

            Constantes.enviaMensajeConsola("el folio maximo es: " + folioMax);
            String seqFinal = "";
            if (folioMax.length() < 7) {
                Constantes.enviaMensajeConsola("entro a folio menor que 5");
                for (int i = 0; i < (7 - folioMax.length()); i++) {
                    seqFinal = seqFinal + "0";

                    Constantes.enviaMensajeConsola("la secuencia en la posicion " + i + " es: " + seqFinal);
                }
            } else {
                seqFinal = "";
            }
            Constantes.enviaMensajeConsola("secuencia final: " + seqFinal);

            //armando  el folio.....
            folioSolicitud = seqFinal + folioMax;
            objFolio.setFOLIO(folioSolicitud);
            Constantes.enviaMensajeConsola("el folio de la solicitud es: " + objFolio.getFOLIO());
            //**********************************************************************************

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error al obtener el FOLIO del trámite: " + e);
        }
    }
    
     private void cierraConexiones() {
        try {
            objConexion.close();
            conecta.close();

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error al cerrar conexiones: " + e);
        }
    }

    //******************************** FIN VARIABLE ARCHIVOS*********************************
    public List<BecasBean> getListaBases() {
        return ListaBases;
    }

    public void setListaBases(List<BecasBean> ListaBases) {
        this.ListaBases = ListaBases;
    }

    public List<BecasBean> getListaRequisitos() {
        return ListaRequisitos;
    }

    public void setListaRequisitos(List<BecasBean> ListaRequisitos) {
        this.ListaRequisitos = ListaRequisitos;
    }

    public List<EstadoCivilBean> getListaEstadosCivil() {
        return ListaEstadosCivil;
    }

    public void setListaEstadosCivil(List<EstadoCivilBean> ListaEstadosCivil) {
        this.ListaEstadosCivil = ListaEstadosCivil;
    }

    public List<ColoniasBean> getListaColonia() {
        return ListaColonia;
    }

    public void setListaColonia(List<ColoniasBean> ListaColonia) {
        this.ListaColonia = ListaColonia;
    }

    public List<GradoBean> getListaGrados() {
        return ListaGrados;
    }

    public void setListaGrados(List<GradoBean> ListaGrados) {
        this.ListaGrados = ListaGrados;
    }

    public List<PromedioBean> getListaPromedios() {
        return ListaPromedios;
    }

    public void setListaPromedios(List<PromedioBean> ListaPromedios) {
        this.ListaPromedios = ListaPromedios;
    }

    public List<ParentezcoBean> getListaParentesco() {
        return ListaParentesco;
    }

    public void setListaParentesco(List<ParentezcoBean> ListaParentesco) {
        this.ListaParentesco = ListaParentesco;
    }

    public List<RespuestasBean> getListaRespuestas() {
        return ListaRespuestas;
    }

    public void setListaRespuestas(List<RespuestasBean> ListaRespuestas) {
        this.ListaRespuestas = ListaRespuestas;
    }

    public List<CobeneficiarioBean> getVerificaCobe() {
        return VerificaCobe;
    }

    public void setVerificaCobe(List<CobeneficiarioBean> VerificaCobe) {
        this.VerificaCobe = VerificaCobe;
    }

    public BecasBean getObjdatos() {
        return objdatos;
    }

    public void setObjdatos(BecasBean objdatos) {
        this.objdatos = objdatos;
    }

    public List<moduloBean> getModulosAUX() {
        return modulosAUX;
    }

    public void setModulosAUX(List<moduloBean> modulosAUX) {
        this.modulosAUX = modulosAUX;
    }

    public List<moduloAuxBean> getModulosAUXP() {
        return modulosAUXP;
    }

    public void setModulosAUXP(List<moduloAuxBean> modulosAUXP) {
        this.modulosAUXP = modulosAUXP;
    }

    public List<BecasBean> getListaBecas() {
        return ListaBecas;
    }

    public void setListaBecas(List<BecasBean> ListaBecas) {
        this.ListaBecas = ListaBecas;
    }

    public List<RequisitosBean> getListaReq() {
        return ListaReq;
    }

    public void setListaReq(List<RequisitosBean> ListaReq) {
        this.ListaReq = ListaReq;
    }

    public usuarioBean getUsuariocons() {
        return usuariocons;
    }

    public void setUsuariocons(usuarioBean usuariocons) {
        this.usuariocons = usuariocons;
    }

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }

    public AspiranteBean getObjAspirante() {
        return objAspirante;
    }

    public void setObjAspirante(AspiranteBean objAspirante) {
        this.objAspirante = objAspirante;
    }

    public AcademicoBean getObjDatosA() {
        return objDatosA;
    }

    public void setObjDatosA(AcademicoBean objDatosA) {
        this.objDatosA = objDatosA;
    }

    public TutorBean getObjDatosP() {
        return objDatosP;
    }

    public void setObjDatosP(TutorBean objDatosP) {
        this.objDatosP = objDatosP;
    }

    public CobeneficiarioBean getObjDatosC() {
        return objDatosC;
    }

    public void setObjDatosC(CobeneficiarioBean objDatosC) {
        this.objDatosC = objDatosC;
    }

    public IngresosBean getObjDatosE() {
        return objDatosE;
    }

    public void setObjDatosE(IngresosBean objDatosE) {
        this.objDatosE = objDatosE;
    }

    public FolioBean getObjFolio() {
        return objFolio;
    }

    public void setObjFolio(FolioBean objFolio) {
        this.objFolio = objFolio;
    }

    public String getNivelUsuario() {
        return nivelUsuario;
    }

    public ValidaCurpBean getObjvalidaC() {
        return objvalidaC;
    }

    public void setObjvalidaC(ValidaCurpBean objvalidaC) {
        this.objvalidaC = objvalidaC;
    }

    public Respuesta_PreguntaBean getObjRespuesta() {
        return objRespuesta;
    }

    public void setObjRespuesta(Respuesta_PreguntaBean objRespuesta) {
        this.objRespuesta = objRespuesta;
    }
    
    
    
    

    public void setNivelUsuario(String nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public boolean isBanColonia() {
        return banColonia;
    }

    public void setBanColonia(boolean banColonia) {
        this.banColonia = banColonia;
    }

    public boolean isBanColoniaP() {
        return banColoniaP;
    }

    public void setBanColoniaP(boolean banColoniaP) {
        this.banColoniaP = banColoniaP;
    }

    public boolean isBanFormAca() {
        return banFormAca;
    }

    public void setBanFormAca(boolean banFormAca) {
        this.banFormAca = banFormAca;
    }

    public boolean isBanFormP() {
        return banFormP;
    }

    public void setBanFormP(boolean banFormP) {
        this.banFormP = banFormP;
    }

    public boolean isBanFormCobe() {
        return banFormCobe;
    }

    public void setBanFormCobe(boolean banFormCobe) {
        this.banFormCobe = banFormCobe;
    }

    public boolean isBanMuestraCobe() {
        return banMuestraCobe;
    }

    public void setBanMuestraCobe(boolean banMuestraCobe) {
        this.banMuestraCobe = banMuestraCobe;
    }

    public boolean isBanConCct() {
        return banConCct;
    }

    public void setBanConCct(boolean banConCct) {
        this.banConCct = banConCct;
    }

    public boolean isBanConCurp() {
        return banConCurp;
    }

    public void setBanConCurp(boolean banConCurp) {
        this.banConCurp = banConCurp;
    }

    public boolean isBanFormIngresos() {
        return banFormIngresos;
    }

    public void setBanFormIngresos(boolean banFormIngresos) {
        this.banFormIngresos = banFormIngresos;
    }

    public boolean isBanT() {
        return banT;
    }

    public void setBanT(boolean banT) {
        this.banT = banT;
    }

    public boolean isBanActualiza() {
        return banActualiza;
    }

    public void setBanActualiza(boolean banActualiza) {
        this.banActualiza = banActualiza;
    }

    public boolean isBanGuarda() {
        return banGuarda;
    }

    public void setBanGuarda(boolean banGuarda) {
        this.banGuarda = banGuarda;
    }

    public boolean isBanActualizaP() {
        return banActualizaP;
    }

    public void setBanActualizaP(boolean banActualizaP) {
        this.banActualizaP = banActualizaP;
    }

    public boolean isBanGuardaP() {
        return banGuardaP;
    }

    public void setBanGuardaP(boolean banGuardaP) {
        this.banGuardaP = banGuardaP;
    }

    public boolean isBanGuardaE() {
        return banGuardaE;
    }

    public void setBanGuardaE(boolean banGuardaE) {
        this.banGuardaE = banGuardaE;
    }

    public boolean isBanActualizaE() {
        return banActualizaE;
    }

    public void setBanActualizaE(boolean banActualizaE) {
        this.banActualizaE = banActualizaE;
    }

    public boolean isBanMuestraArchivo() {
        return banMuestraArchivo;
    }

    public void setBanMuestraArchivo(boolean banMuestraArchivo) {
        this.banMuestraArchivo = banMuestraArchivo;
    }

    public String getVALCOB() {
        return VALCOB;
    }

    public void setVALCOB(String VALCOB) {
        this.VALCOB = VALCOB;
    }

    public String getValingreso() {
        return valingreso;
    }

    public void setValingreso(String valingreso) {
        this.valingreso = valingreso;
    }

    public String getFolioMax() {
        return folioMax;
    }

    public void setFolioMax(String folioMax) {
        this.folioMax = folioMax;
    }

    public String getFolioSolicitud() {
        return folioSolicitud;
    }

    public void setFolioSolicitud(String folioSolicitud) {
        this.folioSolicitud = folioSolicitud;
    }

    public File getArchi() {
        return archi;
    }

    public void setArchi(File archi) {
        this.archi = archi;
    }

    public String getArchiFileName() {
        return archiFileName;
    }

    public void setArchiFileName(String archiFileName) {
        this.archiFileName = archiFileName;
    }

    public List<BecasBean> getListaFechas() {
        return ListaFechas;
    }

    public void setListaFechas(List<BecasBean> ListaFechas) {
        this.ListaFechas = ListaFechas;
    }

    public List<AspiranteBean> getListaActualizaAspirante() {
        return ListaActualizaAspirante;
    }

    public void setListaActualizaAspirante(List<AspiranteBean> ListaActualizaAspirante) {
        this.ListaActualizaAspirante = ListaActualizaAspirante;
    }

    public List<AcademicoBean> getListaDatosAcad() {
        return ListaDatosAcad;
    }

    public void setListaDatosAcad(List<AcademicoBean> ListaDatosAcad) {
        this.ListaDatosAcad = ListaDatosAcad;
    }

    public List<ValidaCurpBean> getValidacurp() {
        return validacurp;
    }

    public void setValidacurp(List<ValidaCurpBean> validacurp) {
        this.validacurp = validacurp;
    }

    public List<PreguntasBean> getListaPreguntas() {
        return ListaPreguntas;
    }

    public void setListaPreguntas(List<PreguntasBean> ListaPreguntas) {
        this.ListaPreguntas = ListaPreguntas;
    }

    public List<Respuesta_PreguntaBean> getListaContestados() {
        return ListaContestados;
    }

    public void setListaContestados(List<Respuesta_PreguntaBean> ListaContestados) {
        this.ListaContestados = ListaContestados;
    }
    
    

}
