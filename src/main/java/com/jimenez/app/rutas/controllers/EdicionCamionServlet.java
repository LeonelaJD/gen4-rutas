package com.jimenez.app.rutas.controllers;

import com.jimenez.app.rutas.models.Camion;
import com.jimenez.app.rutas.models.enums.Marcas;
import com.jimenez.app.rutas.models.enums.Tipos;
import com.jimenez.app.rutas.services.CamionesService;
import com.jimenez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/camiones/editar")
public class EdicionCamionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> service = new CamionesService(conn);

        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));//333
        } catch (NumberFormatException e){
            id = 0L;
        }
        Camion camion = new Camion();
        if (id > 0){
            Optional<Camion> o = service.getById(id);
            if (o.isPresent()){
                camion = o.get();
                req.setAttribute("camion", camion);
                getServletContext().getRequestDispatcher("/edicionCamion.jsp")
                        .forward(req, resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el camion en la base de datos!");
            }
        }
        else {
            //resp.sendRedirect(req.getContextPath() + "/camiones/lista");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error, el id es null, se debe enviar como parametro en la url!");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> service = new CamionesService(conn);

        String matricula = req.getParameter("matricula");
        String tipoCamion = req.getParameter("tipoCamion");
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        String capacidad = req.getParameter("capacidad");
        String kilometraje = req.getParameter("kilometraje");

        /*LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeException e) {
            fecha = null;
        }*/

        String checkbox[];
        checkbox = req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if (checkbox != null) {
            habilitar = true;
        } else {
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if (matricula == null || matricula.isBlank()) {
            errores.put("matricula", "la matricula es requerida");
        }
        if (tipoCamion == null || tipoCamion.isBlank()) {
            errores.put("tipoCamion", "el tipoCamion es requerido!");
        }
        if (marca == null || marca.isBlank()) {
            errores.put("marca", "la marca es requerida!");
        }
        if (modelo == null || modelo.isBlank()) {
            errores.put("modelo", "el modelo es requerido!");
        }
        if (capacidad == null || capacidad.isBlank()) {
            errores.put("capacidad", "la capacidad es requerida!");
        }
        if (kilometraje == null || kilometraje.isBlank()) {
            errores.put("kilometraje", "el kilometraje es requerido!");
        }

        long id;
        id = Long.parseLong(req.getParameter("id")); //323
        Camion camion = new Camion();
        camion.setId(id);
        camion.setMatricula(matricula);
        camion.setTipoCamion(Tipos.valueOf(tipoCamion));
        camion.setMarca(Marcas.valueOf(marca));
        camion.setModelo(Integer.valueOf(modelo));
        camion.setCapacidad(Integer.valueOf(capacidad));
        camion.setKilometraje(Double.valueOf(kilometraje));
        camion.setDisponibilidad(habilitar);

        if (errores.isEmpty()) {

            service.guardar(camion);
            resp.sendRedirect(req.getContextPath()+ "/camiones/listar");
        }
        else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/edicionCamion.jsp")
                    .forward(req, resp);
        }
    }
}
