package beans;

import java.io.Serializable;


public class FolioBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	
        private String ID_CICLO;
        private String ID_ASPIRANTE;
        private String ID_BECA;
        private String FOLIO;
        private Integer ID_ASPIRANTEAUX;
        private String STATUS;
        

    public String getID_CICLO() {
        return ID_CICLO;
    }

    public void setID_CICLO(String ID_CICLO) {
        this.ID_CICLO = ID_CICLO;
    }

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

    public String getFOLIO() {
        return FOLIO;
    }

    public void setFOLIO(String FOLIO) {
        this.FOLIO = FOLIO;
    }

    public Integer getID_ASPIRANTEAUX() {
        return ID_ASPIRANTEAUX;
    }

    public void setID_ASPIRANTEAUX(Integer ID_ASPIRANTEAUX) {
        this.ID_ASPIRANTEAUX = ID_ASPIRANTEAUX;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    
    

   

   
    
    

   	
   
	
}
