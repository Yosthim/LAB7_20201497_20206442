package com.example.lab7_20206442_20201497.Models.Daos;

import com.example.lab7_20206442_20201497.Models.Beans.Estadio;
import com.example.lab7_20206442_20201497.Models.Beans.Partido;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SeleccionesDao extends DaoBase{

    public ArrayList<Seleccion> listarSelecciones() {
        ArrayList<Seleccion> listaSelecciones = new ArrayList<>();

        String sql = "SELECT s.idSeleccion , s.nombre, s.tecnico, e.nombre, (l.nombre) as `selLocal`, (v.nombre) as `selVis` \n" +
                "FROM seleccion s inner join estadio e on s.estadio_idEstadio = e.idEstadio\n" +
                "inner join partido p on s.idSeleccion in (p.seleccionLocal, p.seleccionVisitante)\n" +
                "inner join (SELECT p.seleccionLocal, s.nombre FROM partido p inner join seleccion s on s.idSeleccion = p.seleccionLocal)\n" +
                "l on l.seleccionlocal = p.seleccionLocal\n" +
                "inner join (SELECT p.seleccionVisitante, s.nombre FROM partido p inner join seleccion s on s.idSeleccion = p.seleccionVisitante)\n" +
                "v on v.seleccionVisitante = p.seleccionVisitante\n" +
                "order by p.fecha";

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()) {
                Seleccion seleccion = new Seleccion();

                seleccion.setIdSeleccion(resultSet.getInt(1));
                seleccion.setNombre(resultSet.getString(2));
                seleccion.setTecnico(resultSet.getString(3));

                Estadio estadio = new Estadio();
                estadio.setNombre(resultSet.getString(4));
                seleccion.setEstadio(estadio);

                Partido primerPartido = new Partido();
                primerPartido.setLocal(resultSet.getString(5));
                primerPartido.setVisita(resultSet.getString(6));
                seleccion.setPrimerPartido(primerPartido);

                listaSelecciones.add(seleccion);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return listaSelecciones;
    }
}
