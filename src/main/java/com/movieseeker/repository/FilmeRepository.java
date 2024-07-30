package com.movieseeker.repository;

import com.movieseeker.model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeRepository {

    private Connection getConnection() throws SQLException {
        //URL de conexão com banco
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/movieSeeker", "gmaszera", "130620");
    }

    public void addFilme(Filme filme) throws SQLException {
        String query = "INSERT INTO movie (name, genre, year, director, actor) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, filme.getNome());
            statement.setString(2, filme.getGenero());
            statement.setInt(3, filme.getAno());
            statement.setString(4, filme.getDiretor());
            statement.setString(5, filme.getAtor());
            statement.executeUpdate();
        }

    }

    public List<Filme> getAllFilmes() throws SQLException {
        List<Filme> filmes = new ArrayList<>();
        String query = "SELECT * FROM movie";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setNome(resultSet.getString("name"));
                filme.setGenero(resultSet.getString("genre"));
                filme.setAno(resultSet.getInt("year"));
                filme.setDiretor(resultSet.getString("director"));
                filme.setAtor(resultSet.getString("actor"));
                filmes.add(filme);
            }
        }
        return filmes;
    }
}
