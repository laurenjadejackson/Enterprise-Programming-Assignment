package main;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateFilmServlet
 */
@WebServlet("/UpdateFilmServlet")
public class UpdateFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmInfo fInfo = new FilmInfo();
		Gson gson = new Gson();
		
		String year, id;
		year = request.getParameter("year");
		id = request.getParameter("id");
		
		if (year != null && id != null) {
			int filmYear = Integer.parseInt(year);
			int filmId = Integer.parseInt(id);
			
			Film film = new Film();
			film.setId(filmId);
			film.setYear(filmYear);
			film.setTitle(request.getParameter("title"));
			film.setDirector(request.getParameter("director"));
			film.setReview(request.getParameter("review"));
			film.setStars(request.getParameter("stars"));
			
			fInfo.insertFilm(film);
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		
	}
}
