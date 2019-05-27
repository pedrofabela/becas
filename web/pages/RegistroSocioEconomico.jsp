<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="../../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../../assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>
    Registro a Beca 
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
            <a class="nav-link" href="Javascript:Regreso('Inicio')">
              <i class="material-icons">dashboard</i>
              <p> Dashboard </p>
            </a>
          </li>        
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
           
                  <div class="card ">
                      <div class="card-header card-header-rose card-header-icon">
                          <div class="card-icon">
                              <i class="material-icons">account_box</i>
                          </div>
                          <h4 class="card-title">Datos de Aspirante</h4>
                      </div>
                      <div class="card-body">  
                          <div class="dropdown-divider"></div>
                          <div class="col-sm-12">
                              <div class="row">
                                  <div class="form-group col-md-4">
                                      <label for="exampleEmail" class="bmd-label-floating">CURP</label>
                                      <s:textfield  cssClass="form-control " name="objAspirante.CONSULTA_CURP" id="objRenapo.CONSULTA_CURP" readonly="true"/>
                                      <s:fielderror fieldName="CURP" id="CURP" ></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">
                                          <label for="exampleEmail" class="bmd-label-floating">Nombre</label>

                                      <s:textfield  cssClass="form-control " name="objAspirante.NOMBRE_RENAPO" id="objAspirante.NOMBRE_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="NOMA" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Paterno</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.APATERNO_RENAPO" id="objAspirante.APATERNO_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="APA" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Materno</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.AMATERNO_RENAPO" id="objAspirante.AMATERNO_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="AMA" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                      <s:textfield cssClass="form-control" name="objDatosA.ID_ASPIRANTE" id="objDatosA.ID_ASPIRANTE" required="true"/>
                                  </div>      



                              </div>
                          </div>                         
                      </div> 
                                      <br></br>                
                                      <div class="dropdown-divider"></div>
                                      <div class="card-header card-header-rose card-header-icon">
                                          <div class="card-icon">
                                              <i class="material-icons">attach_money</i>
                                          </div>
                                          <h4 class="card-title">Datos SocioEconomicos</h4>
                                      </div>
                                      <div class="card-body">  
                                          <div class="col-sm-12">                              
                                              <div class="row">

                                                  <div class="form-group col-md-12">
                                                      <label for="examplePass" class="bmd-label-floating">Cuenta con el apoyo del programa PROSPERA: </label>
                                                      <s:select  data-style="select-with-transition"   name="objDatosE.RESPUESTA1" id="objDatosP.RESPUESTA1" list="ListaRespuestas"  listKey="ID_RESPUESTA"  listValue="RESPUESTA" headerKey="" headerValue="--SELLECCIONE--"  cssClass="selectpicker "  />
                                                      <s:iterator value="ListaRespuestas" id="ListaRespuestas" status="stat">                        
                                                          <s:hidden  name = "ListaRespuestas[%{#stat.index}].ID_RESPUESTA" id="ID_RESPUESTA"></s:hidden>
                                                          <s:hidden  name = "ListaRespuestas[%{#stat.index}].RESPUESTA" id="RESPUESTA"></s:hidden>
                                                      </s:iterator>
                                                      <s:fielderror fieldName="RES1" cssClass="alert alert-danger"></s:fielderror>
                                                      </div>

                                                      <div class="form-group col-md-12">
                                                          <label for="examplePass" class="bmd-label-floating">Cuenta con algún apoyo de tipo económico o en especie para su educación al momento de realizar esta solicitud: </label>
                                                      <s:select  data-style="select-with-transition"   name="objDatosE.RESPUESTA2" id="objDatosP.RESPUESTA2" list="ListaRespuestas"  listKey="ID_RESPUESTA"  listValue="RESPUESTA" headerKey="" headerValue="--SELLECCIONE--"  cssClass="selectpicker "  />
                                                      <s:fielderror fieldName="RES2" cssClass="alert alert-danger"></s:fielderror>
                                                      </div>
                                                      <div class="form-group col-md-4">
                                                          <label for="examplePass" class="bmd-label-floating">Monto total de percepciones netas mensuales familiares : $</label>
                                                      <s:textfield cssClass="form-control" name="objDatosE.MONTO" id="objDatosE.MONTO" />
                                                      <s:fielderror fieldName="MONTO" cssClass="alert alert-danger"></s:fielderror>
                                                      </div>

                                                      <div class="form-group col-md-12">

                                                          <div class="row">
                                                              <label class="col-sm-11 col-form-label label-checkbox">MARQUE LA CASILLA SI USTED CUENTA CON ALGUN COMPROBANTE DE INGRESOS.</label>
                                                              <div class="col-sm-1 col-sm-offset-1 checkbox-radios">
                                                                  <div class="form-check">
                                                                      <label class="form-check-label">
                                                                      <s:checkbox  type="checkbox" name="valingreso" id="valingreso" onchange="Javascript:FormIngreso('muestraFormIngreso')" /> 

                                                                  </label>
                                                              </div>                                  
                                                          </div> 
                                                      </div>

                                                  </div>  
                                              </div>               
                                          </div>                         
                                      </div>    
                                                          <s:if test="banFormIngresos">
                                                              <s:hidden name="banFormIngresos" value="%{banFormIngresos}"></s:hidden>

                                                                  <div class="card-header card-header-rose card-header-icon">
                                                                      <div class="card-icon">
                                                                          <i class="material-icons">archive</i>
                                                                      </div>
                                                                      <h4 class="card-title">CARGA DE COMPROBANTE DE INGRESOS</h4>
                                                                  </div>
                                                                  <div class="card-body">  
                                                                      <div class="col-sm-12">                              
                                                                          <div class="row">


                                                                              <div class="col-md-4 col-sm-4">

                                                                                  <div class="fileinput fileinput-new text-center" data-provides="fileinput">
                                                                                      <div class="fileinput-new thumbnail">
                                                                                          <div class="card-icon col-md-4">
                                                                                              <i class="material-icons">cloud_upload</i>
                                                                                          </div>
                                                                                      </div>
                                                                                      <div class="fileinput-preview fileinput-exists thumbnail"></div>
                                                                                      <div>
                                                                                          <span class="btn btn-rose btn-round btn-file">
                                                                                              <span class="fileinput-new">Seleccionar Archivo</span>
                                                                                              <span class="fileinput-exists">Cambiar</span>
                                                                                              <input type="file" name="archi" id="archi" />
                                                                                          </span>
                                                                                          <a href="#pablo" class="btn btn-danger btn-round fileinput-exists" data-dismiss="fileinput"><i class="fa fa-times"></i> Eliminar</a>
                                                                                           <s:fielderror fieldName="archi" cssClass="help-block" cssStyle="color:red"/> 
                                                                                      </div>
                                                                                  </div>
                                                                              </div>


                                                                      </s:if> 
                                                                  </div>               
                                                              </div>                         
                                                          </div>    

                                              
                      <div class="card-footer ">
                          <a href="Javascript:Consulta('GuardaSocioEconomico')" class="btn btn-round btn-primary">Guardar Datos Socioeconomicos</a>
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
         
           <s:textfield  name="objdatos.ID_BECA_AUX" id="ID" ></s:textfield>  
   <s:textfield  name="objAspirante.ID_CICLO" id="objAspirante.ID_CICLO" ></s:textfield>  
   <s:textfield  name="objDatosP.ID_MUNICIPIO_TU" id="objDatosP.ID_MUNICIPIO_TU" ></s:textfield>  
   <s:textfield  name="objDatosE.VALIDACHECK" id="vaingreso" ></s:textfield>  

          
      </footer>
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
       
        function Regreso(accion) {
               
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
                

            }
        
         function Consulta(accion) {
               
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
                

            }
         function ConsultaCP(accion) {
             
                        valor = document.getElementById("CP").value;                   
                        variable=valor.length;  
                        
                    if(variable==5){
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();  
                    }else{
                        alert("El codigo postal debe tener 5 números");
                        document.getElementById("cadena").value="El codigo postal debe tener 5 números";
                        
                    }
                    
                

            }    
            
             function FormIngreso(accion) {
             
                    valor = document.getElementById("valingreso").checked;                                         
                    document.getElementById("vaingreso").value=valor;
                        
                    
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();  
                   
                    
                

            }    

      
       function Accion(accion, valor) {
               
                    document.altaPetiForm.ID_BECA.value = valor;
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
                

            }
  </script>    
       
     </s:form>
</body>

</html>