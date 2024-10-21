package ws2;
import dal.ActorDAO;
import dal.CategoryDAO;
import dal.DatabaseConnection;
import dal.MovieDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Category;
import model.Movie;


public class MovieServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private CategoryDAO categoryDAO;
    private ActorDAO actorDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO objects with the database connection
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            movieDAO = new MovieDAO(connection);
            categoryDAO = new CategoryDAO(connection);
            actorDAO = new ActorDAO(connection);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException(e);
        }
    }

    // Handles the GET request to display the movie creation form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch all categories to populate the dropdown in the form
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);

            // Forward the request to the JSP form for movie creation
            RequestDispatcher dispatcher = request.getRequestDispatcher("../create.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Handles the POST request to process the movie creation
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the movie details from the form input
        String title = request.getParameter("title");
        String releaseDate = request.getParameter("releasedDate");
        boolean forAdult = request.getParameter("forAdult") != null; // Checkbox for 'For Adult'
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String[] actorNames = request.getParameter("actors").split(","); // Comma-separated actor names

        try {
            // Fetch the selected category
            Category category = new Category(categoryId, null);  // Assuming you are only using the ID

            // Create a new Movie object
            Movie movie = new Movie(title, releaseDate, forAdult, category);

            // Save the movie to the database
            movieDAO.addMovie(movie);

            // Associate each actor with the movie
            for (String actorName : actorNames) {
                actorDAO.addActorToMovie(actorName.trim(), movie.getId());
            }

            // Redirect to the movie list page after successful creation
            response.sendRedirect("list");  // Assuming /movie/list is the URL for the movie list
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
