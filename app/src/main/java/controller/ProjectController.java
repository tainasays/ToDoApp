/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author thayn
 */
public class ProjectController {

    public void save(Project project) {           // serão salvados os dados dentro dessa task. Criar uma string c o script sql que a gnt qr q seja executado

        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));

            statement.execute();

            // Executa a SQL para inserção dos dados
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto ", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }

    public void update(Project project) {         // vamos ter que receber a tarefa com os dados no bando de dados

        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            // Preparando a query
            statement = connection.prepareStatement(sql);

            // Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());

            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto ", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    public List<Project> getAll() {       // ele vai buscar todas as tarefas com base no projeto

        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;                     // classe que guarda os valores que a gnt teve de resposta

        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            //Preparação do statement
            statement = connection.prepareStatement(sql);

            //Setando os valores que correspondem ao filtro de busca
            //Valor retornado pela execução da query 
            resultSet = statement.executeQuery();

            // Enquanto houverem valores a serem percorridos no resultSet
            while (resultSet.next()) {

                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                projects.add(project);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return projects;
    }

    public void removeById(int id) {    // passamos o id da task que será deletada e executamos o comando de delete no banco de dados

        {

            String sql = "DELETE FROM projects WHERE id = ?";

            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.execute();
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao deletar o projeto", ex);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao fechar a conexão", ex);
                }
            }

        }
    }
}
