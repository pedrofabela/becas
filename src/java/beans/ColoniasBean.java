package beans;

import java.io.Serializable;


public class ColoniasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String COLONIA;
	private String MUNICIPIO;

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

   
	
   
	
}
