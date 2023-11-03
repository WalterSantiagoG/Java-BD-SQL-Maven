package org.example.platzi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //Se sube a GitHub por fines educativos, en producción no se cargan archivos que contengan credenciales
    private static String url = "jdbc:mysql://localhost:3306/bdjava";
    private static String user = "root";
    private static String pass = "";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        if (myConn == null){
            myConn = DriverManager.getConnection(url,user,pass);
        }
        return myConn;
    }
}
