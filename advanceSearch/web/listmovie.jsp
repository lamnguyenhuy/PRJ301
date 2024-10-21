<%-- movie_list.jsp --%>
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Released Date</th>
            <th>For Adult</th>
            <th>Category Name</th>
            <th>Actors</th>
        </tr>
    </thead>
    <tbody>
        <% 
        List<Movie> movies = (List<Movie>) request.getAttribute("movies");
        for (Movie movie : movies) {
            out.print("<tr>");
            out.print("<td>" + movie.getTitle() + "</td>");
            out.print("<td>" + movie.getReleasedDate() + "</td>");
            out.print("<td>" + (movie.isForAdult() ? "Yes" : "No") + "</td>");
            out.print("<td>" + movie.getCategory().getName() + "</td>");
            out.print("<td>");
            for (Actor actor : movie.getActors()) {
                out.print(actor.getName() + " ");
            }
            out.print("</td>");
            out.print("</tr>");
        }
        %>
    </tbody>
</table>
