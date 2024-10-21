/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author lam
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieDAO {
    private Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a movie
    public void addMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO Movie (title, releasedDate, forAdult, cid) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, movie.getTitle());
        stmt.setString(2, movie.getReleasedDate());
        stmt.setBoolean(3, movie.isForAdult());
        stmt.setInt(4, movie.getCategory().getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating movie failed, no rows affected.");
        }

        // Get the generated movie ID
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            movie.setId(generatedKeys.getInt(1));
        }
    }

    // Method to get all movies with categories and actors
    public List<Movie> getAllMoviesWithActorsAndCategories() throws SQLException {
        String sql = "SELECT m.mid, m.title, m.releasedDate, m.forAdult, c.name AS categoryName, a.name AS actorName " +
                     "FROM Movie m " +
                     "INNER JOIN Category c ON m.cid = c.cid " +
                     "LEFT JOIN Movie_Actor ma ON ma.mid = m.mid " +
                     "LEFT JOIN Actor a ON a.aid = ma.aid";

        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Movie> movies = new ArrayList<>();
        // Map the result set to Movie objects
        while (rs.next()) {
            // Process each movie and actor here
            // ... (omitted for brevity)
        }
        return movies;
    }
}

