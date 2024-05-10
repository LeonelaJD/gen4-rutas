<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.jimenez.app.rutas.models.*" %>

<% //recuperamos la lista de choferes que seteamos en el request desde el servlet
    List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
    List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>



    <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>


</head>

<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
            </div>




            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                            role="button" aria-haspopup="true"
                            aria-expanded="false">Choferes<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista
                                    Choferes</a></li>
                            <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta
                                    Chofer</a></li>


                        </ul>


                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                            role="button" aria-haspopup="true"
                            aria-expanded="false">Camiones<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/camiones/listar">Lista
                                    Camiones</a></li>
                            <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta
                                    Camion</a></li>


                        </ul>
                    </li>


                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                            role="button" aria-haspopup="true"
                            aria-expanded="false">Rutas<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta
                                    Ruta</a></li>


                        </ul>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


    <div class="container body-content">
        <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>





        <div class="row">
            <div class="col-md-12">
                <h2>Iniciar Ruta</h2>
            </div>

            <div style="display: block;"><input type="text" name="" id="txtEsOD"></div>

        </div>

        <br>

        <div class="row">


            <div class="col-md-6">
                <div class="form-group">
                    <label for="">Chofer</label>
                    <select name="chofer" id="chofer" class="form-control">
                        <option value="">seleccionar</option>
                        <% for(Chofer c: choferes){%>
                            <option value="<%=c.getId()%>">
                                <%=c.getNombre()%>
                            </option>}
                            <%}%>
                    </select>
                </div>


                <div class="form-group">
                    <div class="row">
                        <div class="col-md-9">
                            <label for="">Origen</label>
                            <input type="text" name="origen" id="origen"
                                class="form-control">
                            <input type="hidden" name="idOrigen" id="idOrigen"
                                class="form-control">
                        </div>
                        <div class="col-md-3">
                            <button id="" class="btn btn-primary btn-xs"
                                style="margin-top: 30px;" onclick="getDireccion(1)">Obtener
                                Datos</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="">Fecha Salida</label>
                    <input type="datetime-local" name="FSalida" id="FSalida" class="form-control">

                </div>

                <div class="form-group">
                    <label for="">Distancia</label>
                    <input type="text" name="distancia" id="distancia" class="form-control">

                </div>

            </div>


            <div class="col-md-6">
                <div class="form-group">
                    <label for="">Camion</label>
                    <select name="camion" id="select-camion" class="form-control">
                        <option value="">seleccionar</option>
                        <% for(Camion c: camiones){%>
                            <option value="<%=c.getId()%>">
                                <%=c.getMatricula()%>
                            </option>}
                            <label id="capacidad-camion" class="hidden"><%=c.getCapacidad()%></label>
                            <%}%>
                    </select>
                </div>


                <div class="form-group">
                    <div class="row">
                        <div class="col-md-9">
                            <label for="">Destino</label>
                            <input type="text" name="destino" id="destino"
                                class="form-control">
                            <input type="hidden" name="idDestino" id="idDestino"
                                class="form-control">
                        </div>
                        <div class="col-md-3">
                            <button id="" class="btn btn-primary btn-xs"
                                style="margin-top: 30px;" onclick="getDireccion(2)">Obtener
                                Datos</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="">Fecha estimada de Llegada</label>
                    <input type="datetime-local" name="FELegada" id="FELegada" class="form-control">

                </div>

                <div class="form-group">
                    <label for="">Capacidad Camion</label>
                    <input type="text" name="capCamion" id="capCamion" class="form-control">

                </div>

                <div class="form-group">
                    <button class="btn btn-success">Iniciar Ruta</button>
                </div>

            </div>

        </div>

        <div class="row">
           <div class="col-md-12">
               <h4>Cargamento</h4>
           </div>
        </div>

        <div class="row" id="FormaCarga">
           <div class="col-md-4">
               <div class="form-group">
                   <label for="descripcion">Descripción</label>
                   <input type="text" id="descripcion" name="descripcion" class="form-control"/>
               </div>
           </div>
           <div class="col-md-4">
               <div class="form-group">
                   <label for="peso">Peso</label>
                   <input type="number" id="peso" name="peso" class="form-control"/>
               </div>
           </div>
           <div class="col-md-4">
               <div class="form-group">
                   <button id="btnAddCarga" class="btn btn-success" style="margin-top:25px;" onclick="AddCarga()">
                       Agregar Carga
                   </button>
               </div>
           </div>
        </div>
        <div class="row">
           <div class="col-md-10 col-md-offset-1">
               <table id="idCargamentos" class="table table-stripped table-responsive table-bordered">
                   <thead>
                       <tr>
                           <th>Descripcion</th>
                           <th>Peso</th>
                       </tr>
                   </thead>
                   <tbody>
                   </tbody>
               </table>
           </div>
        </div>




    </div>

    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Guardar Direccion</h4>
                        </div>
                    </div>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="">Calle</label>
                                <input type="text" name="Calle" id="Calle" class="form-control">

                            </div>

                             <div class="form-group">
                                <label for="">numero</label>
                                <input type="text" name="Numero" id="Numero" class="form-control">

                            </div>

                            <div class="form-group">
                                <label for="">Colonia</label>
                                <input type="text" name="Colonia" id="Colonia" class="form-control">

                            </div>

                            <div class="form-group">
                                <label for="">Ciudad</label>
                                <input type="text" name="Ciudad" id="Ciudad" class="form-control">

                            </div>

                            <div class="form-group">
                                <label for="">Estado</label>
                                <input type="text" name="Estado" id="Estado" class="form-control">

                            </div>

                            <div class="form-group">
                                <label for="">CP</label>
                                <input type="text" name="CP" id="CP" class="form-control">

                            </div>

                            <div id="contenedor-cargando" class="hidden">
                                <div class="contenedor-loader">
                                  <div class="rueda"></div>
                                </div>
                                <div class="cargando">Cargando...</div>
                                <div class="progress">
                                  <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                                    <span class="sr-only">100% Complete</span>
                                  </div>
                                </div>

                              </div>

                        </div>

                    </div>


                </div>


                <div class="modal-footer">
                    <div class = "row">
                        <div class="col-md-10 col-md-offset-1">
                            <div class ="col-md-4">
                                <button class="btn btn-success" onclick="btnGuardarDir()">Guardar</button></div>

                            <div class="col-md-4 col-md-offset-2">
                                <button class="btn -default" data-dismiss="modal">Cerrar</button>
                            </div>

                            </div>
                        </div>

                    </div>

                </div>

            </div>

        </div>


    </div>



    <script>
        function LimpiarDatos(){
            $("#Calle").val("");
            $("#Numero").val("");
            $("#Colonia").val("");
            $("#Ciudad").val("");
            $("#Estado").val("");
            $("#CP").val("");
        }

        function getDireccion(fuente) {
            LimpiarDatos();
            $('#myModal').modal('show');
            var direccion = "";
            if (fuente == 1){ //la direccion es un origen
                direccion = $("#origen").val(); //boulevard san felipe 223
            }
                else // la direccion es un destino
            {
                direccion = $("#destino").val();
            }

            $("#txtEsOD").val(fuente);
            if (direccion != "") {
                //llamamos/consumir/hacer una peticion a la api de google maps
                //para obtener los datos completos de la direccion
                var geocoder = new google.maps.Geocoder();
                geocoder.geocode({ 'address': direccion }, function (results, status) {
                    console.log(results);
                    console.log(status);
                    if (status == google.maps.GeocoderStatus.OK) {
                        //traemos los datos completos de la direccion
                        var numresults = results[0].address_components.length; // 7
                        //recorremos cada una de las propiedades de addres_components
                        for (var indice = 0; indice < numresults; indice++) {
                            var numresultstypes =
                                results[0].address_components[indice].types.length; // 1
                            //recorremos los tipos de cada componente de la direccion
                            for (var propiedad = 0; propiedad < numresultstypes;
                                propiedad++) {
                                //declaramos una variable que nos permita saber el tipo
                                // de propiedad que estamos recorriendo
                                var Tipo = results[0].address_components[indice].types
                                [propiedad];
                                //Descripcion va a contener el valor de la propiedad
                                var Descripcion =
                                    results[0].address_components[indice].long_name;
                                //validamos cada propiedad para escribirla en el textbox
                                //que corresponda
                                switch (Tipo) {
                                    case "route": //calle
                                        $("#Calle").val(Descripcion);
                                        break;
                                    case "street_number": //numero
                                        $("#Numero").val(Descripcion);
                                        break;
                                    case "sublocality_level_1": //colonia
                                        $("#Colonia").val(Descripcion);
                                        break;
                                    case "locality": //ciudad
                                        $("#Ciudad").val(Descripcion);
                                        break;
                                    case "administrative_area_level_1": //estado
                                        $("#Estado").val(Descripcion);
                                        break;
                                    case "postal_code": //c.p.
                                        $("#CP").val(Descripcion);
                                        break
                                }
                            }
                        }

                        //asignamos a txtorigen o destino la direccion formateada
                        if (results[0].address_components.length > 0) {
                            if (fuente == 1) // es origen
                            {
                                $("#origen").val(results[0].formatted_address);
                            }
                            else {
                                $("#destino").val(results[0].formatted_address);
                            }
                        }
                    }

                    else{
                        swal("Error de google", "Google no obtuvo datos", "Warning");
                    }

                })
            }
            else
            {
                //avisamos al usuario que tendra que capturar
                //todos los datos
                swal({
                    title: "Estas seguro?",
                    text: "No podemos obtener datos sino proporciona una direccion",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-warning",
                    confirmButtonText: "Si, quiero capturar la información",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: true,
                    closeOnCancel: true,
                }, function(isConfirm){
                    if(!isConfirm)
                    {
                        $('#myModal').modal('hide');

                    }
                });
            }
        }

        function btnGuardarDir(){
           //recuperar los valores de las cajas de texto
           var calle = $("#Calle").val(); //Boulevard San Felipe
           var numero = $("#Numero").val(); // 224
           var colonia = $("#Colonia").val(); // Valle del Ángel
           var ciudad = $("#Ciudad").val(); // Heroica Puebla de Zaragoza
           var estado = $("#Estado").val(); // Puebla
           var cp = $("#CP").val(); // 72040

           $('#contenedor-cargando').removeClass("hidden");

           //hacer la peticion a mi api, mandandole los valores de las cajas
           $.ajax({
               type: 'POST',
               url: "http://localhost:8080/Gen4-ApiDirecciones/api/direccion",
               data: { "calle": calle, "numero": numero, "colonia": colonia, "ciudad": ciudad, "estado": estado, "cp": cp },
               //esperar la respuesta del servidor
               success: function(resp) {
                   //mostrar la respuesta
                   console.log(resp);

                   //habilitar el boton
                   $('#myModal').modal('hide');
                   if( $("#txtEsOD").val() == 1 ){ //es origen
                       $("#idOrigen").val(resp.message);
                   }
                   else{ //es destino
                       $("#idDestino").val(resp.message);
                   }
               }
           });
        }

        $(function() {
            $("#select-camion").change(function() {
                console.log($("#capacidad-camion").text());
            });
        });

    </script>
</body>
</html>