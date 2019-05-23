package beans;

import java.io.Serializable;


public class ParentezcoBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_PARENTESCO;
	private String NOM_PARENTESCO;

    public String getID_PARENTESCO() {
        return ID_PARENTESCO;
    }

    public void setID_PARENTESCO(String ID_PARENTESCO) {
        this.ID_PARENTESCO = ID_PARENTESCO;
    }

    public String getNOM_PARENTESCO() {
        return NOM_PARENTESCO;
    }

    public void setNOM_PARENTESCO(String NOM_PARENTESCO) {
        this.NOM_PARENTESCO = NOM_PARENTESCO;
    }
       
       
	
   
	
}
