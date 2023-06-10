package com.example.lab7_20206442_20201497.Controllers;

import com.example.lab7_20206442_20201497.Models.Beans.Jugador;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;
import com.example.lab7_20206442_20201497.Models.Daos.JugadoresDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "JugadorServlet", value = {"", "/JugadorServlet"})

public class JugadorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JugadoresDao jugadoresDao = new JugadoresDao();

        String action =request.getParameter("action")==null ?"lista" :request.getParameter("action");

        switch (action){

            case "lista":
                request.setAttribute("lista",jugadoresDao.listaJugadores());
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case "crear":
                request.setAttribute("selecciones",jugadoresDao.obtenerListaSeleciones());
                request.getRequestDispatcher("Jugadores/AgregarJugadores.jsp").forward(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action =request.getParameter("parametro")==null ?"crear" :request.getParameter("p");
        JugadoresDao jugadoresDao = new JugadoresDao();

        switch (action){

            case "crear":

                Jugador jugador = parseJugador(request);
                jugadoresDao.guardar(jugador);
                response.sendRedirect(request.getContextPath() + "/JugadorServlet");
                break;
        }

    }
    public Jugador parseJugador(HttpServletRequest request){

        Jugador jugador = new Jugador();
        Seleccion seleccio = new Seleccion();

        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String posicion = request.getParameter("posicion");
        String club = request.getParameter("club");
        String seleccion = request.getParameter("seleccion");

        try{
            int edad_int = Integer.parseInt((edad));
            int seleccion_int = Integer.parseInt(seleccion);

            jugador.setNombre(nombre);
            jugador.setEdad(edad_int);
            jugador.setPosicion(posicion);
            jugador.setClub(club);

            seleccio.setIdSeleccion(seleccion_int);
            jugador.setSeleccion(seleccio);

            return jugador;
        }catch (NumberFormatException e){

        }

        return jugador;
    }
}
