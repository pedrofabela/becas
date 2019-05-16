package beans;

import java.io.Serializable;


public class BecasBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String ID_BECA;
	private String NOM_BECA;
	private String ACRO_BECA;
	private String ESTATUS_BECA;
	private String FECHA_INICIO;
	private String FECHA_TERMINO;
        private String POB_OBJ;
        private String IMAGEN;
        private String ID_BECA_AUX;
        private String BASE;
        private String ID_BASE;
        private String ID_REQUISITO;
        private String REQUISITO;
         private String ESTATUS_FECHA;

    public String getESTATUS_FECHA() {
        return ESTATUS_FECHA;
    }

    public void setESTATUS_FECHA(String ESTATUS_FECHA) {
        this.ESTATUS_FECHA = ESTATUS_FECHA;
    }
         
         
         

    public String getID_REQUISITO() {
        return ID_REQUISITO;
    }

    public void setID_REQUISITO(String ID_REQUISITO) {
        this.ID_REQUISITO = ID_REQUISITO;
    }

    public String getREQUISITO() {
        return REQUISITO;
    }

    public void setREQUISITO(String REQUISITO) {
        this.REQUISITO = REQUISITO;
    }
            

    public String getID_BASE() {
        return ID_BASE;
    }

    public void setID_BASE(String ID_BASE) {
        this.ID_BASE = ID_BASE;
    }
            

    public String getBASE() {
        return BASE;
    }

    public void setBASE(String BASE) {
        this.BASE = BASE;
    }
     
    public String getID_BECA() {
        return ID_BECA;
    }

    public void setID_BECA(String ID_BECA) {
        this.ID_BECA = ID_BECA;
    }

    public String getNOM_BECA() {
        return NOM_BECA;
    }

    public void setNOM_BECA(String NOM_BECA) {
        this.NOM_BECA = NOM_BECA;
    }

    public String getACRO_BECA() {
        return ACRO_BECA;
    }

    public void setACRO_BECA(String ACRO_BECA) {
        this.ACRO_BECA = ACRO_BECA;
    }

   

    public String getESTATUS_BECA() {
        return ESTATUS_BECA;
    }

    public void setESTATUS_BECA(String ESTATUS_BECA) {
        this.ESTATUS_BECA = ESTATUS_BECA;
    }

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getFECHA_TERMINO() {
        return FECHA_TERMINO;
    }

    public void setFECHA_TERMINO(String FECHA_TERMINO) {
        this.FECHA_TERMINO = FECHA_TERMINO;
    }

    public String getPOB_OBJ() {
        return POB_OBJ;
    }

    public void setPOB_OBJ(String POB_OBJ) {
        this.POB_OBJ = POB_OBJ;
    }

    public String getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(String IMAGEN) {
        this.IMAGEN = IMAGEN;
    }

    public String getID_BECA_AUX() {
        return ID_BECA_AUX;
    }

    public void setID_BECA_AUX(String ID_BECA_AUX) {
        this.ID_BECA_AUX = ID_BECA_AUX;
    }
    
    
	 
    
	
	
}
