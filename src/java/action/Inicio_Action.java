package action;

import beans.AcademicoBean;
import beans.BecasBean;
import beans.ColoniasBean;
import beans.EstadoCivilBean;
import beans.GradoBean;
import beans.PromedioBean;
import beans.RequisitosBean;

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
    AcademicoBean objDatosA=new AcademicoBean();
    
   
    //************************************

    //VARIABLES REQUERIDAS//
    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    private List<BecasBean> ListaBecas = new ArrayList<BecasBean>();
    private List<RequisitosBean> ListaReq = new ArrayList<RequisitosBean>();
    private List<BecasBean> ListaBases = new ArrayList<BecasBean>();
    private List<BecasBean> ListaRequisitos = new ArrayList<BecasBean>();
    private List<EstadoCivilBean>ListaEstadosCivil=new ArrayList<EstadoCivilBean>();
    private List<ColoniasBean>ListaColonia=new ArrayList<ColoniasBean>();
    private List<GradoBean> ListaGrados=new ArrayList<GradoBean>();
    private List<PromedioBean>ListaPromedios=new ArrayList<PromedioBean>();
    
    private boolean banColonia=false;
    private boolean banFormAca=false;
            
   
    
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
           

            ConsultasBusiness con=new ConsultasBusiness();
            
            ListaBecas=(ArrayList<BecasBean>) con.ConsultaBecas();
            Constantes.enviaMensajeConsola("lista becas: "+ListaBecas);

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
           

            ConsultasBusiness con=new ConsultasBusiness();
            consultaRenapo renapo=new consultaRenapo();
            
            ListaEstadosCivil=con.ConsultaEstadosCivil();
            
            objRenapo= renapo.consultaRenapo(objRenapo.getCONSULTA_CURP());
            
            //consulta cct*********************
            
              objDatosA.setCCTAUX("15EPR0806K");
            
              objDatosA=con.ConsultaCCT(objDatosA);
            
            if (objDatosA==null) {
                
                banFormAca=false;
                addFieldError("ERRORCCT", "LA CCT QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");
                
            } else {
                banFormAca=true;
                 ListaGrados=con.ConsultaGrados();        
            ListaPromedios=con.ConsultaPromedios();
            }
        
        
        
        
            System.out.println("nombre de renapo en metodo"+ objRenapo.getNOMBRE_RENAPO());
                            
            Constantes.enviaMensajeConsola("ID_BECA_AUX: "+objdatos.getID_BECA_AUX());
            Constantes.enviaMensajeConsola("lista Req: "+ListaReq.size());
            Constantes.enviaMensajeConsola("lista ESTADOS: "+ListaEstadosCivil.size());
          

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

            
            ListaColonia=con.ConsultaColonia(objRenapo);
            
            if (ListaColonia.size()>0) {
                
                banColonia=true;
                
                Iterator LC=ListaColonia.iterator();
                ColoniasBean objg;
                
                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();
                    
                    objRenapo.setMUNICIPIO(objg.getMUNICIPIO());
                    objRenapo.setID_MUNICIPIO(objg.getID_MUNICIPIO());
                    
                }
                
            } else {
                   banColonia=false;
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
            
    boolean NOMBRE_RENAPO=false;
    boolean APATERNO_RENAPO=false;
    boolean AMATERNO_RENAPO=false;
    boolean GENERO_RENAPO=false;
    boolean ENTIDAD_NACIMINETO_RENAPO=false;
    boolean FEC_NAC_RENAPO=false;
    boolean NACIONALIDAD_RENAPO=false;
    boolean CP=false;
    boolean MUNICIPIO=false;
    boolean ID_ESTADO_CIVIL=false;
    boolean DOMICILIO=false;
    boolean CALLE1=false;
    boolean CALLE2=false;
    boolean REFERENCIA=false;
    boolean COLONIA=false;
    boolean TELEFONO=false;
    boolean CELULAR=false;
    boolean EMAIL=false; 
    boolean GRADO=false;
    boolean PROMEDIO=false;

    
            if (objRenapo.getNOMBRE_RENAPO().length()>0) {
                NOMBRE_RENAPO=true;
                
            } else {
                NOMBRE_RENAPO=false;
                addFieldError("NOMA", "Debe registrar el nombre del alumno");
            }
        
             if (objRenapo.getAPATERNO_RENAPO().length()>0) {
                APATERNO_RENAPO=true;
                
            } else {
                APATERNO_RENAPO=false;
                addFieldError("APA", "Debe registrar el apellido paterno del alumno");
            }
             
             if (objRenapo.getAMATERNO_RENAPO().length()>0) {
                AMATERNO_RENAPO=true;
                
            } else {
                AMATERNO_RENAPO=false;
                addFieldError("AMA", "Debe registrar el apellido materno del alumno");
            } 
             if (objRenapo.getFEC_NAC_RENAPO().length()>0) {
                FEC_NAC_RENAPO=true;
                
            } else {
                FEC_NAC_RENAPO=false;
                addFieldError("FECNAN", "Debe registrar la fecha de nacimiento del alumno");
            } 
            
             if (objRenapo.getNACIONALIDAD_RENAPO().length()>0) {
                NACIONALIDAD_RENAPO=true;
                
            } else {
                NACIONALIDAD_RENAPO=false;
                addFieldError("NAC", "Debe registrar la nacionalidad del alumno");
            }  
             if (objRenapo.getENTIDAD_NACIMINETO_RENAPO().length()>0) {
                ENTIDAD_NACIMINETO_RENAPO=true;
                
            } else {
                ENTIDAD_NACIMINETO_RENAPO=false;
                addFieldError("ENTIDADNAC", "Debe registrar la entidad de nacimiento");
            }  
            
             if (objRenapo.getGENERO_RENAPO().length()>0) {
                GENERO_RENAPO=true;
                
            } else {
                GENERO_RENAPO=false;
                addFieldError("GENERO", "Debe registrar el genero del alumno");
            }  
             
            if (objRenapo.getID_ESTADO_CIVIL().length() > 0) {
                ID_ESTADO_CIVIL = true;

            } else {
                ID_ESTADO_CIVIL = false;
                addFieldError("IDESTADO", "Debe Seleccionar un estado civil del alumno");
            }
             if (objRenapo.getDOMICILIO().length() > 0) {
                DOMICILIO = true;

            } else {
                DOMICILIO = false;
                addFieldError("DOMICILIO", "Debe registrar el domicilio del alumno");
            }
             
             if (objRenapo.getCALLE1().length() > 0) {
                CALLE1 = true;

            } else {
                CALLE1 = false;
                addFieldError("CALLE1", "Debe registrar entre calle del domicilio del alumno");
            }
              if (objRenapo.getCALLE2().length() > 0) {
                CALLE2 = true;

            } else {
                CALLE2 = false;
                addFieldError("CALLE2", "Debe registrar Y calle del domicilio del alumno");
            }

             if (objRenapo.getREFERENCIA().length() > 0) {
                REFERENCIA = true;

            } else {
                REFERENCIA = false;
                addFieldError("REFERENCIA", "Debe registrar otra referencia del domicilio del alumno");
            }
            
             if (objRenapo.getCP().length() > 0) {
                CP = true;

            } else {
                CP = false;
                addFieldError("CP", "Debe registrar un codigo postal del alumno");
            } 
              if (objRenapo.getCOLONIA().length() > 0) {
                COLONIA = true;

            } else {
                COLONIA = false;
                addFieldError("COLONIA", "Debe registrar una colonia del alumno");
            }
              if (objRenapo.getMUNICIPIO().length() > 0) {
                MUNICIPIO = true;

            } else {
                MUNICIPIO = false;
                addFieldError("MUNICIPIO", "Debe registrar el municipio del alumno");
            } 
             if (objRenapo.getTELEFONO().length() > 0) {
                TELEFONO = true;

            } else {
                TELEFONO = false;
                addFieldError("TEL", "Debe registrar un número telefonico del alumno");
            } 
            
             if (objRenapo.getCELULAR().length() > 0) {
                CELULAR = true;

            } else {
                CELULAR = false;
                addFieldError("CEL", "Debe registrar un número celular del alumno");
            } 
             
             if (objRenapo.getEMAIL().length() > 0) {
                EMAIL = true;

            } else {
                EMAIL = false;
                addFieldError("EMAIL", "Debe registrar un correo electronico del alumno");
            } 
            
             if (objDatosA.getGRADO().length()>0) {
                GRADO=true;
                
            } else {
                GRADO=false;
                addFieldError("GRADO", "Debe registrar el grado que cursa el alumno");
            }
        
             if (objDatosA.getPROMEDIO().length()>0) {
                PROMEDIO=true;
                
            } else {
                PROMEDIO=false;
                addFieldError("PROMEDIO", "Debe registrar el promedio del alumno");
            }
 
             
             if ( NOMBRE_RENAPO && APATERNO_RENAPO && AMATERNO_RENAPO && GENERO_RENAPO && ENTIDAD_NACIMINETO_RENAPO && FEC_NAC_RENAPO && NACIONALIDAD_RENAPO && CP && MUNICIPIO &&
    ID_ESTADO_CIVIL && DOMICILIO && CALLE1 && CALLE2 && REFERENCIA && COLONIA && TELEFONO && CELULAR && EMAIL && GRADO && PROMEDIO) {
                 
                 con.GuardaDatosPersonales(objRenapo);
                 
                 objRenapo.setID_ASPIRANTE(con.ConsultaAspirante(objRenapo));
                 
                 objDatosA.setID_ASPIRANTE(objRenapo.getID_ASPIRANTE());
                 objDatosA.setID_CICLO("1");
                 
                 con.GuardaDatosAcademicos(objDatosA);
                
             }         
            Constantes.enviaMensajeConsola("lista Req: " + ListaRequisitos.size());

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }
    
    public String ConsultaCCT() {
        try {
            //validando session***********************************************************************

            ConsultasBusiness con = new ConsultasBusiness();
            
            Constantes.enviaMensajeConsola("cctaux: "+objDatosA.getCCTAUX());
            
            if (objDatosA.getCCTAUX().length()>0 && objDatosA.getCCTAUX().length()==10) {
                
                 objDatosA=con.ConsultaCCT(objDatosA);
            
            if (objDatosA==null) {
                
                banFormAca=false;
                addFieldError("ERRORCCT", "LA CCT QUE INGRESO NO SE ENCUENTRA FAVOR DE VERIFICAR");
                
            } else {
                banFormAca=true;
                 ListaGrados=con.ConsultaGrados();        
            ListaPromedios=con.ConsultaPromedios();
            }

                
            } else {
                         
                 addFieldError("ERRORCCT", "POR FAVOR INGRESE UNA CCT VALIDA");
                
            }

            
                       
           
            

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }
    
    public String GuardaDatosAcademicos() {
        
        try {
            //validando session***********************************************************************
            
           
            
            

            ConsultasBusiness con = new ConsultasBusiness();
            
               boolean GRADO=false;
               boolean PROMEDIO=false;
    
    
            if (objDatosA.getGRADO().length()>0) {
                GRADO=true;
                
            } else {
                GRADO=false;
                addFieldError("GRADO", "Debe registrar el grado que cursa el alumno");
            }
        
             if (objDatosA.getPROMEDIO().length()>0) {
                PROMEDIO=true;
                
            } else {
                PROMEDIO=false;
                addFieldError("PROMEDIO", "Debe registrar el promedio del alumno");
            }
             
                         
             if ( GRADO && PROMEDIO) {
                 
                  Constantes.enviaMensajeConsola("entro a metodo paso validaciones");
                 
                 objDatosA.setID_ASPIRANTE(objRenapo.getID_ASPIRANTE());
                 objDatosA.setID_CICLO("1");
                 
                 con.GuardaDatosAcademicos(objDatosA);
                             
             }else{
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

    public AcademicoBean getObjDatosA() {
        return objDatosA;
    }

    public void setObjDatosA(AcademicoBean objDatosA) {
        this.objDatosA = objDatosA;
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

    public boolean isBanFormAca() {
        return banFormAca;
    }

    public void setBanFormAca(boolean banFormAca) {
        this.banFormAca = banFormAca;
    }
    
    
    
    
    
    
    
}
