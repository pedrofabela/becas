package action;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.CobeneficiarioBean;
import beans.ColoniasBean;
import beans.EstadoCivilBean;
import beans.GradoBean;
import beans.ParentezcoBean;
import beans.PromedioBean;
import beans.RequisitosBean;
import beans.TutorBean;

import beans.moduloAuxBean;
import beans.moduloBean;
import beans.renapoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import utilidades.Constantes;

public class Inicio_Action extends ActionSupport {

    //INSTANCIA A LOS BEANS//
    BecasBean objdatos = new BecasBean();
    renapoBean objRenapo = new renapoBean();
    AspiranteBean objAspirante = new AspiranteBean();
    AcademicoBean objDatosA = new AcademicoBean();
    TutorBean objDatosP = new TutorBean();
    CobeneficiarioBean objDatosC = new CobeneficiarioBean();

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

    private boolean banColonia = false;
    private boolean banColoniaP = false;
    private boolean banFormAca = false;
    private boolean banFormP = false;
    private boolean banFormCobe = false;
    private boolean banMuestraCobe = false;
    
    private boolean banConCct=false;
    private boolean banConCurp=false;
            

    private String VALCOB;

   
    
   
    
    //****************************//
    // xxxxxxxxxxxxxxxxxxxxxSESIONxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    private usuarioBean usuariocons;
    private Map session = ActionContext.getContext().getSession();
    private String nivelUsuario;

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

            if (objdatos.getRESTRICCION_ESC().equals("1")) {

                CctParticipa = con.ConsultaEscParticipa(objdatos, objRenapo);

                Constantes.enviaMensajeConsola("Tamaño participa" + CctParticipa.length());

                if (CctParticipa.length() > 0) {

                    Constantes.enviaMensajeConsola("LA ESCUELA SI PARTICIPA");
                    
                    objDatosA.setCCTAUX(CctParticipa);
                    objDatosA=con.ConsultaCCT(objDatosA);
                    
                    banConCct=false;
                    banConCurp=true;
                    

                } else {
                    
                     banConCct=true;
                    banConCurp=false;

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

           ConsultasBusiness con=new ConsultasBusiness();
            
            Constantes.enviaMensajeConsola("ID_BECA_AUX: "+objdatos.getID_BECA_AUX());

            MuestraBecas();         
            ListaBases=(ArrayList<BecasBean>) con.ConsultaBases(objdatos);
            ListaRequisitos=(ArrayList<BecasBean>) con.ConsultaRequisitos(objdatos);
            
            //System.out.println("auxiliar de beca:"+ objdatos.getID_BECA_AUX());
            
            
            objRenapo.setID_CICLO(con.ConsultaCiclo(objdatos));
            
           
            
            
            
            
            objRenapo.setINTERVALO(con.ConsultaIntervalo(objdatos, objRenapo));
            
            objRenapo.setNIVEL(con.ConsultaNivel(objdatos, objRenapo));
            
            
            
            
            
            
            
            banConCct=true;
            objDatosA=null;
             banConCurp=false;
            
            
            
           // System.out.println("id de l cilclo es:"+ con.ConsultaCiclo(objdatos));

            
          
            
            
            
            
            Constantes.enviaMensajeConsola("lista Req: "+ListaRequisitos.size());


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

            ListaEstadosCivil = con.ConsultaEstadosCivil();

            objRenapo = renapo.consultaRenapo(objRenapo.getCONSULTA_CURP());

            objAspirante.setCONSULTA_CURP(objRenapo.getCONSULTA_CURP());
            objAspirante.setNOMBRE_RENAPO(objRenapo.getNOMBRE_RENAPO());
            objAspirante.setAPATERNO_RENAPO(objRenapo.getAPATERNO_RENAPO());
            objAspirante.setAMATERNO_RENAPO(objRenapo.AMATERNO_RENAPO);
            objAspirante.setFEC_NAC_RENAPO(objRenapo.getFEC_NAC_RENAPO());
            objAspirante.setNACIONALIDAD_RENAPO(objRenapo.getNACIONALIDAD_RENAPO());
            objAspirante.setENTIDAD_NACIMINETO_RENAPO(objRenapo.getENTIDAD_NACIMINETO_RENAPO());
            objAspirante.setGENERO_RENAPO(objRenapo.getGENERO_RENAPO());

            //consulta cct*********************
            objDatosA.setCCTAUX("15EPR0806K");
            objAspirante.setID_CICLO("1");

            objDatosA = con.ConsultaCCT(objDatosA);

            if (objDatosA == null) {

                banFormAca = false;
                addFieldError("ERRORCCT", "LA CCT QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

            } else {
                banFormAca = true;
                ListaGrados = con.ConsultaGrados();
                ListaPromedios = con.ConsultaPromedios();
            }

            Constantes.enviaMensajeConsola("ID_BECA_AUX: " + objdatos.getID_BECA_AUX());
            Constantes.enviaMensajeConsola("lista Req: " + ListaReq.size());
            Constantes.enviaMensajeConsola("lista ESTADOS: " + ListaEstadosCivil.size());

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
                addFieldError("TEL", "Debe registrar un número telefonico del alumno");
            }

            if (objAspirante.getCELULAR().length() > 0) {
                CELULAR = true;

            } else {
                CELULAR = false;
                addFieldError("CEL", "Debe registrar un número celular del alumno");
            }

            if (objAspirante.getEMAIL().length() > 0) {
                EMAIL = true;

            } else {
                EMAIL = false;
                addFieldError("EMAIL", "Debe registrar un correo electronico del alumno");
            }

            if (objDatosA.getGRADO().length() > 0) {
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

                    banFormP = true;

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

                objRenapo = renapo.consultaRenapo(objDatosC.getCURPAUX_CO());

                if (objRenapo == null) {

                    banMuestraCobe = false;
                    addFieldError("NOCURP", "LA CURP QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");

                } else {

                    objDatosC.setCONSULTA_CURP_CO(objRenapo.getCONSULTA_CURP());
                    objDatosC.setNOMBRE_RENAPO_CO(objRenapo.getNOMBRE_RENAPO());
                    objDatosC.setAPATERNO_RENAPO_CO(objRenapo.getAPATERNO_RENAPO());
                    objDatosC.setAMATERNO_RENAPO_CO(objRenapo.getAMATERNO_RENAPO());
                    objDatosC.setFEC_NAC_RENAPO_CO(objRenapo.getFEC_NAC_RENAPO());
                    objDatosC.setGENERO_RENAPO_CO(objRenapo.getGENERO_RENAPO());
                    objDatosC.setNACIONALIDAD_RENAPO_CO(objRenapo.getNACIONALIDAD_RENAPO());
                    objDatosC.setENTIDAD_NACIMINETO_RENAPO_CO(objRenapo.getENTIDAD_NACIMINETO_RENAPO());

                    ListaParentesco = con.ConsultaParentesco();
                    ListaEstadosCivil = con.ConsultaEstadosCivil();

                    banMuestraCobe = true;

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

            if (NOMBRE_TU && APATERNO_TU && AMATERNO_TU && GENERO_TU && ENTIDAD_NACIMINETO_TU && FEC_NAC_TU && NACIONALIDAD_TU && CP_TU && MUNICIPIO_TU
                    && ID_PARENTESCO_TU && DOMICILIO_TU && CALLE1_TU && CALLE2_TU && REFERENCIA_TU && COLONIA_TU && TELEFONO_TU && CELULAR_TU && EMAIL_TU) {

                if (objDatosC.getVALIDACHECK().equals("true")) {

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
                    
                    
                    if (NOMBRE_CO && APATERNO_CO && AMATERNO_CO && GENERO_CO && ENTIDAD_NACIMINETO_CO && FEC_NAC_CO && NACIONALIDAD_CO && ID_PARENTESCO_CO &&ID_ESTADO_CIVIL_CO) {
                        
                        
                        
                        
                    } else {
                    }
                    
                    

                } else {
                }

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

    public String getNivelUsuario() {
        return nivelUsuario;
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
    
    

    public String getVALCOB() {
        return VALCOB;
    }

    public void setVALCOB(String VALCOB) {
        this.VALCOB = VALCOB;
    }

}
