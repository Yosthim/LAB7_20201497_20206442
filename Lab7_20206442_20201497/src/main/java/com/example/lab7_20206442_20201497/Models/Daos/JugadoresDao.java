package com.example.lab7_20206442_20201497.Models.Daos;

import com.example.lab7_20206442_20201497.Models.Beans.Jugador;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class JugadoresDao extends DaoBase{


    public ArrayList<Jugador> listaJugadores(){

        ArrayList<Jugador> lista = new ArrayList<>();

        String sql = "SELECT * FROM jugador j\n" +
                "left join seleccion s on j.sn_idSeleccion=s.idSeleccion;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Jugador jugador = new Jugador();
                jugador.setIdJugador(resultSet.getInt(1));
                jugador.setNombre(resultSet.getString(2));
                jugador.setEdad(resultSet.getInt(3));
                jugador.setPosicion(resultSet.getString(4));
                jugador.setClub(resultSet.getString(5));

                Seleccion seleccion = new Seleccion();
                seleccion.setIdSeleccion(resultSet.getInt("s.idSeleccion"));
                seleccion.setNombre(resultSet.getString("s.nombre"));

                jugador.setSeleccion(seleccion);

                lista.add(jugador);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Seleccion> obtenerListaSeleciones(){

        ArrayList<Seleccion> selecciones = new ArrayList<>();

        try(Connection connection = this.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM seleccion;")){

            while (rs.next()){

                Seleccion seleccion = new Seleccion();
                seleccion.setIdSeleccion(rs.getInt(1));
                seleccion.setNombre(rs.getString(2));

                selecciones.add(seleccion);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return selecciones;

    }


    public void guardar(Jugador jugador){

        String sql = "INSERT INTO jugador (nombre,edad,posicion,club,sn_idSeleccion) VALUES(?,?,?,?,?)";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,jugador.getNombre());
            preparedStatement.setInt(2,jugador.getEdad());
            preparedStatement.setString(3,jugador.getPosicion());
            preparedStatement.setString(4,jugador.getClub());
            preparedStatement.setInt(5,jugador.getSeleccion().getIdSeleccion());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}
