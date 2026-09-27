package com.tiendaaseo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/tienda_aseo?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "admin123";

   
    public static Connection obtenerConexion() throws SQLException {
        Connection conexionBD = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        return conexionBD;
    }
}
