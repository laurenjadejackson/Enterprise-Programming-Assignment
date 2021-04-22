package main;

import java.io.IOException;
import java.io.StringWriter;

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
 * Servlet implementation class GetFilmByIdServlet
 */
@WebServlet("/GetFilmByIdServlet")
public class GetFilmByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFilmByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmInfo fInfo = new FilmInfo();
		Gson gson = new Gson();

		String format, searchFilmId;
		int filmId;
		try {
			format = request.getParameter("format");
			searchFilmId = request.getParameter("id");
			filmId = Integer.parseInt(searchFilmId);
		} catch (Exception exception){
			System.out.println(exception);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Film film = fInfo.getFilm(filmId);
		
		if (format != null) {
			if (format.equals("xml")) {
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					StringWriter stringWriter = new StringWriter();
					
					marshaller.marshal(film, stringWriter);
					String xml = stringWriter.toString(); 
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("./jspHolder/GetFilmsById-XML.jsp");
					request.setAttribute("xml", xml);
					response.setContentType("text/xml");
					requestDispatcher.forward(request, response);
				} catch (Exception exception) {
					System.out.println(exception);
				}
			}

			if (format.equals("json")) {
				response.getWriter().append(gson.toJson(film));
			}

		} else {
			response.getWriter().append(gson.toJson(film));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
