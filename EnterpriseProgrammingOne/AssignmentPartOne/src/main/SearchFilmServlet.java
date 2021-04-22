package main;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchFilmServlet
 */
@WebServlet("/SearchFilmServlet")
public class SearchFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmInfo fInfo = new FilmInfo();
		
		Gson gson = new Gson();

		String format, searchFilmTitle;
		try {
			format = request.getParameter("format");
			searchFilmTitle = request.getParameter("search");
		} catch (Exception exception){
			System.out.println(exception);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		ArrayList<Film> filmList = fInfo.retrieveFilm(searchFilmTitle);
		
		if (format != null) {
			if (format.equals("xml")) {
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					StringWriter stringWriter = new StringWriter();
					for(Film film : filmList) {
					marshaller.marshal(film, stringWriter);
					}
					String xml = stringWriter.toString(); 
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("./jspHolder/GetAllFilms-XML.jsp");
					request.setAttribute("xml", xml);
					response.setContentType("text/xml");
					requestDispatcher.forward(request, response);
				} catch (Exception exception) {
					System.out.println(exception);
				}
			}

			if (format.equals("json")) {
				response.getWriter().append(gson.toJson(filmList));
			}

		} else {
			response.getWriter().append(gson.toJson(filmList));
		}
		
	}
}
