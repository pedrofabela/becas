package beans;

import java.io.Serializable;


public class ColoniasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String COLONIA;
	private String MUNICIPIO;
        private String ID_MUNICIPIO;

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(String MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    public String getID_MUNICIPIO() {
        return ID_MUNICIPIO;
    }

    public void setID_MUNICIPIO(String ID_MUNICIPIO) {
        this.ID_MUNICIPIO = ID_MUNICIPIO;
    }

   
	
   
	
}
