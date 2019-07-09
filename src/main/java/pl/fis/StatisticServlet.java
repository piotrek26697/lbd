package pl.fis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.logic.AverageStats;
import pl.fis.logic.Calculator;
import pl.fis.logic.DataEntry;

@WebServlet("/average-statistics")
public class StatisticServlet extends HttpServlet
{
	@Inject
	private ServletContext servletContext;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		List<DataEntry> dataList = (List<DataEntry>) servletContext.getAttribute("list");
		Calculator calculator = new Calculator();
		Map<String, AverageStats> results = calculator.calculateScorePerUni(dataList);
		getServletContext().setAttribute("map", results);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n" + "</head>\r\n" + "<body>");

		for (Map.Entry<String, AverageStats> entry : results.entrySet())
		{
			writer.println(entry.getKey() + ":<br>");
			writer.println("Contact with teachers: "+entry.getValue().getContactWithTeachers()+"<br>");
			writer.println("Quality: "+entry.getValue().getQuality()+"<br>");
			writer.println("Inclusion of work: "+entry.getValue().getInclusionOfWork()+"<br>");
		}
		writer.println("<form method=\"get\" action=\"/lbd/download\">");
		writer.println("<button type=\"submit\">Download</button>");
		writer.println("</form>");
		writer.println("</body></html>");

	}

}
