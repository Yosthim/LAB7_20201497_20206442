package com.example.lab7_20206442_20201497.Controllers;

import com.example.lab7_20206442_20201497.Models.Beans.Estadio;
import com.example.lab7_20206442_20201497.Models.Beans.Jugador;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;
import com.example.lab7_20206442_20201497.Models.Daos.SeleccionesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SeleccionServlet", value = "/listaSelecciones")
public class SeleccionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SeleccionesDao seleccionesDao = new SeleccionesDao();
        String action = req.getParameter("act") == null ? "listar" : req.getParameter("act");

        switch (action) {
            case "listar":
                req.setAttribute("listaSelecciones", seleccionesDao.listarSelecciones());
                req.getRequestDispatcher("Selecciones/listaSelecciones.jsp").forward(req,resp);
                break;
            case "reg":
                req.setAttribute("estadios", seleccionesDao.listarEstadios());
                req.getRequestDispatcher("Selecciones/registro.jsp").forward(req, resp);
                break;
            case "bor":
                req.setAttribute("lista", seleccionesDao.listarSelecciones());
                req.getRequestDispatcher("Selecciones/borrar.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SeleccionesDao seleccionesDao = new SeleccionesDao();

        Seleccion seleccion = parseSeleccion(req);
        if (seleccion != null) {
            boolean valor = seleccionesDao.guardar(seleccion);
            if (valor) {
                resp.sendRedirect(req.getContextPath() + "/listaSelecciones");
            } else {
                resp.sendRedirect(req.getContextPath()+"/listaSelecciones?act=reg");
            }
        } else {
            resp.sendRedirect(req.getContextPath()+"/listaSelecciones?act=reg");
        }
    }

    public Seleccion parseSeleccion(HttpServletRequest request){

        Seleccion seleccion = new Seleccion();
        Estadio estadioOb = new Estadio();

        String nombre = request.getParameter("nombre");
        String tecnico = request.getParameter("tecnico");
        String estadio = request.getParameter("id_estadio");

        try{
            int idEstadio = Integer.parseInt(estadio);

            seleccion.setNombre(nombre);
            seleccion.setTecnico(tecnico);

            estadioOb.setIdEstadio(idEstadio);
            seleccion.setEstadio(estadioOb);

            return seleccion;
        }catch (NumberFormatException e){
            return null;
        }
    }
}
