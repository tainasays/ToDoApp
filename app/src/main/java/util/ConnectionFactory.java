/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thayn
 */
public class ConnectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";                    // local do driver
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp";         // endereço do servidor, caminho da conexão
    public static final String USER = "root";                                       //em geral, o padrão do SQL o usuário e a senha são esses.
    public static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);                                          // carrega o driver pra nossa app
            return DriverManager.getConnection(URL, USER, PASS);                    // faz uma conexão levando em consideração esses parametros
        } catch (Exception ex) {                                                    // ação reparadora do erro
            throw new RuntimeException("Erro na conexão com o banco de dados ", ex);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão com o banco de dados ", ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão com o banco de dados ", ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                statement.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar conexão com o banco de dados ", ex);
        }
    }
}
