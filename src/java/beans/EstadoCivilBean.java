package beans;

import java.io.Serializable;


public class EstadoCivilBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_ESTADO_CIVIL;
	private String ESTADO_CIVIL;

    public String getID_ESTADO_CIVIL() {
        return ID_ESTADO_CIVIL;
    }

    public void setID_ESTADO_CIVIL(String ID_ESTADO_CIVIL) {
        this.ID_ESTADO_CIVIL = ID_ESTADO_CIVIL;
    }

    public String getESTADO_CIVIL() {
        return ESTADO_CIVIL;
    }

    public void setESTADO_CIVIL(String ESTADO_CIVIL) {
        this.ESTADO_CIVIL = ESTADO_CIVIL;
    }
	
   
	
}
