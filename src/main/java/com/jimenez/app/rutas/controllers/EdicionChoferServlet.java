package com.jimenez.app.rutas.controllers;

import com.jimenez.app.rutas.models.Chofer;
import com.jimenez.app.rutas.services.ChoferesService;
import com.jimenez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/choferes/editar")
public class EdicionChoferServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));//333
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Chofer chofer = new Chofer();
        if (id > 0) {
            Optional<Chofer> o = service.getById(id);
            if (o.isPresent()) {
                chofer = o.get();
                req.setAttribute("chofer", chofer);
                getServletContext().getRequestDispatcher("/edicionChofer.jsp")
                        .forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe el chofer en la base de datos!");
            }
        } else {
            //resp.sendRedirect(req.getContextPath() + "/choferes/lsta");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error, el id es null, se debe enviar como parametro en la url!");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);

        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String licencia = req.getParameter("licencia");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento");

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeException e) {
            fecha = null;
        }

        String checkbox[];
        checkbox = req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if (checkbox != null) {
            habilitar = true;
        } else {
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "el nombre es requerido");
        }
        if (apPaterno == null || apPaterno.isBlank()) {
            errores.put("apPaterno", "el apellido paterno es requerido!");
        }
        if (apMaterno == null || apMaterno.isBlank()) {
            errores.put("apMaterno", "el apellido materno es requerido!");
        }
        if (licencia == null || licencia.isBlank()) {
            errores.put("licencia", "la licencia es requerida!");
        }
        if (telefono == null || telefono.isBlank()) {
            errores.put("telefono", "el telefono es requerido!");
        }
        if (fechaNacimiento == null || fechaNacimiento.isBlank()) {
            errores.put("fechaNacimiento", "la fecha de nacimiento es requerida!");
        }

        long id;
        id = Long.parseLong(req.getParameter("id")); //323
        Chofer chofer = new Chofer();
        chofer.setId(id);
        chofer.setNombre(nombre);
        chofer.setApPaterno(apPaterno);
        chofer.setApMaterno(apMaterno);
        chofer.setLicencia(licencia);
        chofer.setTelefono(telefono);
        chofer.setFechaNacimiento(fecha);
        chofer.setDisponibilidad(habilitar);

        if (errores.isEmpty()) {

            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath() + "/choferes/listar");
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/edicionChofer.jsp")
                    .forward(req, resp);
        }
    }

}
