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

@WebServlet("/camiones/alta")
    public class AltaCamionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCamion.jsp")
                .forward(req, resp);
    }

    @Override
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
            errores.put("tipoCamion", "el tipo de camion es requerido!");
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
        if (errores.isEmpty()) {
            Camion camion = new Camion();
            camion.setId(0L);
            camion.setMatricula(matricula);
            camion.setTipoCamion(Tipos.valueOf(tipoCamion));
            camion.setMarca(Marcas.valueOf(marca));
            camion.setModelo(Integer.valueOf(modelo));
            camion.setCapacidad(Integer.valueOf(capacidad));
            camion.setKilometraje(Double.valueOf(kilometraje));
            camion.setDisponibilidad(habilitar);
            service.guardar(camion);
            resp.sendRedirect(req.getContextPath()+ "/camiones/listar");
        }
        else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCamion.jsp")
                    .forward(req, resp);
        }
    }
}