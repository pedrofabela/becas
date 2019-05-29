<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>




<script>
  var m = "D";
  var expreg = new RegExp("^[A-C]$");
  
  if(expreg.test(m))
   // alert("La letra si pasa");
  else
    //alert("La letra no pasa");
        
</script>

 <script type="text/javascript"> 
          
     function validarInput(input) {
	var curp = input.value.toUpperCase(),
    	resultado = document.getElementById("resultado"),
        valido = "No válido";
        div = document.getElementById('btnvalidar');
        div.style.display = 'none';
                
    if (curpValida(curp)) {
    	valido = "Válido";
        resultado.classList.add("ok");
        
        div = document.getElementById('btnvalidar');
        div.style.display = '';    
    } else {
    	resultado.classList.remove("ok");
    }
        
    resultado.innerText = "\nFormato de CURP: " + valido;
}

function curpValida(curp) {
	var re = /^([A-Z][AEIOUX][A-Z]{2}\d{2}(?:0\d|1[0-2])(?:[0-2]\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/,
       
                validado = curp.match(re);
	
    if (!validado)  //Coincide con el formato general?
    	return false;
    
    //Validar que coincida el dígito verificador
    function digitoVerificador(curp17) {
        //Fuente https://consultas.curp.gob.mx/CurpSP/
        var diccionario  = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ",
            lngSuma      = 0.0,
            lngDigito    = 0.0;
        for(var i=0; i<17; i++)
            lngSuma= lngSuma + diccionario.indexOf(curp17.charAt(i)) * (18 - i);
        lngDigito = 10 - lngSuma % 10;
        if(lngDigito == 10)
            return 0;
        return lngDigito;
    }
    if (validado[2] != digitoVerificador(validado[1])) 
    	return false;
        
	return true; //Validado
}
     
          </script>


<script>
function myFunction() {
  var x = document.getElementById("myInput").value;
  
  div = document.getElementById('btnvalidar2');
            div.style.display = 'none';
   
 
  x=x.toUpperCase();
  myFunction2(x);
  
}

function myFunction2(x){
    
  var expreg = /^[1]{1}[5]{1}[A-Z]{3}[0-9]{4}[A-Z]{1}$/;
  
  if(expreg.test(x)){   
        div = document.getElementById('btnvalidar2');
            div.style.display = '';
            
            document.altaPetiForm.myInput.value = x;
            
	document.getElementById("demo").innerHTML = "CCT Correcta ";
        
        }
  else {
   
   if(x.length==10){
   document.getElementById("demo").innerHTML = "CCT no valida favor de verificar " ;
  
   }
    else 
        
        
   document.getElementById("demo").innerHTML = "validando CCT...";
   
        }
    
}


</script>


<html lang="en">

<head>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="../../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../../assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>
    Requisitos de la beca  
  </title>
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- CSS Files -->
  <link href="assets/css/material-dashboard.css?v=2.1.0" rel="stylesheet" />
  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link href="assets/demo/demo.css" rel="stylesheet" />
</head>

<body class="">
     <s:form name="altaPetiForm" id="altaPetiForm" enctype="multipart/form-data" >

  <div class="wrapper ">
    <div class="sidebar" data-color="rose" data-background-color="black" data-image="assets/img/sidebar-1.jpg">
      <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

        Tip 2: you can also add an image using data-image tag
    -->
     
      <div class="sidebar-wrapper">
        <div class="user">
          <div class="photo">
            <img src="assets/img/faces/m.png" />
          </div>
          <div class="user-info">
            <a data-toggle="collapse" href="#collapseExample" class="username">
              <span>
                Becas
                <b class="caret"></b>
              </span>
            </a>
            <div class="collapse" id="collapseExample">
                <ul class="nav">
                    <s:iterator value="ListaBecas" id="ListaBecas" status="stat">   
                        <li class="nav-item">
                            <a class="nav-link" href="#">

                                <span class="sidebar-normal"><s:property value="NOM_BECA"/> </span>
                            </a>
                        </li> 
                            <s:hidden name = "ListaBecas[%{#stat.index}].ID_BECA" id="ID_BECA"></s:hidden>
                            <s:hidden name = "ListaBecas[%{#stat.index}].NOM_BECA" id="NOM_BECA"></s:hidden>
                    </s:iterator>              
                </ul>
            </div>
          </div>
        </div>
        <ul class="nav">
         <li class="nav-item ">
            <a class="nav-link" href="Javascript:Regreso2('Inicio')">
              <i class="material-icons">dashboard</i>
              <p> Inicio </p>
            </a>
          </li>  
            <!-- 
          <li class="nav-item ">
            <a class="nav-link" href="examples/calendar.html">
              <i class="material-icons">date_range</i>
              <p> Calendar </p>
            </a>
          </li>-->
        </ul>
      </div>   
    </div>
    <div class="main-panel">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
        <div class="container-fluid">
          <div class="navbar-wrapper">
            <div class="navbar-minimize"></div>     
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
            <span class="navbar-toggler-icon icon-bar"></span>
          </button>    
        </div>
      </nav>
      <!-- End Navbar -->
      <div class="content">
        <div class="container-fluid">
          
          <div class="row">
                 
              <div class="col-md-12" style="margin-bottom: 30px;">

                  <img src="assets/img/banner3.jpg" style="width: 100%;"></img>

              </div>
           
              <div class="col-md-4">
                  <div class="card">
                      <div class="card-header card-header-image" data-header-animation="true">
                          <s:iterator value="ListaBecas" id="ListaBecas" status="stat">                                 
                              <s:if test="objdatos.ID_BECA_AUX==ID_BECA"> 
                                  <img class="img" src="assets/img/<s:property value="IMAGEN"/>"/>
                              </s:if>     
                                   <s:hidden name = "ListaBecas[%{#stat.index}].ID_BECA" id="ID_BECA"></s:hidden>
                                   <s:hidden name = "ListaBecas[%{#stat.index}].NOM_BECA" id="NOM_BECA"></s:hidden>
                                   <s:hidden name = "ListaBecas[%{#stat.index}].IMAGEN" id="IMAGEN"></s:hidden>
                          </s:iterator>   

                      </div>  
                      <div class="card-footer">                    
                          <div class="row">
                              
                              <s:if test="banConCct">
                              
                                  <div class="col-md-12 text-center">
                                      <div style="margin: auto;"><h4 style="color:purple">Favor de Capturar la Clave de Centro de Trabajo de tu Escuela (CCT)</h4></div>

                                  </div>

                                  <div class="col-md-12" style="margin-top: 15px;">
                                      <label for="examplePass" class="bmd-label-floating">CCT</label>
                                      <s:textfield name="objRenapo.CCT" id="myInput"  cssClass="form-control " placeholder="Elemplo: 15ETV0027B" oninput="myFunction()" cssStyle="text-transform: uppercase;"  ></s:textfield>
                                  </div>
                                  <div class="col-md-12" style="margin-top: 15px; " >

                                      <p id="demo"></p>

                                  </div>                           
                                  <div id="btnvalidar2">
                                      <div class="col-md-12"  >
                                          <a href="Javascript:consultaCctBase('ConsultaCct')" class="btn btn-success" >Buscar Escuela</a>
                                      </div>
                                  </div>
                              
                             </s:if>                             
                              <div class="col-md-12"  >                       
                              <s:fielderror fieldName="NOPARTICIPA" cssClass="alert alert-danger"></s:fielderror> 
                              </div>                       
                              <s:if test="objDatosA.NOMCCT.length()>0">
                                  <div class="col-md-12 text-center" style="margin-top: 20px;">
                                      <div style="margin: auto;"><h4 style="color:purple">Datos de la Escuela Elegida</h4></div>

                                  </div>
                                  <div class="form-group col-md-12">                                    
                                      <label for="examplePass" class="bmd-label-floating">CCT </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.CCT" id="objDatosA.CCT" readonly="true"/>
                                      <s:fielderror fieldName="CCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">NOMBRE DE LA INSTITUCIÓN </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.NOMCCT" id="NOMCCT" readonly="true"/>
                                      <s:fielderror fieldName="NOMCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">DOMICILIO DE LA INSTITUCIÓN </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.DOMCCT" id="objDatosA.DOMCCT" readonly="true" />
                                      <s:fielderror fieldName="DOMCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">NIVEL EDUCATIVO </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.NIVELCCT" id="NIVELCCT" readonly="true"/>
                                      <s:fielderror fieldName="NIVELCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">TURNO</label>
                                      <s:textfield cssClass="form-control" name="objDatosA.TURNO" id="objDatosA.TURNO" readonly="true"/>
                                      <s:fielderror fieldName="TURNO" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 


                                      <h6 class="text-center">Si esta no es tu Escuela pulsa  <a href="Javascript:Regreso2('ConsultaReq')">Aquí </a>para buscar de nuevo</h6>

                              </s:if> 

                              
                              
                                      <s:if test="banConCurp">
                              
                              <div class="col-md-12 text-center" style="margin-top: 25px;">
                                  <div style="margin: auto;"><h4 style="color:purple">Favor de capturar la CURP del Aspirante a la Beca</h4></div>
                                  
                              </div>

                              <div class="col-md-12" style="margin-top: 15px;">
                                  <label for="examplePass" class="bmd-label-floating">CURP</label>
                                    <s:textfield name="objRenapo.CONSULTA_CURP" id="CONSULTA_CURP"  cssClass="form-control " oninput="validarInput(this)"></s:textfield>
                                     <s:fielderror fieldName="ERRORCURP" id="ERRORCURP" cssClass="alert alert-danger"></s:fielderror>
                              </div>
                              
                              <div class="col-md-12" style="margin-top: 15px;" >
                                   <pre id="resultado" > </pre>
                              </div>
                              
                               
                              
                                    <div id="btnvalidar">

                                        <div class="col-md-12" style="margin-top: 15px;">
                                       
    

                                        <div class="togglebutton">
                                            <label>
                                                <input type="checkbox" name="ACUERDO" id="ACUERDO" value="1"  ></input>
                                                    <span class="toggle"></span>
                                                   Estoy de acuerdo y cumplo con las bases y requisitos de la beca a la que aspiro.
                                            </label>
                                        </div>
                                       
                                        
                                        
                                        
                                        </div>

                                        <div class="col-md-12"  >
                                            <a href="Javascript:Regreso('Registro')" class="btn btn-success" >Registrar Beca</a>
                                        </div>
                                        
                                       
                                        
                                    </div>
                              
                               </s:if>
                              
                          </div>
                      </div>
                     
                  </div>
              </div>         
              <div class="col-md-8">
                  <div class="card">

                      <div class="card-body">
                          <div id="accordion" role="tablist">
                              
                              <div class="card-collapse">
                                  <div class="card-header" role="tab" id="headingOne">
                                      <h5 class="mb-0">
                                          <a data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" class="collapsed">
                                              Población Objetivo
                                              <i class="material-icons">keyboard_arrow_down</i>
                                          </a>
                                      </h5>
                                  </div>
                                  <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion" style="">
                                      <div class="card-body">
                                          <s:iterator value="ListaBecas" id="ListaBecas" status="stat">                                 

                                              <s:property value="POB_OBJ"/>  
                                              <s:hidden name = "ListaBecas[%{#stat.index}].POB_OBJ" id="POB_OBJ"></s:hidden>

                                          </s:iterator>   
                                      </div>
                                  </div>
                              </div>
                              
                              
                              
                              <div class="card-collapse">
                                  <div class="card-header" role="tab" id="headingTwo">
                                      <h5 class="mb-0">
                                          <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                              Bases
                                              <i class="material-icons">keyboard_arrow_down</i>
                                          </a>
                                      </h5>
                                  </div>
                                  <div id="collapseTwo" class="collapse show" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion">
                                      <div class="card-body">
                                          <div class="list-group">

                                              <s:iterator value="ListaBases" id="ListaBses" status="stat">
                                                  <a href="#" class="list-group-item list-group-item-action">
                                                      <i class="material-icons" >verified_user</i> &nbsp; &nbsp; <s:property value="BASE"></s:property>
                                                  </a>
                                                  
                                                  <s:hidden name = "ListaBases[%{#stat.index}].BASE" id="BASE"></s:hidden>
                                                  
                                              </s:iterator>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <div class="card-collapse">
                                  <div class="card-header" role="tab" id="headingThree">
                                      <h5 class="mb-0">
                                          <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                              Requisitos
                                              <i class="material-icons">keyboard_arrow_down</i>
                                          </a>
                                      </h5>
                                  </div>
                                  <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
                                      <div class="card-body">
                                          <div class="list-group">
                                              <s:iterator value="ListaRequisitos" id="ListaBsesRequisitos" status="stat">
                                                  <a href="#" class="list-group-item list-group-item-action">
                                                      <i class="material-icons" >check_circle_outline</i> &nbsp; &nbsp; <s:property value="REQUISITO"></s:property>
                                                  </a>
                                                   <s:hidden name = "ListaRequisitos[%{#stat.index}].REQUISITO" id="REQUISITO"></s:hidden>
                                              </s:iterator>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>                       
                      </div>
                  </div>
              </div> 
          </div>
                
        </div>
      </div>
      <footer class="footer">
        <div class="container-fluid">
          <nav class="float-left">
            <ul>
              <li>
                <a href="https://www.creative-tim.com">
                  Creative Tim
                </a>
              </li>
              <li>
                <a href="https://creative-tim.com/presentation">
                  About Us
                </a>
              </li>
              <li>
                <a href="http://blog.creative-tim.com">
                  Blog
                </a>
              </li>
              <li>
                <a href="https://www.creative-tim.com/license">
                  Licenses
                </a>
              </li>
            </ul>
          </nav>
          <div class="copyright float-right">
            &copy;
            <script>
              document.write(new Date().getFullYear())
            </script>, made with <i class="material-icons">favorite</i> by
            <a href="https://www.creative-tim.com" target="_blank">Creative Tim</a> for a better web.
          </div>
        </div>
          
      </footer>
                              
                              
                              <s:textfield  name="objdatos.ID_BECA_AUX" id="ID" style='visibility:hidden' ></s:textfield>  
        <s:textfield  name="objRenapo.ID_CICLO" id="ID_CICLO" style='visibility:hidden' ></s:textfield>  
        <s:textfield  name="objRenapo.INTERVALO" id="INTERVALO" style='visibility:hidden' ></s:textfield>  
         <s:textfield  name="objRenapo.NIVEL" id="NIVEL" style='visibility:hidden' ></s:textfield>  
       <s:textfield  name="objdatos.RESTRICCION_ESC" id="RESTRICCION" style='visibility:hidden' ></s:textfield>   
        <s:textfield  name="objdatos.FECHA_INICIO" id="FECHA_INICIO" style='visibility:hidden' ></s:textfield>  
         <s:textfield  name="objdatos.FECHA_TERMINO" id="FECHA_TERMINO" style='visibility:hidden' ></s:textfield>  
          <s:textfield  name="objRenapo.EN_PERIODO" id="EN_PERIODO" style='visibility:hidden'></s:textfield>  
         
     </s:form>                       
                              
                              
                              
                              
    </div>
  </div>
  <div class="fixed-plugin">
    <div class="dropdown show-dropdown">
      <a href="#" data-toggle="dropdown">
        <i class="fa fa-cog fa-2x"> </i>
      </a>
      <ul class="dropdown-menu">
        <li class="header-title"> Sidebar Filters</li>
        <li class="adjustments-line">
          <a href="javascript:void(0)" class="switch-trigger active-color">
            <div class="badge-colors ml-auto mr-auto">
              <span class="badge filter badge-purple" data-color="purple"></span>
              <span class="badge filter badge-azure" data-color="azure"></span>
              <span class="badge filter badge-green" data-color="green"></span>
              <span class="badge filter badge-warning" data-color="orange"></span>
              <span class="badge filter badge-danger" data-color="danger"></span>
              <span class="badge filter badge-rose active" data-color="rose"></span>
            </div>
            <div class="clearfix"></div>
          </a>
        </li>
        <li class="header-title">Sidebar Background</li>
        <li class="adjustments-line">
          <a href="javascript:void(0)" class="switch-trigger background-color">
            <div class="ml-auto mr-auto">
              <span class="badge filter badge-black active" data-background-color="black"></span>
              <span class="badge filter badge-white" data-background-color="white"></span>
              <span class="badge filter badge-red" data-background-color="red"></span>
            </div>
            <div class="clearfix"></div>
          </a>
        </li>
        <li class="adjustments-line">
          <a href="javascript:void(0)" class="switch-trigger">
            <p>Sidebar Mini</p>
            <label class="ml-auto">
              <div class="togglebutton switch-sidebar-mini">
                <label>
                  <input type="checkbox">
                  <span class="toggle"></span>
                </label>
              </div>
            </label>
            <div class="clearfix"></div>
          </a>
        </li>
        <li class="adjustments-line">
          <a href="javascript:void(0)" class="switch-trigger">
            <p>Sidebar Images</p>
            <label class="switch-mini ml-auto">
              <div class="togglebutton switch-sidebar-image">
                <label>
                  <input type="checkbox" checked="">
                  <span class="toggle"></span>
                </label>
              </div>
            </label>
            <div class="clearfix"></div>
          </a>
        </li>
        <li class="header-title">Images</li>
        <li class="active">
          <a class="img-holder switch-trigger" href="javascript:void(0)">
            <img src="assets/img/sidebar-1.jpg" alt="">
          </a>
        </li>
        <li>
          <a class="img-holder switch-trigger" href="javascript:void(0)">
            <img src="assets/img/sidebar-2.jpg" alt="">
          </a>
        </li>
        <li>
          <a class="img-holder switch-trigger" href="javascript:void(0)">
            <img src="assets/img/sidebar-3.jpg" alt="">
          </a>
        </li>
        <li>
          <a class="img-holder switch-trigger" href="javascript:void(0)">
            <img src="assets/img/sidebar-4.jpg" alt="">
          </a>
        </li>
        <li class="button-container">
          <a href="https://www.creative-tim.com/product/material-dashboard-pro" target="_blank" class="btn btn-rose btn-block btn-fill">Buy Now</a>
          <a href="https://demos.creative-tim.com/material-dashboard-pro/docs/2.1/getting-started/introduction.html" target="_blank" class="btn btn-default btn-block">
            Documentation
          </a>
          <a href="https://www.creative-tim.com/product/material-dashboard" target="_blank" class="btn btn-info btn-block">
            Get Free Demo!
          </a>
        </li>
        <li class="button-container github-star">
          <a class="github-button" href="https://github.com/creativetimofficial/ct-material-dashboard-pro" data-icon="octicon-star" data-size="large" data-show-count="true" aria-label="Star ntkme/github-buttons on GitHub">Star</a>
        </li>
        <li class="header-title">Thank you for 95 shares!</li>
        <li class="button-container text-center">
          <button id="twitter" class="btn btn-round btn-twitter"><i class="fa fa-twitter"></i> &middot; 45</button>
          <button id="facebook" class="btn btn-round btn-facebook"><i class="fa fa-facebook-f"></i> &middot; 50</button>
          <br>
          <br>
        </li>
      </ul>
    </div>
  </div>
  <!--   Core JS Files   -->
  <script src="assets/js/core/jquery.min.js"></script>
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap-material-design.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!-- Plugin for the momentJs  -->
  <script src="assets/js/plugins/moment.min.js"></script>
  <!--  Plugin for Sweet Alert -->
  <script src="assets/js/plugins/sweetalert2.js"></script>
  <!-- Forms Validations Plugin -->
  <script src="assets/js/plugins/jquery.validate.min.js"></script>
  <!-- Plugin for the Wizard, full documentation here: https://github.com/VinceG/twitter-bootstrap-wizard -->
  <script src="assets/js/plugins/jquery.bootstrap-wizard.js"></script>
  <!--	Plugin for Select, full documentation here: http://silviomoreto.github.io/bootstrap-select -->
  <script src="assets/js/plugins/bootstrap-selectpicker.js"></script>
  <!--  Plugin for the DateTimePicker, full documentation here: https://eonasdan.github.io/bootstrap-datetimepicker/ -->
  <script src="assets/js/plugins/bootstrap-datetimepicker.min.js"></script>
  <!--  DataTables.net Plugin, full documentation here: https://datatables.net/  -->
  <script src="assets/js/plugins/jquery.dataTables.min.js"></script>
  <!--	Plugin for Tags, full documentation here: https://github.com/bootstrap-tagsinput/bootstrap-tagsinputs  -->
  <script src="assets/js/plugins/bootstrap-tagsinput.js"></script>
  <!-- Plugin for Fileupload, full documentation here: http://www.jasny.net/bootstrap/javascript/#fileinput -->
  <script src="assets/js/plugins/jasny-bootstrap.min.js"></script>
  <!--  Full Calendar Plugin, full documentation here: https://github.com/fullcalendar/fullcalendar    -->
  <script src="assets/js/plugins/fullcalendar.min.js"></script>
  <!-- Vector Map plugin, full documentation here: http://jvectormap.com/documentation/ -->
  <script src="assets/js/plugins/jquery-jvectormap.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="assets/js/plugins/nouislider.min.js"></script>
  <!-- Include a polyfill for ES6 Promises (optional) for IE11, UC Browser and Android browser support SweetAlert -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
  <!-- Library for adding dinamically elements -->
  <script src="assets/js/plugins/arrive.min.js"></script>
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <!-- Chartist JS -->
  <script src="assets/js/plugins/chartist.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="assets/js/material-dashboard.js?v=2.1.0" type="text/javascript"></script>
  <!-- Material Dashboard DEMO methods, don't include it in your project! -->
  <script src="assets/demo/demo.js"></script>
  <script>
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');

        $sidebar_img_container = $sidebar.find('.sidebar-background');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');

        window_width = $(window).width();

        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();

        if (window_width > 767 && fixed_plugin_open == 'Dashboard') {
          if ($('.fixed-plugin .dropdown').hasClass('show-dropdown')) {
            $('.fixed-plugin .dropdown').addClass('open');
          }

        }

        $('.fixed-plugin a').click(function(event) {
          // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .active-color span').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-color', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data-color', new_color);
          }
        });

        $('.fixed-plugin .background-color .badge').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('background-color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data-background-color', new_color);
          }
        });

        $('.fixed-plugin .img-holder').click(function() {
          $full_page_background = $('.full-page-background');

          $(this).parent('li').siblings().removeClass('active');
          $(this).parent('li').addClass('active');


          var new_image = $(this).find("img").attr('src');

          if ($sidebar_img_container.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            $sidebar_img_container.fadeOut('fast', function() {
              $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
              $sidebar_img_container.fadeIn('fast');
            });
          }

          if ($full_page_background.length != 0 && $('.switch-sidebar-image input:checked').length != 0) {
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $full_page_background.fadeOut('fast', function() {
              $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
              $full_page_background.fadeIn('fast');
            });
          }

          if ($('.switch-sidebar-image input:checked').length == 0) {
            var new_image = $('.fixed-plugin li.active .img-holder').find("img").attr('src');
            var new_image_full_page = $('.fixed-plugin li.active .img-holder').find('img').data('src');

            $sidebar_img_container.css('background-image', 'url("' + new_image + '")');
            $full_page_background.css('background-image', 'url("' + new_image_full_page + '")');
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.css('background-image', 'url("' + new_image + '")');
          }
        });

        $('.switch-sidebar-image input').change(function() {
          $full_page_background = $('.full-page-background');

          $input = $(this);

          if ($input.is(':checked')) {
            if ($sidebar_img_container.length != 0) {
              $sidebar_img_container.fadeIn('fast');
              $sidebar.attr('data-image', '#');
            }

            if ($full_page_background.length != 0) {
              $full_page_background.fadeIn('fast');
              $full_page.attr('data-image', '#');
            }

            background_image = true;
          } else {
            if ($sidebar_img_container.length != 0) {
              $sidebar.removeAttr('data-image');
              $sidebar_img_container.fadeOut('fast');
            }

            if ($full_page_background.length != 0) {
              $full_page.removeAttr('data-image', '#');
              $full_page_background.fadeOut('fast');
            }

            background_image = false;
          }
        });

        $('.switch-sidebar-mini input').change(function() {
          $body = $('body');

          $input = $(this);

          if (md.misc.sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            md.misc.sidebar_mini_active = false;

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar();

          } else {

            $('.sidebar .sidebar-wrapper, .main-panel').perfectScrollbar('destroy');

            setTimeout(function() {
              $('body').addClass('sidebar-mini');

              md.misc.sidebar_mini_active = true;
            }, 300);
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);

        });
      });
    });
  </script>
  <script>
    $(document).ready(function() {
      md.checkFullPageBackgroundImage();
    });
  </script>
  
   <script type="text/javascript">
       
        function Regreso(accion)
        
    {
               
        var curp=document.getElementById("CONSULTA_CURP").value;
        var nivel=document.getElementById("NIVEL").value;
        var en_periodo=document.getElementById("EN_PERIODO").value;
        var acuerdo=document.getElementById("ACUERDO").checked;
        var intervalo=document.getElementById("INTERVALO").value;           
        var primeraletra=curp.substr(0,1);    
        var pasa= validaLetra(intervalo, primeraletra);
        var nivelcct="";    
           
      /* VALIDACIONES DE ENTRADA A PLATAFORMA DE BECAS */
      
      if((nivel.length==0 && intervalo.length>0) ){
          document.getElementById("EN_PERIODO").value = "SI";
          en_periodo="SI";
      }
      
           
                     
                     
                     if(curp.length==18 && acuerdo==true && en_periodo=="SI"){
                         
                         	
             
                         
                         /* SIN RESTRICCIONES DE CAPTURA DE BECA */
                        
                        if(intervalo.length==0 && nivel.length==0){
                             
                
                        document.altaPetiForm.action = accion;
                        document.altaPetiForm.target = "_self";
                        document.altaPetiForm.submit();
                        
                      
                        }
                        
                        
                        /* CON RESTRICCION DE LETRA*/
                        
                        if(intervalo.length==3 && nivel.length==0){
                            
                            
                            if(pasa=="SI"){
                                
                         document.altaPetiForm.action = accion;
                        document.altaPetiForm.target = "_self";
                        document.altaPetiForm.submit();
                                
                            }
                            
                            else
                            {
                                alert("La letra de tu Apellido no esta habilitada para el registro de la Beca en esta fecha");
                            }
                                
                
                            
                            
                        }
                        
                        
                          /* CON RESTRICCION DE NIVEL*/
                        
                        if(intervalo.length==0 && nivel.length>0){
                            
                            
                           nivelcct=document.getElementById("NIVELCCT").value;
                           
                           if(nivel==nivelcct){
                               
                        document.altaPetiForm.action = accion;
                        document.altaPetiForm.target = "_self";
                        document.altaPetiForm.submit();
                               
                           }
                           
                           else{
                               
                              alert("Nivel educativo no habilitado en esta fecha, favor de verificar el periodo"); 
                           }
                                
                
                            
                            
                        }
                        
                        
                        if(intervalo.length==3 && nivel.length>0){
                            
                            
                           nivelcct=document.getElementById("NIVELCCT").value;
                           
                           if(nivel==nivelcct && pasa=="SI"){
                               
                        document.altaPetiForm.action = accion;
                        document.altaPetiForm.target = "_self";
                        document.altaPetiForm.submit();
                               
                           }
                           
                           else{
                               
                               
                               if(pasa=="NO" && nivel==nivelcct ){
                                   
                                  alert("La letra de tu Apellido no esta habilitada para el registro de la Beca en esta fecha");  
                                   
                               }
                               
                             
                              if(nivel!=nivelcct && pasa=="SI"){
                                   
                                  alert("Nivel educativo no habilitado en esta fecha, favor de verificar el periodo");  
                                   
                               }
                               
                               
                                if(pasa=="NO" && nivel!=nivelcct){
                                   
                                  alert("Nivel Educativo y primera letra de primer apellido no habilitada para el registro de la beca en este periodo");  
                                   
                               }
                             
                             
                             
                             
                             
                             
                             
                             
                           }
                                
                
                            
                            
                        }
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
            
                     }
                     
                     else{
                         
                         
                         if(en_periodo=="NO"){
                             
                      
                            alert("Te encuentras fuera de las fechas estipuladas de registro para la Beca");
            
                      
                             
                             
                             
                            }
                            
                            else{
                                
                                
                                  if(acuerdo==false && curp.length==18){
                                  alert("Debes Aceptar las bases y requisitos de la beca");
                            }
                                if(acuerdo==true && curp.length!=18){
                                  alert("Formato de curp no valido, favor de intentar de nuevo");
                             }
                            }
                             
                             
                             
                         
                     }
                     
                     
                     
                     
                     
                     
                     
                



    }


     function validaLetra(intervalo, primeraletra)
            
             {
                
                        var ExpReg = new RegExp("^[" + intervalo + "]$");


                            if(ExpReg.test(primeraletra)){
                                 return "SI";
                                  }
                            else {
                             return "NO";
                                  }


              }
            
            
            
            
            
            
            
            
            
            
            
            
            function Regreso2(accion) {
               
                
               
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
   

            }
            
              function consultaCctBase(accion) {
               
              
                 var cctConsulta =document.getElementById("myInput").value;
                 
                 
               if(cctConsulta.length==10){
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
                }
                else
                    alert("Debe capturar una CCT valida");

            }
            
            
            
            
            
            
      
       function Accion(accion, valor) {
           
                    document.altaPetiForm.ID_BECA.value = valor;
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();

            }
            
            
  </script>    
  
  
  <script>
      
      
      $('.bootstrap-switch').each(function(){
    $this = $(this);
    data_on_label = $this.data('on-label') || '';
    data_off_label = $this.data('off-label') || '';

    $this.bootstrapSwitch({
        onText: data_on_label,
        offText: data_off_label
    });
});
      
      
      
      
      
      
      
  </script>
  
  
  
  
  
  
</body>

</html>