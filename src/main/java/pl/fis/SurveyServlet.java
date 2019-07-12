package pl.fis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.fis.beans.SurveyManager;
import pl.fis.data.DataEntry;
import pl.fis.data.Degree;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet
{
	@Inject
	private ServletContext servletContext;

	@Inject
	private SurveyManager surveyManagerBean;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer = resp.getWriter();

		resp.setContentType("text/html");
		writer.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n" + "</head>\r\n" + "<body>");

		Boolean voteForbidden = (Boolean) req.getSession().getAttribute("voteForbidden");
		if(voteForbidden == null)
			voteForbidden = false;
		if (voteForbidden)
		{
			writer.println("You have already submitted your survey");
		} else
		{
			writer.println("Thanks " + req.getParameter("firstName") + " " + req.getParameter("lastName") + " "
					+ "for participating " + "in our university survey.<br>Your answers:<br>"
					+ req.getParameter("universityName") + "<br>" + req.getParameter("facultyName") + "<br>"
					+ req.getParameter("degree") + "<br>");

			surveyManagerBean.saveAnswers(req.getParameterMap());

			writer.println("Number of surveys: " + servletContext.getAttribute("counter"));

			req.getSession().setAttribute("voteForbidden", true);
		}
		writer.println("</body></html>");
	}

	@PostConstruct
	private void initService()
	{
		Integer counter = 0;
		List<DataEntry> list = new ArrayList<>();
		servletContext.setAttribute("counter", counter);
		servletContext.setAttribute("list", list);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// req.getSession();
		resp.sendRedirect("survey.html");
	}

}
