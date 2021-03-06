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
import beans.FolioBean;
import beans.IngresosBean;
import beans.Respuesta_PreguntaBean;
import beans.TutorBean;
import beans.renapoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public interface ConsultaDAO {

    public Connection crearConexion() throws Exception;

    public Statement crearStatement(Connection cone) throws Exception;

    public List ConsultaBecas() throws Exception;

    public List ConsultaFechasBeca(BecasBean obj, renapoBean ren) throws Exception;

    public List ConsultaAspirante(BecasBean obj, renapoBean ren) throws Exception;

    public List ConsultaDatosAca(AspiranteBean obj, AcademicoBean acad) throws Exception;

    public List ConsultaFechasBecaTodos(BecasBean obj, renapoBean ren) throws Exception;

    public List ConsultaReq(BecasBean obj) throws Exception;

    public List ConsultaBases(BecasBean obj) throws Exception;

    public List ConsultaRequisitos(BecasBean obj) throws Exception;

    public String ConsultaCiclo(BecasBean obj) throws Exception;

    public String ConsultaIntervalo(BecasBean obj, renapoBean ren) throws Exception;

    public String ConsultaIntervaloSinNivel(BecasBean obj, renapoBean ren) throws Exception;

    public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception;

    public String ConsultaEscParticipa(BecasBean obj, renapoBean ren) throws Exception;

    public List ConsultaEstadosCivil() throws Exception;

    public List ConsultaColonia(AspiranteBean obj) throws Exception;

    public boolean GuardaDatosPersonales(AspiranteBean objg) throws Exception;

    public boolean ActualizaDatosPersonales(AspiranteBean objg) throws Exception;

    public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception;

    public List ConsultaGrados(String Nivel) throws Exception;

    public List ConsultaPromedios() throws Exception;

    public String ConsultaAspirante(AspiranteBean obj) throws Exception;

    public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception;

    public TutorBean ConsultaTutor(AcademicoBean objg) throws Exception;

    public CobeneficiarioBean ConsultaCobe(AcademicoBean objg) throws Exception;

    public List ConsultaCobeXcurp(AcademicoBean objg) throws Exception;

    public boolean ActualizaDatosAcademicos(AcademicoBean objg) throws Exception;

    public List ConsultaParentesco() throws Exception;

    public boolean GuardaDatosTutor(TutorBean objg) throws Exception;

    public boolean GuardaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception;

    public boolean ActualizaDatosTutor(TutorBean objg) throws Exception;

    public boolean ActualizaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception;

    public List ConsultaRespuestas() throws Exception;

    public List ConsultaPreguntas(String idbeca) throws Exception;

    public boolean GuardaSocioeconomico(IngresosBean objg) throws Exception;

    public IngresosBean consultaSocioEconomico(String idaspirante, String Ciclo) throws Exception;
    
    public List ConsultaRes_Preg(String idaspirante, String Ciclo) throws Exception;

    public boolean ActualizaSocioeconomico(IngresosBean objg) throws Exception;

    public AcademicoBean ObtenDatosAcademicosAspirante(String idaspirante, String Ciclo) throws Exception;

    public int consultaSecuencia(String secuencia) throws Exception;

    public boolean GuardaFolio(FolioBean objg) throws Exception;

    public String verificaFolio(String idaspirante, String idbeca, String idciclo) throws Exception;

    public List VerificaCurp(String curp) throws Exception;
    
    public boolean ActualizaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception;
    
    public boolean GuardaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception;
}
