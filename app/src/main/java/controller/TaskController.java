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
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author thayn
 */
public class TaskController {

    public void save(Task task) {           // serão salvados os dados dentro dessa task. Criar uma string c o script sql que a gnt qr q seja executado

        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar tarefa "
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);

        }
    }

    public void update(Task task) {         // vamos ter que receber a tarefa com os dados no bando de dados

        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, completed = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            // Preparando a query
            statement = connection.prepareStatement(sql);
            
            // Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa ", ex);
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
    

    public void removeById(int taskId) {    //throws SQLException {    // passamos o id da task que será deletada e executamos o comando de delete no banco de dados

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;     // 2 variáveis que precisamos ter: uma conexão e um statement.

        PreparedStatement statement = null;

        try {
            
           // Criando a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando o statement
            statement = connection.prepareStatement(sql);    //obj que ajuda a preparar o comando para ser executado pela conexão. Questão de segurança?
            
            // Setando os valores
            statement.setInt(1, taskId);        //a 1ª '?', qro substituí-lo pelo valor do ID da tarefa que recebi por parâmetro.
            
            // Executando a query
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa " + ex.getMessage(), ex);
        } finally {                             // um bloco que SEMPRE vai ser executado
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll() {       // ele vai buscar todas as tarefas com base no projeto

        String sql = "SELECT * FROM tasks";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;                     // classe que guarda os valores que a gnt teve de resposta

        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<>();

        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            //Preparação do statement
            statement = connection.prepareStatement(sql);
            
            //Setando os valores que correspondem ao filtro de busca
            //statement.setInt(1, idProject);
            
            //Valor retornado pela execução da query 
            resultSet = statement.executeQuery();

            // Enquanto houverem valores a serem percorridos no resultSet
            while (resultSet.next()) {

                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("Deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                tasks.add(task);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
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
        return tasks;
    }
    
    public List<Task> getByIdProject(int id) {
        String sql = "SELECT * FROM tasks where idProject = ?";

        List<Task> tasks = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            //Enquanto existir dados no banco de dados, faï¿½a
            while (resultSet.next()) {

                Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setCreatedAt(resultSet.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
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
        return tasks;
    }
    }

