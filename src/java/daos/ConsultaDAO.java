/**
 *
 * @author Esteban
 */
package daos;

import beans.AcademicoBean;
import beans.AspiranteBean;
import beans.BecasBean;
import beans.CobeneficiarioBean;
import beans.ColoniasBean;
import beans.IngresosBean;
import beans.TutorBean;
import beans.renapoBean;
import java.util.List;

public interface ConsultaDAO {

    public List ConsultaBecas() throws Exception;
        public List ConsultaFechasBeca(BecasBean obj,  renapoBean ren) throws Exception;

    public List ConsultaReq(BecasBean obj) throws Exception;

    public List ConsultaBases(BecasBean obj) throws Exception;

    public List ConsultaRequisitos(BecasBean obj) throws Exception;

    public String ConsultaCiclo(BecasBean obj) throws Exception;

    public String ConsultaIntervalo(BecasBean obj, renapoBean ren) throws Exception;

    public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception;

    public String ConsultaEscParticipa(BecasBean obj, renapoBean ren) throws Exception;

    public List ConsultaEstadosCivil() throws Exception;

    public List ConsultaColonia(AspiranteBean obj) throws Exception;

    public boolean GuardaDatosPersonales(AspiranteBean objg) throws Exception;

    public boolean ActualizaDatosPersonales(AspiranteBean objg) throws Exception;

    public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception;

    public List ConsultaGrados() throws Exception;

    public List ConsultaPromedios() throws Exception;

    public String ConsultaAspirante(AspiranteBean obj) throws Exception;

    public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception;

    public TutorBean ConsultaTutor(AcademicoBean objg) throws Exception;

    public CobeneficiarioBean ConsultaCobe(AcademicoBean objg) throws Exception;

    public CobeneficiarioBean ConsultaCobeXcurp(AcademicoBean objg) throws Exception;

    public boolean ActualizaDatosAcademicos(AcademicoBean objg) throws Exception;

    public List ConsultaParentesco() throws Exception;

    public boolean GuardaDatosTutor(TutorBean objg) throws Exception;

    public boolean GuardaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception;
    
     public boolean ActualizaDatosTutor(TutorBean objg) throws Exception;

    public boolean ActualizaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception;

    public List ConsultaRespuestas() throws Exception;

    public boolean GuardaSocioeconomico(IngresosBean objg) throws Exception;
    
     public IngresosBean consultaSocioEconomico(String idaspirante, String Ciclo) throws Exception;
     
      public boolean ActualizaSocioeconomico(IngresosBean objg) throws Exception;

}
