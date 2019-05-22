package business;

import beans.AcademicoBean;
import beans.BecasBean;
import beans.ColoniasBean;
import beans.renapoBean;
import java.util.List;
import daos.ConsultaDAO;
import daos.ConsultaDAOImpl;

public class ConsultasBusiness {

    private ConsultaDAO con;

    public ConsultasBusiness() throws Exception {
        this.con = new ConsultaDAOImpl();
    }

    public List ConsultaBecas() throws Exception {
        List lista = this.con.ConsultaBecas();
        return lista;
    }

    public List ConsultaReq(BecasBean obj) throws Exception {
        List lista = this.con.ConsultaReq(obj);
        return lista;
    }

    public List ConsultaBases(BecasBean obj) throws Exception {
        List lista = this.con.ConsultaBases(obj);
        return lista;
    }

    public List ConsultaRequisitos(BecasBean obj) throws Exception {
        List lista = this.con.ConsultaRequisitos(obj);
        return lista;
    }
    
    public List ConsultaEstadosCivil() throws Exception {
        List lista = this.con.ConsultaEstadosCivil();
        return lista;
    }
    
    public List ConsultaColonia(renapoBean obj) throws Exception {
        List lista = this.con.ConsultaColonia(obj);
        return lista;
    }
    
    public boolean GuardaDatosPersonales(renapoBean objg) throws Exception{
        return this.con.GuardaDatosPersonales(objg);
    }
    
     public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception {
        return con.ConsultaCCT(cct);
    }
     
      public List ConsultaGrados() throws Exception {
        List lista = this.con.ConsultaGrados();
        return lista;
    }
      
        public List ConsultaPromedios() throws Exception {
        List lista = this.con.ConsultaPromedios();
        return lista;
    }
        
         public String ConsultaAspirante(renapoBean obj) throws Exception {
        String id = this.con.ConsultaAspirante(obj);
        return id;
    }
    
     public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception{
        return this.con.GuardaDatosAcademicos(objg);
    }
     

}
