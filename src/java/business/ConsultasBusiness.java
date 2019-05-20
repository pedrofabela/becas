package business;

import beans.BecasBean;
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


}
