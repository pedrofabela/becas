package utilidades;

import java.io.Serializable;

public class Constantes implements Serializable {
	private static final long serialVersionUID = -3358884679090829992L;
	
     //GENERALES
	 public static final String tablaDual="dual";
	 public static final String nombreSistema = "SEGESCN";
	 public static String usuarioseg= "SEGESCN";
	 public static final String modulo0 = "0";
	 public static final String moduloSegEsc = "segesc";

	 
	 //JOVENES CON VALOR
     //TABLAS
	 public static final String TablaUsuarios="TBL_USUARIOS";
	 public static final String TablaModulosPerfiles="TBL_MODULOSPERFILES";
	 public static final String TablaModulos="TBL_MODULOS";
	 public static final String CatBecas= "CAT_BECAS";
         public static final String TablaEstados= "CAT_ESTADO_CIVIL";
         public static final String TablaAspirantes="TBL_ASPIRANTES";
         public static final String TablaDatosAcademicos="TBL_DATOS_ACADEMICOS";
         public static final String TablaTutor="TBL_PADRETUTOR";
         public static final String TablaCobeneficiario="TBL_COBENEFICIARIO";
         public static final String TablaSocioEconomico="TBL_SOCIOECONOMICO";
         public static final String TablaRespuesta_Preguntas="TBL_RESPUESTA_PREGUNTAS";
         public static final String TablaFolio="TBL_FOLIOS_BECAS";

	 
		 //**************DESARROLLO**********************************************************
	
		public static final boolean esDesarrollo = true;
		public static final String rutaProyectos = "http://172.20.2.110:8080/";	
		public static final String rutareportesjasper = "C:/Soft Stif/JASPERS/becasnuevas/";		                                                  
		public static final String rutaArch = "C:/ARCHIVOS/becas/";  //cambair cuando els ervidor este activo
		public static final String rutaEstilos = "http://172.20.2.110:8080/estilosUDAI/" ;
		public static final String rutaManuales = "C:/manuales/permanecerEstudiando.pdf";
		public static final String rutaImages ="C:/Users/gioca/Desktop/imagenes Tablero/";		
		//*/
		
	 
		/*//******************* PRODUCCION*******************************************************
      
		public static final boolean esDesarrollo = true;		
		public static final String rutaProyectos =   "http://aplicaciones.edugem.gob.mx/";
		public static final String rutareportesjasper = "/usr/local/tomcat/webapps/";		                                                  
		public static final String rutaArch = "/archivos/";  				
		public static final String rutaEstilos = "http://aplicaciones.edugem.gob.mx/estilosUDAI/";		                                        
		public static final String rutaImages = "/usr/local/tomcat/webapps/";
		public static final String rutaManuales = "http://aplicaciones.edugem.gob.mx/manuales/ManualUsuario3P.pdf";		
		//*/
	  

	  public static void enviaMensajeConsola(String cadena){
			if(Constantes.esDesarrollo){
				System.out.println("MENSAJE_DESARROLLO: "+cadena);
			}
		}
} 

