package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DeleteFilmServlet
 */
@WebServlet("/DeleteFilmServlet")
public class DeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		FilmInfo fInfo = new FilmInfo();
		
		String deleteFilmId;
		int filmId;
		try {
			deleteFilmId = request.getParameter("id");
			filmId = Integer.parseInt(deleteFilmId);
			fInfo.deleteFilm(filmId);
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception exception){
			System.out.println(exception);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}		
			
	}

}
