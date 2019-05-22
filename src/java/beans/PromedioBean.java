package beans;

import java.io.Serializable;


public class PromedioBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_PROMEDIO;
	private String PROMEDIO;

    public String getID_PROMEDIO() {
        return ID_PROMEDIO;
    }

    public void setID_PROMEDIO(String ID_PROMEDIO) {
        this.ID_PROMEDIO = ID_PROMEDIO;
    }

    public String getPROMEDIO() {
        return PROMEDIO;
    }

    public void setPROMEDIO(String PROMEDIO) {
        this.PROMEDIO = PROMEDIO;
    }
      

  
   
	
}
