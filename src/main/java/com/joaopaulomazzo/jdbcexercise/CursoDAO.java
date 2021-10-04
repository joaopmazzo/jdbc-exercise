package com.joaopaulomazzo.jdbcexercise;

import com.joaopaulomazzo.jdbcexercise.databaseConnection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    //    consulta os cursos inseridos
    public List<Curso> list() {
//    cria uma list com os cursos
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {
            //Preparar a consulta
            String sql = "SELECT * FROM curso";

            //Prepara o statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Executa o statement e armazena o retorno
            ResultSet rs = stmt.executeQuery();

            //cria um objeto curso e guarda o mesmo na List criada
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                float duracaoHoras = rs.getFloat("duracao_horas");

                cursos.add(new Curso(
                        id,
                        nome,
                        duracaoHoras
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de cursos FALHOU!");
            e.printStackTrace();
        }

        return cursos;
    }

    //    consulta com filtro
    public Curso getById(int id) {
        Curso curso = new Curso();

        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM curso WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracaoHoras(rs.getFloat("duracao_horas"));
            }

        } catch (SQLException e) {
            System.out.println("Listagem de cursos FALHOU!");
            e.printStackTrace();
        }

        return curso;
    }

    //    insere um novo dado
    public void create(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO curso(nome, duracao_horas) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, curso.getDuracaoHoras());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA! Foi adicionada " + rowsAffected + " linha(s).");
        } catch (SQLException e) {
            System.out.println("Inserção FALHOU!");
            e.printStackTrace();
        }
    }

    //    remove um dado
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM curso WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Remoção BEM SUCEDIDA! Foi removida " + rowsAffected + " linha(s).");
        } catch (SQLException e) {
            System.out.println("Remoção FALHOU!");
            e.printStackTrace();
        }
    }

    //    altera um dado
    public void update(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "UPDATE curso SET nome=?, duracao_horas=? WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setFloat(2, curso.getDuracaoHoras());
            stmt.setInt(3, curso.getId());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização de dados BEM SUCEDIDA! Foi atualizada " + rowsAffected + " linha(s).");
        } catch (SQLException e) {
            System.out.println("Atualização dos dados FALHOU!");
            e.printStackTrace();
        }
    }
}
