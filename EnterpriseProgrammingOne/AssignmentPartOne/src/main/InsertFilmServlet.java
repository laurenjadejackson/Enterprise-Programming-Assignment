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
 * Servlet implementation class InsertFilmServlet
 */
@WebServlet("/InsertFilmServlet")
public class InsertFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmInfo fInfo = new FilmInfo();
		Gson gson = new Gson();
		
		String year;
		year = request.getParameter("year");
		
		if (year != null) {
			int filmYear = Integer.parseInt(year);
			
			Film film = new Film();
			film.setId(fInfo.getNextId());
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
