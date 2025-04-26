package com.poli.reciclApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://145.223.79.134/reciclApp";
    private static final String USER = "reciclApp";
    private static final String PASSWORD = "zaVRUkqbd8LYi9cN9txV";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC no encontrado.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}