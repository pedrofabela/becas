package action;

import beans.BecasBean;
import beans.EstadoCivilBean;
import beans.RequisitosBean;

import beans.moduloAuxBean;
import beans.moduloBean;
import beans.renapoBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utilidades.Constantes;

public class Inicio_Action extends ActionSupport {

    //INSTANCIA A LOS BEANS//
    BecasBean objdatos = new BecasBean();
     renapoBean objRenapo = new renapoBean();
   
    //************************************

    //VARIABLES REQUERIDAS//
    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    private List<BecasBean> ListaBecas = new ArrayList<BecasBean>();
    private List<RequisitosBean> ListaReq = new ArrayList<RequisitosBean>();
    private List<BecasBean> ListaBases = new ArrayList<BecasBean>();
    private List<BecasBean> ListaRequisitos = new ArrayList<BecasBean>();
    private List<EstadoCivilBean>ListaEstadosCivil=new ArrayList<EstadoCivilBean>();
            
   
    
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
            
        objRenapo= renapo.consultaRenapo("FAMP871204HMCBRD07");
        
        
        
        
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

   

    public String getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(String nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }
    
    
    
    
    
}
