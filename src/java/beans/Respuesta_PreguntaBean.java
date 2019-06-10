package beans;

import java.io.Serializable;


public class Respuesta_PreguntaBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_ASPIRANTE;
        private String ID_BECA;
        private String ID_PREGUNTA;
	private String RESPUESTA;
        private String CICLO;

    public String getID_ASPIRANTE() {
        return ID_ASPIRANTE;
    }

    public void setID_ASPIRANTE(String ID_ASPIRANTE) {
        this.ID_ASPIRANTE = ID_ASPIRANTE;
    }

    public String getID_BECA() {
        return ID_BECA;
    }

    public void setID_BECA(String ID_BECA) {
        this.ID_BECA = ID_BECA;
    }

    public String getID_PREGUNTA() {
        return ID_PREGUNTA;
    }

    public void setID_PREGUNTA(String ID_PREGUNTA) {
        this.ID_PREGUNTA = ID_PREGUNTA;
    }

    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    public String getCICLO() {
        return CICLO;
    }

    public void setCICLO(String CICLO) {
        this.CICLO = CICLO;
    }

  
	
   
	
}
