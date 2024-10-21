/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lam
 */
public class ActorDAO {
    private Connection connection;

    public ActorDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add an actor to a movie
    public void addActorToMovie(String actorName, int movieId) throws SQLException {
        // First, check if actor exists
        String selectSql = "SELECT aid FROM Actor WHERE name = ?";
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        selectStmt.setString(1, actorName);
        ResultSet rs = selectStmt.executeQuery();

        int actorId;
        if (rs.next()) {
            actorId = rs.getInt("aid");
        } else {
            // If actor doesn't exist, insert a new actor
            String insertSql = "INSERT INTO Actor (name) VALUES (?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, actorName);
            insertStmt.executeUpdate();

            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                actorId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating actor failed, no rows affected.");
            }
        }

        // Associate actor with movie
        String insertMovieActorSql = "INSERT INTO Movie_Actor (mid, aid) VALUES (?, ?)";
        PreparedStatement movieActorStmt = connection.prepareStatement(insertMovieActorSql);
        movieActorStmt.setInt(1, movieId);
        movieActorStmt.setInt(2, actorId);
        movieActorStmt.executeUpdate();
    }
}
