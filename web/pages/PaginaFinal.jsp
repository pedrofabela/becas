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
            <a class="nav-link" href="Javascript:Regreso2('Inicio')">
              <i class="material-icons">dashboard</i>
              <p> Inicio </p>
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
              <div class="progress col-md-12" style="height:2rem">
                      <div class="progress-bar  bg-success " style="width:25%">REGISTRO DE DATOS GENERALES DEL ASPIRANTE</div>
                      <div class="progress-bar  bg-info " style="width:25%">REGISTRO DE DATOS DEL PADRE O TUTOR</div>
                      <div class="progress-bar  bg-danger " style="width:25%">REGISTRO DE DATOS SOCIOECONOMICOS </div>
                      <div class="progress-bar  bg-warning text-dark" style="width:25%">IMPRESIÓN DE CEDÚLA DE INSCRIPCIÓN (4 DE 4)</div>

             </div>

           
                  <div class="card ">
                      <div class="card-header card-header-warning card-header-icon">
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
                                      <s:textfield  cssClass="form-control " name="objAspirante.CONSULTA_CURP" id="objAspirante.CONSULTA_CURP" readonly="true"/>
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
                                          <label for="examplePass" class="bmd-label-floating">Fecha de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.FEC_NAC_RENAPO" id="objAspirante.FEC_NAC_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="FECNAN" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Nacionalidad</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.NACIONALIDAD_RENAPO" id="objAspirante.NACIONALIDAD_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="NAC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Entidad de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.ENTIDAD_NACIMINETO_RENAPO" id="objAspirante.ENTIDAD_NACIMINETO_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="ENTIDADNAC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>


                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Genero</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.GENERO_RENAPO" id="objAspirante.GENERO_RENAPO" readonly="true"/>
                                      <s:fielderror fieldName="GENERO" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                      <s:select  data-style="select-with-transition" title="Estado Civil"  name="objAspirante.ID_ESTADO_CIVIL" id="objAspirante.ID_ESTADO_CIVIL" list="ListaEstadosCivil"  listKey="ID_ESTADO_CIVIL"  listValue="ESTADO_CIVIL"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objAspirante.ID_ESTADO_CIVIL" id="%{objAspirante.ID_ESTADO_CIVIL}"></s:hidden>
                                      <s:fielderror fieldName="IDESTADO" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Domicilio</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.DOMICILIO" id="objAspirante.DOMICILIO" readonly="true"/>
                                      <s:fielderror fieldName="DOMICILIO" cssClass="alert alert-danger" ></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Entre Calle </label>
                                      <s:textfield cssClass="form-control" name="objAspirante.CALLE1" id="objAspirante.CALLE1" readonly="true"/>
                                      <s:fielderror fieldName="CALLE1" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating"> Y Calle</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.CALLE2" id="objAspirante.CALLE2" readonly="true"/>
                                      <s:fielderror fieldName="CALLE2" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating"> Otra referencia de domicilio</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.REFERENCIA" id="objAspirante.REFERENCIA" readonly="true"/>
                                      <s:fielderror fieldName="REFERENCIA" cssClass="alert alert-danger"></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">

                                          <label for="examplePass" class="bmd-label-floating">Codigo Postal</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.CP" id="CP" onchange="Javascript:ConsultaCP('ConsultaCP')" maxLength="5" readonly="true"/>
                                      <s:fielderror fieldName="NoCP" cssClass="alert alert-danger"></s:fielderror>
                                      <s:fielderror fieldName="CP" cssClass="alert alert-danger"></s:fielderror>


                                      </div>

                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Colonia: </label>
                                      <s:select  data-style="select-with-transition"   name="objAspirante.COLONIA" id="objAspirante.COLONIA" list="ListaColonia"  listKey="COLONIA"  listValue="COLONIA" headerValue="COLONIA"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objAspirante.COLONIA" id="%{objAspirante.COLONIA}"></s:hidden>
                                      <s:fielderror fieldName="COLONIA" cssClass="alert alert-danger"></s:fielderror>
                                      </div>  
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Municipio</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.MUNICIPIO" id="objAspirante.MUNICIPIO" readonly="true"/>
                                      <s:fielderror fieldName="MUNICIPIO" cssClass="alert alert-danger"></s:fielderror>
                                      </div>    

                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Teléfono Fijo Ej.(722)1234567</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.TELEFONO" id="objAspirante.TELEFONO" maxLength="12" readonly="true"/>
                                      <s:fielderror fieldName="TEL" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Teléfono Celular Ej.(044)1234567890</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.CELULAR" id="objAspirante.CELULAR" maxLength="15" readonly="true"/>
                                      <s:fielderror fieldName="CEL" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Email</label>
                                      <s:textfield cssClass="form-control" name="objAspirante.EMAIL" id="objAspirante.EMAIL" required="true" readonly="true"/>
                                      <s:fielderror fieldName="EMAIL" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                      <s:textfield cssClass="form-control" name="objAspirante.ID_ASPIRANTE" id="objAspirante.ID_ASPIRANTE"   required="true" style='visibility:hidden'/>
                                  </div>




                              </div>
                          </div>                         
                      </div> 

                      <div class="dropdown-divider"></div>
                      <div class="card-header card-header-warning card-header-icon">
                          <div class="card-icon">
                              <i class="material-icons">local_library</i>
                          </div>
                          <h4 class="card-title">Datos Academicos</h4>
                      </div>
                      <div class="card-body">  
                          <div class="col-sm-12">
                              <div class="row">
                                  <div class="form-group col-md-12">                                    
                                      <label for="examplePass" class="bmd-label-floating">CCT </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.CCT" id="objDatosA.CCT" readonly="true"/>
                                      <s:fielderror fieldName="CCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">NOMBRE DE LA INSTITUCIÓN </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.NOMCCT" id="objDatosA.NOMCCT" readonly="true"/>
                                      <s:fielderror fieldName="NOMCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">DOMICILIO DE LA INSTITUCIÓN </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.DOMCCT" id="objDatosA.DOMCCT" readonly="true" />
                                      <s:fielderror fieldName="DOMCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">NIVEL EDUCATIVO </label>
                                      <s:textfield cssClass="form-control" name="objDatosA.NIVELCCT" id="objDatosA.NIVELCCT" readonly="true"/>
                                      <s:fielderror fieldName="NIVELCCT" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-12">                                    
                                          <label for="examplePass" class="bmd-label-floating">TURNO</label>
                                      <s:textfield cssClass="form-control" name="objDatosA.TURNO" id="objDatosA.TURNO" readonly="true"/>
                                      <s:fielderror fieldName="TURNO" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-6">                                    
                                          <label for="examplePass" class="bmd-label-floating">GRADO</label>
                                      <s:select  data-style="select-with-transition"   name="objDatosA.ID_GRADO" id="objDatosA.ID_GRADO" list="ListaGrados"  listKey="ID_GRADO"  listValue="GRADO" headerKey="" headerValue="--SELECCIONAR--"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosA.ID_GRADO" id="%{objDatosA.ID_GRADO}"></s:hidden>
                                      <s:fielderror fieldName="GRADO" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div> 
                                      <div class="form-group col-md-6">                                    
                                          <label for="examplePass" class="bmd-label-floating">PROMEDIO</label>
                                      <s:select  data-style="select-with-transition"   name="objDatosA.PROMEDIO" id="objDatosA.PROMEDIO" list="ListaPromedios"  listKey="ID_PROMEDIO"  listValue="PROMEDIO" headerKey="" headerValue="--SELECCIONAR--" cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosA.PROMEDIO" id="%{objDatosA.PROMEDIO}"></s:hidden>                                   
                                      <s:fielderror fieldName="PROMEDIO" cssClass="alert alert-danger"></s:fielderror>                                        
                                      </div>     


                                  </div>
                              </div>                         
                          </div> 
                          <div class="dropdown-divider"></div>
                          <div class="card-header card-header-warning card-header-icon">
                              <div class="card-icon">
                                  <i class="material-icons">local_library</i>
                              </div>
                              <h4 class="card-title">Datos del Padre o Tutor</h4>
                          </div>
                          <div class="card-body">  
                              <div class="col-sm-12">                              
                                  <div class="row">
                                      <div class="form-group col-md-4">
                                          <label for="exampleEmail" class="bmd-label-floating">CURP</label>
                                      <s:textfield  cssClass="form-control " name="objDatosP.CONSULTA_CURP_TU" id="objDatosP.CONSULTA_CURP_TU" readonly="true"/>
                                      <s:fielderror fieldName="CURP" id="CURP" ></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">
                                          <label for="exampleEmail" class="bmd-label-floating">Nombre</label>

                                      <s:textfield  cssClass="form-control " name="objDatosP.NOMBRE_RENAPO_TU" id="objDatosP.NOMBRE_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="NOMT" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Paterno</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.APATERNO_RENAPO_TU" id="objDatosP.APATERNO_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="APT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Materno</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.AMATERNO_RENAPO_TU" id="objDatosP.AMATERNO_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="AMT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Fecha de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.FEC_NAC_RENAPO_TU" id="objDatosP.FEC_NAC_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="FECNANT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Nacionalidad</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.NACIONALIDAD_RENAPO_TU" id="objDatosP.NACIONALIDAD_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="NACT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Entidad de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.ENTIDAD_NACIMINETO_RENAPO_TU" id="objDatosP.ENTIDAD_NACIMINETO_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="ENTIDADNACT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>


                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Genero</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.GENERO_RENAPO_TU" id="objDatosP.GENERO_RENAPO_TU" readonly="true"/>
                                      <s:fielderror fieldName="GENEROT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Parentesco: </label>
                                      <s:select  data-style="select-with-transition"   name="objDatosP.PARENTESCO" id="objDatosP.PARENTESCO" list="ListaParentesco"  listKey="ID_PARENTESCO"  listValue="NOM_PARENTESCO" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosP.PARENTESCO" id="%{objDatosP.PARENTESCO}"></s:hidden>
                                      <s:fielderror fieldName="PARENTESCOT" cssClass="alert alert-danger"></s:fielderror>
                                      </div> 
                                      <div class="form-group col-md-4">
                                      <s:select  data-style="select-with-transition" title="Estado Civil"  name="objDatosP.ID_ESTADO_CIVIL_TU" id="objDatosP.ID_ESTADO_CIVIL_TU" list="ListaEstadosCivil"  listKey="ID_ESTADO_CIVIL"  listValue="ESTADO_CIVIL"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosP.ID_ESTADO_CIVIL_TU" id="%{objDatosP.ID_ESTADO_CIVIL_TU}"></s:hidden>
                                      <s:fielderror fieldName="IDESTADOP" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Domicilio</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.DOMICILIO_TU" id="objDatosP.DOMICILIO_TU" readonly="true" />
                                      <s:fielderror fieldName="DOMICILIOT" cssClass="alert alert-danger" ></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Entre Calle </label>
                                      <s:textfield cssClass="form-control" name="objDatosP.CALLE1_TU" id="objDatosP.CALLE1_TU" readonly="true"/>
                                      <s:fielderror fieldName="CALLE1T" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating"> Y Calle</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.CALLE2_TU" id="objDatosP.CALLE2_TU" readonly="true"/>
                                      <s:fielderror fieldName="CALLE2T" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating"> Otra referencia de domicilio</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.REFERENCIA_TU" id="objDatosP.REFERENCIA_TU" readonly="true"/>
                                      <s:fielderror fieldName="REFERENCIAT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">

                                          <label for="examplePass" class="bmd-label-floating">Codigo Postal</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.CP_TU" id="CP" onchange="Javascript:ConsultaCP('ConsultaCPP')" maxLength="5" readonly="true"/>
                                      <s:fielderror fieldName="NoCPP" cssClass="alert alert-danger"></s:fielderror>
                                      <s:fielderror fieldName="CPT" cssClass="alert alert-danger"></s:fielderror>

                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Colonia: </label>
                                      <s:select  data-style="select-with-transition"   name="objDatosP.COLONIA_TU" id="objDatosP.COLONIA_TU" list="ListaColonia"  listKey="COLONIA"  listValue="COLONIA"  headerValue="COLONIA"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosP.COLONIA_TU" id="%{objDatosP.COLONIA_TU}"></s:hidden>
                                      <s:fielderror fieldName="COLONIAT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>  
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Municipio</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.MUNICIPIO_TU" id="objDatosP.MUNICIPIO_TU" readonly="true"/>
                                      <s:fielderror fieldName="MUNICIPIOT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>    


                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Teléfono Fijo</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.TELEFONO_TU" id="objDatosP.TELEFONO_TU" readonly="true" />
                                      <s:fielderror fieldName="TELT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Teléfono Celular</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.CELULAR_TU" id="objDatosP.CELULAR_TU" readonly="true"/>
                                      <s:fielderror fieldName="CELT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Email</label>
                                      <s:textfield cssClass="form-control" name="objDatosP.EMAIL_TU" id="objDatosP.EMAIL_TU" readonly="true"/>
                                      <s:fielderror fieldName="EMAILT" cssClass="alert alert-danger"></s:fielderror>
                                      </div>


                                  </div>    
                              </div>                         
                          </div>
                          <div class="dropdown-divider"></div>
                          <div class="card-header card-header-warning card-header-icon">
                              <div class="card-icon">
                                  <i class="material-icons">supervisor_account</i>
                              </div>
                              <h4 class="card-title">Datos del Cobeneficiario</h4>
                          </div>
                          <div class="card-body">  
                              <div class="col-sm-12">
                                  <div class="row">                                      
                                      <div class="form-group col-md-4">
                                          <label for="exampleEmail" class="bmd-label-floating">CURP</label>

                                      <s:textfield  cssClass="form-control " name="objDatosC.CURP_CO" id="objDatosC.CURP_CO" readonly="true"/>
                                      <s:fielderror fieldName="CURPC"  cssClass="alert alert-danger"></s:fielderror>

                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="exampleEmail" class="bmd-label-floating">Nombre</label>

                                      <s:textfield  cssClass="form-control " name="objDatosC.NOMBRE_RENAPO_CO" id="objDatosC.NOMBRE_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="NOMC" id="NOMA" cssClass="alert alert-danger"></s:fielderror>

                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Paterno</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.APATERNO_RENAPO_CO" id="objDatosP.APATERNO_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="APAC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Apellido Materno</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.AMATERNO_RENAPO_CO" id="objDatosC.AMATERNO_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="AMAC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>

                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Fecha de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.FEC_NAC_RENAPO_CO" id="objDatosC.FEC_NAC_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="FECNANC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Nacionalidad</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.NACIONALIDAD_RENAPO_CO" id="objDatosC.NACIONALIDAD_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="NACC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Entidad de Nacimiento</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.ENTIDAD_NACIMINETO_RENAPO_CO" id="objDatosC.ENTIDAD_NACIMINETO_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="ENTIDADNACC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>


                                      <div class="form-group col-md-4">
                                          <label for="examplePass" class="bmd-label-floating">Genero</label>
                                      <s:textfield cssClass="form-control" name="objDatosC.GENERO_RENAPO_CO" id="objDatosC.GENERO_RENAPO_CO" readonly="true"/>
                                      <s:fielderror fieldName="GENEROC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                      <div class="form-group col-md-4" >
                                          <label for="examplePass" class="bmd-label-floating">Parentesco: </label>
                          
                                      <s:select  data-style="select-with-transition"   name="objDatosC.PARENTESCO_CO" id="objDatosC.PARENTESCO_CO" list="ListaParentesco"  listKey="ID_PARENTESCO"  listValue="NOM_PARENTESCO" headerKey="" headerValue="--SELECCIONE--"  cssClass="selectpicker " disabled="true" />
                                      <s:hidden name="objDatosC.PARENTESCO_CO" id="%{objDatosC.PARENTESCO_CO}"></s:hidden>
                                      
                                      <s:fielderror fieldName="PARENTESCOC" cssClass="alert alert-danger"></s:fielderror>
                                      </div> 
                                      <div class="form-group col-md-4" >
                                      <s:select  data-style="select-with-transition" title="Estado Civil"   name="objDatosC.ID_ESTADO_CIVIL_CO" id="objDatosC.ID_ESTADO_CIVIL_CO" list="ListaEstadosCivil"  listKey="ID_ESTADO_CIVIL"  listValue="ESTADO_CIVIL"  cssClass="selectpicker text-center" disabled="true" />
                                       <s:hidden name="objDatosC.ID_ESTADO_CIVIL_CO" id="%{objDatosC.ID_ESTADO_CIVIL_CO}"></s:hidden>
                                      <s:fielderror fieldName="IDESTADOC" cssClass="alert alert-danger"></s:fielderror>
                                      </div>
                                  </div>
                              </div>
                          </div> 
                          <div class="dropdown-divider"></div>
                          <div class="card-header card-header-warning card-header-icon">
                              <div class="card-icon">
                                  <i class="material-icons">attach_money</i>
                              </div>
                              <h4 class="card-title">Datos SocioEconomicos</h4>
                          </div>
                          <div class="card-body">  
                              <div class="col-sm-12">                              
                                  <div class="row">

                                  <s:set var="NoPreg" value="1"/>
                                  <s:set var="cont" value="0" />
                                  <s:iterator value="ListaPreguntas" id="ListaPreguntas" status="stat">
                                      <div class="form-group col-md-12">
                                          <label for="examplePass" class="bmd-label-floating"> <s:property value="#NoPreg" />) &nbsp;&nbsp; <s:property value="PREGUNTA" />  </label>

                                          <s:if test="TIPO_PREGUNTA==2">
                                              <s:textfield cssClass="form-control" name="ListaContestados[%{#cont}].RESPUESTA"  maxlength="1000" readonly="true"/>

                                          </s:if>
                                          <s:elseif test="TIPO_PREGUNTA==1">
                                              <s:select  data-style="select-with-transition"   name="ListaContestados[%{#cont}].RESPUESTA"  list="ListaRespuestas"  listKey="RESPUESTA"  listValue="RESPUESTA" headerKey="" headerValue="--SELLECCIONE--"  cssClass="selectpicker " disabled="true" />
                                          </s:elseif> 

                                      </div>

                                      <s:hidden  name = "ListaPreguntas[%{#stat.index}].PREGUNTA" id="NOMPREG"></s:hidden>
                                      <s:hidden  name = "ListaPreguntas[%{#stat.index}].ID_PREGUNTA" id="ID_PREGUNTA"></s:hidden>
                                      <s:hidden  name = "ListaPreguntas[%{#stat.index}].TIPO_PREGUNTA" id="TIPO_PREGUNTA"></s:hidden>



                                      <s:hidden  name = "ListaContestados[%{#cont}].ID_PREGUNTA" value="%{ID_PREGUNTA}"></s:hidden>



                                      <s:set var="NoPreg" value="%{#NoPreg+1}"/>
                                      <s:set var="cont" value="%{#cont+1}"/>

                                  </s:iterator>

                                  <s:iterator value="ListaRespuestas" id="ListaRespuestas" status="stat1">                        
                                      <s:hidden  name = "ListaRespuestas[%{#stat1.index}].ID_RESPUESTA" id="ID_RESPUESTA"></s:hidden>
                                      <s:hidden  name = "ListaRespuestas[%{#stat1.index}].RESPUESTA" id="RESPUESTA"></s:hidden>
                                  </s:iterator>   




                              </div>               
                              </div>                         
                          </div>   
                          &nbsp;            
                          <div class="card-header card-header-warning card-header-icon">
                              <div class="card-icon">
                                  <i class="material-icons">archive</i>
                              </div>
                              <h4 class="card-title">COMPROBANTE DE INGRESOS</h4>
                          </div>
                          <div class="card-body">  
                          <s:if  test="BanMuestraArchivo">
                              <s:hidden name="BanMuestraArchivo>" value="%{BanMuestraArchivo}"></s:hidden>
                                  <div class="col-md-12 col-sm-12">
                                      <div class="alert alert-info alert-with-icon" data-notify="container">
                                          <i class="material-icons" data-notify="icon">notifications</i>
                                          <span data-notify="icon" class="now-ui-icons ui-1_bell-53"></span>
                                          <span data-notify="message">
                                              ARCHIVO REGISTRADO
                                              <a  href="https://becas.edugem.gob.mx/documentos/becas/<s:property  value="objDatosE.ARCHIVO_INGRESO"/>" target="black"/><s:property  value="objDatosE.ARCHIVO_INGRESO"/>
                                      </span>
                                  </div>                                                                                                           
                              </div>  
                          </s:if>                                 
                      </div>                                 
                                      
                      <div class="card-footer ">
                          <div class="col-lg-12">
                              <div class="row">
                                  <div class="col-lg-6  text-center">                          
                                      <a href="Javascript:Consulta('GenerarFolio')" onclick="this.onclick = function () {return false}" class="btn btn-block  btn-success"><p class=" col-md-auto ">De click aqui Si la informacion que registro es correcta</p></a>
                                  </div>
                                  <div class="col-lg-6  text-center">   
                                      <a href="Javascript:Consulta('ModificaDatos')" onclick="this.onclick = function () {return false}" class="btn btn-block btn-warning"><p class=" col-md-auto " >De click aqui si desea modificar alguna dato registrado</p></a>
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
          
          <s:textfield  name="objdatos.ID_BECA_AUX" id="ID" style='visibility:hidden' ></s:textfield>  
           <s:textfield  name="objRenapo.CONSULTA_CURP" id="objRenapo.CONSULTA_CURP" style='visibility:hidden' ></s:textfield>  
          <s:textfield  name="objAspirante.ID_MUNICIPIO" id="objAspirante.ID_MUNICIPIO" style='visibility:hidden' ></s:textfield>  
          <s:textfield  name="objAspirante.ID_CICLO" id="objAspirante.ID_CICLO" style='visibility:hidden' ></s:textfield>  
          <s:textfield  name="objRenapo.ID_CICLO" id="objRenapo.ID_CICLO" style='visibility:hidden' ></s:textfield> 
           <s:textfield  name="objFolio.FOLIO" id="objRenapo.FOLIO" style='visibility:hidden' ></s:textfield>  
          
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
       
        function Regreso2(accion) {
               
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
      
       function Accion(accion, valor) {
               
                    document.altaPetiForm.ID_BECA.value = valor;
                    document.altaPetiForm.action = accion;
                    document.altaPetiForm.target = "_self";
                    document.altaPetiForm.submit();
                

            }
            
            
            
            
             window.onload = function () {/*hace que se cargue la función lo que predetermina que div estará oculto hasta llamar a la función nuevamente*/
                                    var pos = window.name || 0;
                                    window.scrollTo(0, pos);

                                    window.location.hash = "no-back-button";
                                    window.location.hash = "Again-No-back-button" //chrome
                                    window.onhashchange = function () {
                                        window.location.hash = "no-back-button";
                                    }

                                }

                                window.onunload = function () {
                                    window.name = self.pageYOffset
                                            || (document.documentElement.scrollTop + document.body.scrollTop);


                                }

            
  </script>    
   
     
     </s:form>
</body>

</html>
