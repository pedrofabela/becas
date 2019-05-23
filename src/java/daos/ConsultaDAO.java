/**
 *
 * @author Esteban
 */
package daos;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.ColoniasBean;
import beans.renapoBean;
import java.util.List;

public interface ConsultaDAO {

    public List ConsultaBecas() throws Exception;

    public List ConsultaReq(BecasBean obj) throws Exception;

    public List ConsultaBases(BecasBean obj) throws Exception;

    public List ConsultaRequisitos(BecasBean obj) throws Exception;
    
    public String ConsultaCiclo(BecasBean obj) throws Exception;
    
    public String ConsultaIntervalo(BecasBean obj,  renapoBean ren) throws Exception;

    public List ConsultaEstadosCivil() throws Exception;
    
    public List ConsultaColonia(AspiranteBean obj) throws Exception;
    
    public boolean GuardaDatosPersonales(AspiranteBean objg) throws Exception;
    
    public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception;
    
    public List ConsultaGrados() throws Exception;
    
    public List ConsultaPromedios() throws Exception;
    
    public String ConsultaAspirante(AspiranteBean obj) throws Exception;
    
    public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception;
    
    public List ConsultaParentesco() throws Exception;

}
