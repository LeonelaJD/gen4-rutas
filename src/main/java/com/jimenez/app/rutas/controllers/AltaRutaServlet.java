package com.jimenez.app.rutas.controllers;

import com.jimenez.app.rutas.models.Cargamento;
import com.jimenez.app.rutas.services.IRutasService;
import com.jimenez.app.rutas.services.IService;
import com.jimenez.app.rutas.services.RutasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/rutas/alta")
public class AltaRutaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IRutasService service = new RutasService(conn);
        req.setAttribute("camiones", service.listarCamiones());
        req.setAttribute("choferes", service.listarChoferes());
        /**/
        HttpSession session = req.getSession();
        List<Cargamento> cargamentos = (List<Cargamento>) session
                .getAttribute("cargamentos");
        req.setAttribute("cargamentos", cargamentos);
        /**/
        getServletContext().getRequestDispatcher("/altaRuta.jsp")
                .forward(req, resp);


    }
}
