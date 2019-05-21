/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.renapoBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author pedro
 */
public class consultaRenapo {
    
       renapoBean objRenapo = new renapoBean();

    public renapoBean getObjRenapo() {
        return objRenapo;
    }

    public void setObjRenapo(renapoBean objRenapo) {
        this.objRenapo = objRenapo;
    }
    
    
    
    
    
    
    
    
    
    
    
     class request {

        private consulta consulta;

        public consulta getConsulta() {
            return consulta;
        }

        public void setConsulta(consulta consulta) {
            this.consulta = consulta;
        }

    }

    class consulta {

        private data data;

        public data getData() {
            return data;
        }

        public void setData(data data) {
            this.data = data;
        }

    }

    class data {

        private String CURP;

        public String getCURP() {
            return CURP;
        }

        public void setCURP(String CURP) {
            this.CURP = CURP;
        }

    }

    class Request {

        private request request;

        public request getRequest() {
            return request;
        }

        public void setRequest(request request) {
            this.request = request;
        }

    }
    
    
    public renapoBean consultaRenapo(String CURP) throws MalformedURLException, IOException{
         
        
        
          final String POST_PARAMS = "{\n"
                + "    \"request\": {\n"
                + "        \"consulta\": \"CURP\",\n"
                + "        \"data\": {\n"
                + "            \"CURP\": \""+CURP+"\"\n"
                + "        }\n"
                + "    }\n"
                + "}";

     
      

        System.out.println(POST_PARAMS);
        URL obj = new URL("https://desabus.edomex.gob.mx/bussrv/sei/dkb_frRENAPO1.php/consulta");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Authorization", "Basic Y21zcmVnOiM2RyV1am8j");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        
     

        
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
            String inputLine;
            
            
            StringBuffer response = new StringBuffer();
            
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            
            
            in.close();
      
            String curpF;
            Gson gson = new Gson();
            
            JsonElement elementoResultado = gson.fromJson(response.toString(), JsonElement.class);
            JsonObject jsonResultado = elementoResultado.getAsJsonObject();

            curpF = jsonResultado.get("response").toString().replace("[", "");
            curpF = curpF.replace("]", "");


            JsonElement elementoPersona = gson.fromJson(curpF, JsonElement.class);
            JsonObject jsonPersona = elementoPersona.getAsJsonObject();

            System.out.println("RESULTADO JSON: " + jsonResultado.get("response"));
            System.out.println("PERSONA JSON : " + jsonPersona.get("apellidoMaterno"));
            System.out.println("PERSONA JSON : " + jsonPersona.get("apellidoPaterno"));
            
            objRenapo.setCONSULTA_CURP(String.valueOf(jsonPersona.get("curp")).replace("\"", ""));
            objRenapo.setNOMBRE_RENAPO(String.valueOf(jsonPersona.get("nombre")).replace("\"", ""));
            objRenapo.setAPATERNO_RENAPO(String.valueOf(jsonPersona.get("apellidoPaterno")).replace("\"", ""));
            objRenapo.setAMATERNO_RENAPO(String.valueOf(jsonPersona.get("apellidoMaterno")).replace("\"", ""));
            objRenapo.setGENERO_RENAPO(String.valueOf(jsonPersona.get("sexo")).replace("\"", ""));
            objRenapo.setENTIDAD_NACIMINETO_RENAPO(String.valueOf(jsonPersona.get("cveEntidadNacimiento")).replace("\"", ""));
            objRenapo.setFEC_NAC_RENAPO(String.valueOf(jsonPersona.get("fechaNacimientoAxu")).replace("\"", ""));
            objRenapo.setNACIONALIDAD_RENAPO(String.valueOf(jsonPersona.get("nacionalidad")).replace("\"", ""));

            
         if( objRenapo.getGENERO_RENAPO().equals("H")){
             
             objRenapo.setGENERO_RENAPO("HOMBRE");
         }
       
          if( objRenapo.getGENERO_RENAPO().equals("M")){
             
             objRenapo.setGENERO_RENAPO("MUJER");
         }
           if( objRenapo.getGENERO_RENAPO().length()<1){
             
             objRenapo.setGENERO_RENAPO("SIN INFORMACIÓN");
         }
       
        
            
            
            
            
            
            
            
           
            
        } else {
            System.out.println("ERROR AL CONSULTAR");
             
        }
      

        
        return objRenapo;
        
        
        
    }
    
    
    
    
}
