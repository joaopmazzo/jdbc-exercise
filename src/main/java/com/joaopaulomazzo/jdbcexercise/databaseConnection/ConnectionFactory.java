package com.joaopaulomazzo.jdbcexercise.databaseConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {

        Connection connection = null;

        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            String connectionUrl = "jdbc:" +
                    driver + "://" +
                    dataBaseAddress + "/" +
                    dataBaseName;

            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e) {
                System.out.println("Falha ao conectar com o banco de dados");
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("Falha ao tentar carregar arquivo de propriedades");
            e.printStackTrace();
        }

        return connection;
    }
}
