package org.example.platzi.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //Se sube a GitHub por fines educativos, en producción no se cargan archivos que contengan credenciales
    private static String url = "jdbc:mysql://localhost:3306/bdjava";
    private static String user = "root";
    private static String pass = "";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);

            pool.setInitialSize(3);//Establece el tamaño inicial del grupo de conexiones.
            pool.setMinIdle(3);//Establece el número mínimo de conexiones inactivas en el grupo.
            pool.setMaxIdle(10);//Establece el número máximo de conexiones que pueden permanecer inactivas en el grupo.
            pool.setMaxTotal(10);//Establece el número total máximo de conexiones inactivas y de préstamo que pueden estar activas al mismo tiempo.
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
