package com.example.lab7_20206442_20201497.Controllers;

import com.example.lab7_20206442_20201497.Models.Beans.Jugador;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;
import com.example.lab7_20206442_20201497.Models.Daos.JugadoresDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

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

                Boolean valida = validar(request, jugadoresDao.listaJugadores());
                if (valida == true ){
                    Jugador jugador = parseJugador(request );
                    jugadoresDao.guardar(jugador);
                    response.sendRedirect("JugadorServlet");
                }else {
                    response.sendRedirect("JugadorServlet?action=crear");
                }
                break;
        }

    }
    public Jugador parseJugador(HttpServletRequest request ){

        Jugador jugador = new Jugador();
        Seleccion seleccio = new Seleccion();

        jugador.setNombre(request.getParameter("nombre").trim());
        jugador.setEdad(Integer.parseInt(request.getParameter("edad")));
        jugador.setPosicion(request.getParameter("posicion").trim());
        jugador.setClub(request.getParameter("club").trim());

        seleccio.setIdSeleccion(Integer.parseInt((request.getParameter("idSeleccion"))));

        jugador.setSeleccion(seleccio);


        return jugador;
    }

    public Boolean validar(HttpServletRequest request, ArrayList<Jugador> lista){
        int r=0;
        for(int i =0 ; i< lista.size() ; i++){

            if(request.getParameter("nombre").trim().equals(lista.get(i).getNombre())){
                r++;
            }
        }

        if (r>0){
            return false;
        }

        return true;
    }
}
