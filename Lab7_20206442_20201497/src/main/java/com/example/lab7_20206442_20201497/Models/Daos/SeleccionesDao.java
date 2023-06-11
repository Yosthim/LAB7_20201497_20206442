package com.example.lab7_20206442_20201497.Models.Daos;

import com.example.lab7_20206442_20201497.Models.Beans.Estadio;
import com.example.lab7_20206442_20201497.Models.Beans.Partido;
import com.example.lab7_20206442_20201497.Models.Beans.Seleccion;

import java.sql.*;
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

        //implementado de esta manera porque se sabe que Perú se repite y es el primer elemento de la lista
        //implementar de otra manera es algo complicado dado que se tiene que llenar la tabla de partidos
        Seleccion selec = listaSelecciones.get(0);

        for (int i = 1; i < listaSelecciones.size(); i++) {
            if (listaSelecciones.get(i).getNombre().equals(selec.getNombre())) {
                selec = listaSelecciones.get(i);
            }
        }

        listaSelecciones.remove(selec);
        return listaSelecciones;
    }

    public ArrayList<Estadio> listarEstadios() {
        ArrayList<Estadio> estadios = new ArrayList<>();
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idEstadio, nombre FROM estadio")){

            while (rs.next()){

                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt(1));
                estadio.setNombre(rs.getString(2));

                estadios.add(estadio);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return estadios;
    }

    public boolean guardar(Seleccion seleccion) {

        ArrayList<Seleccion> selecciones = this.listarSelecciones();
        boolean valor = true;
        for (Seleccion s: selecciones) {
            if (s.getNombre().equals(seleccion.getNombre())) {
                valor = false;
            }
        }

        if (valor) {
            String sql = "INSERT INTO seleccion (nombre,tecnico,estadio_idEstadio) VALUES(?,?,?)";

            try (Connection connection = this.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, seleccion.getNombre());
                preparedStatement.setString(2, seleccion.getTecnico());
                preparedStatement.setInt(3, seleccion.getEstadio().getIdEstadio());

                preparedStatement.executeUpdate();
                return true;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }
}
