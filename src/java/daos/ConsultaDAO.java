/**
 *
 * @author Esteban
 */
package daos;

import beans.BecasBean;
import java.util.List;

public interface ConsultaDAO {

    public List ConsultaBecas() throws Exception;
  
    public List ConsultaReq(BecasBean obj) throws Exception;
    
    public List ConsultaBases(BecasBean obj) throws Exception;
    public List ConsultaRequisitos(BecasBean obj) throws Exception;

}
