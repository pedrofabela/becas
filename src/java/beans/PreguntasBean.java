package beans;

import java.io.Serializable;


public class PreguntasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_PREGUNTA;
	private String PREGUNTA;
        private String TIPO_PREGUNTA;

    public String getID_PREGUNTA() {
        return ID_PREGUNTA;
    }

    public void setID_PREGUNTA(String ID_PREGUNTA) {
        this.ID_PREGUNTA = ID_PREGUNTA;
    }

    public String getPREGUNTA() {
        return PREGUNTA;
    }

    public void setPREGUNTA(String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }

    public String getTIPO_PREGUNTA() {
        return TIPO_PREGUNTA;
    }

    public void setTIPO_PREGUNTA(String TIPO_PREGUNTA) {
        this.TIPO_PREGUNTA = TIPO_PREGUNTA;
    }

   
        
       

   
   
	
   
	
}
