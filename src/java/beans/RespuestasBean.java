package beans;

import java.io.Serializable;


public class RespuestasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_RESPUESTA;
	private String RESPUESTA;
        private String STATUS;

    public String getID_RESPUESTA() {
        return ID_RESPUESTA;
    }

    public void setID_RESPUESTA(String ID_RESPUESTA) {
        this.ID_RESPUESTA = ID_RESPUESTA;
    }

    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
                
       

   
   
	
   
	
}
