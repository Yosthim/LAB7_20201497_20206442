package com.example.lab7_20206442_20201497.Models.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    public Connection getConnection() throws SQLException{

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/lab7";

        return DriverManager.getConnection(url, "root","root");
    }
}
