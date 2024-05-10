import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/C1")
public class C1 extends HttpServlet {

    //Hicimos estas funciones, pero las primeras dos solo se imprimen en consola, En cambio cuando usamos el
    //get..post..etc.. ya cambia la forma de hacer.

    /*void f1(){
        System.out.println("hola mundo");
    }*/

    /*void f2(){
        System.out.println("estoy programando una app. web en java");
    }*/

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("hola mundo");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("estoy programando una app.web en java");
    }

}
