package beans;

import java.io.Serializable;


public class IngresosBean implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;

	private String RESPUESTA1;
	private String RESPUESTA2;
        private String MONTO;
        private String VALIDACHECK;
        private String ID_ASPIRANTE;
        private String ARCHIVO_INGRESO;
        private String ID_CICLO;
        private String ID_BECA;

    public String getRESPUESTA1() {
        return RESPUESTA1;
    }

    public void setRESPUESTA1(String RESPUESTA1) {
        this.RESPUESTA1 = RESPUESTA1;
    }

    public String getRESPUESTA2() {
        return RESPUESTA2;
    }

    public void setRESPUESTA2(String RESPUESTA2) {
        this.RESPUESTA2 = RESPUESTA2;
    }

    public String getMONTO() {
        return MONTO;
    }

    public void setMONTO(String MONTO) {
        this.MONTO = MONTO;
    }

    public String getVALIDACHECK() {
        return VALIDACHECK;
    }

    public void setVALIDACHECK(String VALIDACHECK) {
        this.VALIDACHECK = VALIDACHECK;
    }

    public String getID_ASPIRANTE() {
        return ID_ASPIRANTE;
    }

    public void setID_ASPIRANTE(String ID_ASPIRANTE) {
        this.ID_ASPIRANTE = ID_ASPIRANTE;
    }

    public String getARCHIVO_INGRESO() {
        return ARCHIVO_INGRESO;
    }

    public void setARCHIVO_INGRESO(String ARCHIVO_INGRESO) {
        this.ARCHIVO_INGRESO = ARCHIVO_INGRESO;
    }

    public String getID_CICLO() {
        return ID_CICLO;
    }

    public void setID_CICLO(String ID_CICLO) {
        this.ID_CICLO = ID_CICLO;
    }

    public String getID_BECA() {
        return ID_BECA;
    }

    public void setID_BECA(String ID_BECA) {
        this.ID_BECA = ID_BECA;
    }
    
    
        
        

   
	
}
