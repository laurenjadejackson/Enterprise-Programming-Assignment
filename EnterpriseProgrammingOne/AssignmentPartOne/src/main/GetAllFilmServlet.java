package main;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.PreparedStatement;
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
 * Servlet implementation class GetAllFilmServlet
 */
@WebServlet("/GetAllFilmServlet")
public class GetAllFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmInfo fInterface = new FilmInfo();
		Gson gson = new Gson();

		ArrayList<Film> filmResults = fInterface.listFilm();

		String format = request.getParameter("format");

		if (format != null) {
			if (format.equals("xml")) {
				try {
					JAXBContext jaxbContect = JAXBContext.newInstance(Film.class);
					Marshaller marshaller = jaxbContect.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					StringWriter stringWriter = new StringWriter();
					for (Film film : filmResults) {
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
			else if (format.equals("json")) {
				//response.getWriter().append(gson.toJson(filmResults));
				String json = gson.toJson(filmResults);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("./jspHolder/GetAllFilms-XML.jsp");
				request.setAttribute("xml", json);
				response.setContentType("text/json");
				requestDispatcher.forward(request, response);
			}
			else if (format.equals("table")) {
				String htmlTable = "<table>\r\n"
						+ "	<tr>\r\n"
						+ "		<th>ID</th>\r\n"
						+ "		<th>Title</th>\r\n"
						+ "		<th>Year</th>\r\n"
						+ "		<th>Director</th>\r\n"
						+ "		<th>Stars</th>\r\n"
						+ "		<th>Review</th>\r\n"
						+ "	</tr>";
				
				for (Film film : filmResults) {
					String htmlRow = String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", film.getId(), film.getTitle(), film.getYear(), film.getDirector(), film.getStars(), film.getReview());
					htmlTable += htmlRow;
				}
				 
				htmlTable += "</table>";
				
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("./jspHolder/GetAllFilms-Table.jsp");
				request.setAttribute("table", htmlTable);
				response.setContentType("text/html");
				requestDispatcher.forward(request, response);
			}

		} else {
			response.getWriter().append(gson.toJson(filmResults));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
