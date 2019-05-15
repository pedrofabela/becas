<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="assets/demo/demo.css" rel="stylesheet" />
  
   <script type="text/javascript">

            function SeleccionMenu(accion){
                 if (accion == "accesoManual"){
                     document.formularioPrincipal.target="_blank";
                     document.formularioPrincipal.action = accion;
                     document.formularioPrincipal.submit();
                 }else {
                        document.formularioPrincipal.target = "_self";
                        document.formularioPrincipal.action = accion;
                        document.formularioPrincipal.submit();
                    }
                }

        </script>

</head>
 
<body class="">
   <s:form name="formularioPrincipal" method="post" id="formularioPrincipal">
  
       
        <ul class="nav">
            <s:iterator value="modulosAUX" id="modulosAUX" status="stat">
                <s:if test='CVE_MODPADRE=="S"'>
          <li class="nav-item ">
            <a class="nav-link" data-toggle="dropdown" href="#Horizontal-Menus">
                <i class="material-icons"><s:property value="IMAGEN"></s:property></i>
              <p>  <s:property value="DESC_MOD"/>
                <b class="caret"></b>
              </p>
            </a>
                <div class="dropdown-menu dropdown-menu-top-right" style="width: 88%;" aria-labelledby="Horizontal-Menus">
              
                  <s:iterator value="modulosAUXP" id="modulosAUXP" status="stat">
                      <s:if test='CVE_MODULO==MODPADRE'>
                
                          <a class="dropdown-item " style="color: black;"  href="Javascript:SeleccionMenu('<s:property value="ACCION"/>')">                            
                        <s:property value="MOD"/>
                        <s:set var="myVar">
                            <s:property value="MOD.length()" />
                        </s:set>
                        <s:if test='MOD.length()<40'>
                            <c:forEach begin="${myVar}" end="40" varStatus="lp">
                                &nbsp;
                            </c:forEach>
                        </s:if>                                                        
                    </a>  
                
                </s:if>
                </s:iterator>
              </div>
          </li>
          </s:if>
           <s:hidden name = "modulosAUX[%{#stat.index}].CVE_MODULO" id="CVE_MODULO"></s:hidden>
           <s:hidden name = "modulosAUX[%{#stat.index}].CVE_MODPADRE" id="CVE_MODPADRE"></s:hidden>
           <s:hidden name = "modulosAUX[%{#stat.index}].DESC_MOD" id="DESC_MOD"></s:hidden>
           <%-- <s:hidden  name = "modulosAUX[%{#stat.index}].ACTION" id="ACTION"></s:hidden> --%>
           <s:hidden name = "modulosAUX[%{#stat.index}].IMAGEN" id="IMAGEN"></s:hidden>
           <s:hidden name = "modulosAUX[%{#stat.index}].NAMEUNIDAD" id="NAMEUNIDAD"></s:hidden>
           <s:hidden name = "modulosAUX[%{#stat.index}].VALORU" id="VALORU"></s:hidden>
        </s:iterator>
        </ul>
        <s:iterator value="modulosAUXP" id="modulosAUXP" status="stat">                        
            <s:hidden  name = "modulosAUXP[%{#stat.index}].MODULO" id="MODULO"></s:hidden>
            <s:hidden  name = "modulosAUXP[%{#stat.index}].MODPADRE" id="MODPADRE"></s:hidden>
            <s:hidden  name = "modulosAUXP[%{#stat.index}].MOD" id="MOD"></s:hidden>
            <s:hidden  name = "modulosAUXP[%{#stat.index}].ACCION" id="ACCION"></s:hidden>
            <s:hidden name =  "modulosAUXP[%{#stat.index}].CVE_ACCESO_AUX" id="CVE_ACCESO_AUX"></s:hidden>
        </s:iterator>
     
                      
      </s:form>        
</body>

</html>