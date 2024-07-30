package com.movieseeker;

import com.movieseeker.model.Filme;
import com.movieseeker.repository.FilmeRepository;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FilmeRepository filmeRepository = new FilmeRepository();

        // Adiciona um novo filme
        Filme novoFilme = new Filme();
        novoFilme.setNome("Monster Hunter");
        novoFilme.setGenero("Sci-Fi");
        novoFilme.setAno(2020);
        novoFilme.setDiretor("Paul W. S. Anderson");
        novoFilme.setAtor("Milla Jovovich");

        try {
            filmeRepository.addFilme(novoFilme);
            System.out.println("Filme adicionado com sucesso!");

            // Recupera e exibe todos os filmes
            List<Filme> filmes = filmeRepository.getAllFilmes();
            System.out.println("Filmes no banco de dados:");
            for (Filme filme : filmes) {
                System.out.println(filme.getNome() + " (" + filme.getAno() + ") - " + filme.getGenero() + " - Diretor: " + filme.getDiretor());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}

