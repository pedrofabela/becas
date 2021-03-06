package business;

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
import java.util.List;
import daos.ConsultaDAO;
import daos.ConsultaDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConsultasBusiness {

    private ConsultaDAO con;

    public ConsultasBusiness() throws Exception {
        this.con = new ConsultaDAOImpl();
    }

    //crear conexion..... business.................
    public Connection crearConexion() throws Exception {
        return con.crearConexion();
    }
//crear statement2

    public Statement crearStatement(Connection cone) throws Exception {
        return con.crearStatement(cone);
    }

    public List ConsultaBecas() throws Exception {
        List lista = this.con.ConsultaBecas();
        return lista;
    }

    public List ConsultaFechasBeca(BecasBean obj, renapoBean ren) throws Exception {
        List lista = this.con.ConsultaFechasBeca(obj, ren);
        return lista;
    }

    public List ConsultaAspirante(BecasBean obj, renapoBean ren) throws Exception {
        List lista = this.con.ConsultaAspirante(obj, ren);

        return lista;

    }

    public List ConsultaDatosAca(AspiranteBean obj, AcademicoBean acad) throws Exception {
        List lista = this.con.ConsultaDatosAca(obj, acad);

        return lista;

    }

    public List ConsultaFechasBecaTodos(BecasBean obj, renapoBean ren) throws Exception {
        List lista = this.con.ConsultaFechasBecaTodos(obj, ren);
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

    public String ConsultaCiclo(BecasBean obj) throws Exception {
        String ciclo = this.con.ConsultaCiclo(obj);
        return ciclo;
    }

    public String ConsultaIntervalo(BecasBean obj, renapoBean ren) throws Exception {
        String ciclo = this.con.ConsultaIntervalo(obj, ren);
        return ciclo;
    }

    public String ConsultaIntervaloSinNivel(BecasBean obj, renapoBean ren) throws Exception {
        String ciclo = this.con.ConsultaIntervaloSinNivel(obj, ren);
        return ciclo;
    }

    public String ConsultaNivel(BecasBean obj, renapoBean ren) throws Exception {
        String ciclo = this.con.ConsultaNivel(obj, ren);
        return ciclo;
    }

    public String ConsultaEscParticipa(BecasBean obj, renapoBean ren) throws Exception {
        String participa = this.con.ConsultaEscParticipa(obj, ren);
        return participa;
    }

    public List ConsultaEstadosCivil() throws Exception {
        List lista = this.con.ConsultaEstadosCivil();
        return lista;
    }

    public List ConsultaColonia(AspiranteBean obj) throws Exception {
        List lista = this.con.ConsultaColonia(obj);
        return lista;
    }

    public boolean GuardaDatosPersonales(AspiranteBean objg) throws Exception {
        return this.con.GuardaDatosPersonales(objg);
    }

    public boolean ActualizaDatosPersonales(AspiranteBean objg) throws Exception {
        return this.con.ActualizaDatosPersonales(objg);
    }

    public AcademicoBean ConsultaCCT(AcademicoBean cct) throws Exception {
        return con.ConsultaCCT(cct);
    }

    public List ConsultaGrados(String Nivel) throws Exception {
        List lista = this.con.ConsultaGrados(Nivel);
        return lista;
    }

    public List ConsultaPromedios() throws Exception {
        List lista = this.con.ConsultaPromedios();
        return lista;
    }

    public String ConsultaAspirante(AspiranteBean obj) throws Exception {
        String id = this.con.ConsultaAspirante(obj);
        return id;
    }

    public boolean GuardaDatosAcademicos(AcademicoBean objg) throws Exception {
        return this.con.GuardaDatosAcademicos(objg);
    }

    public boolean ActualizaDatosAcademicos(AcademicoBean objg) throws Exception {
        return this.con.ActualizaDatosAcademicos(objg);
    }

    public TutorBean ConsultaTutor(AcademicoBean objg) throws Exception {
        return con.ConsultaTutor(objg);
    }

    public CobeneficiarioBean ConsultaCobe(AcademicoBean objg) throws Exception {
        return con.ConsultaCobe(objg);
    }

    public List ConsultaCobeXcurp(AcademicoBean objg) throws Exception {
        List lista = this.con.ConsultaCobeXcurp(objg);
        return lista;
    }

    public List ConsultaParentesco() throws Exception {
        List lista = this.con.ConsultaParentesco();
        return lista;
    }

    public boolean GuardaDatosTutor(TutorBean objg) throws Exception {
        return this.con.GuardaDatosTutor(objg);
    }

    public boolean GuardaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception {
        return this.con.GuardaDatosCobeneficiario(objg);
    }

    public boolean ActualizaDatosTutor(TutorBean objg) throws Exception {
        return this.con.ActualizaDatosTutor(objg);
    }

    public boolean ActualizaDatosCobeneficiario(CobeneficiarioBean objg) throws Exception {
        return this.con.ActualizaDatosCobeneficiario(objg);
    }

    public List ConsultaRespuestas() throws Exception {
        List lista = this.con.ConsultaRespuestas();
        return lista;
    }

    public List ConsultaPreguntas(String idbeca) throws Exception {
        List lista = this.con.ConsultaPreguntas(idbeca);
        return lista;
    }

    public boolean GuardaSocioeconomico(IngresosBean objg) throws Exception {
        return this.con.GuardaSocioeconomico(objg);
    }

    public boolean GuardaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception {
        return this.con.GuardaRespuestas(conn, stat, objg);
    }

    public IngresosBean consultaSocioEconomico(String idaspirante, String Ciclo) throws Exception {
        return con.consultaSocioEconomico(idaspirante, Ciclo);
    }

    public List ConsultaRes_Preg(String idaspirante, String Ciclo) throws Exception {
        List lista = this.con.ConsultaRes_Preg(idaspirante, Ciclo);
        return lista;
    }

    public boolean ActualizaSocioeconomico(IngresosBean objg) throws Exception {
        return this.con.ActualizaSocioeconomico(objg);
    }

    public boolean ActualizaRespuestas(Connection conn, PreparedStatement stat, Respuesta_PreguntaBean objg) throws Exception {
        return this.con.ActualizaRespuestas(conn, stat, objg);
    }

    public AcademicoBean ObtenDatosAcademicosAspirante(String idaspirante, String Ciclo) throws Exception {
        return con.ObtenDatosAcademicosAspirante(idaspirante, Ciclo);
    }

    public int consultaSecuencia(String secuencia) throws Exception {
        int numreg = this.con.consultaSecuencia(secuencia);
        return numreg;
    }

    public boolean GuardaFolio(FolioBean objg) throws Exception {
        return this.con.GuardaFolio(objg);
    }

    public String verificaFolio(String idaspirante, String idbeca, String idciclo) throws Exception {
        String id = this.con.verificaFolio(idaspirante, idbeca, idciclo);
        return id;
    }

    public List VerificaCurp(String curp) throws Exception {
        List lista = this.con.VerificaCurp(curp);
        return lista;
    }

}
