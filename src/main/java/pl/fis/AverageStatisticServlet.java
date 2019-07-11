package pl.fis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.data.Stats;
import pl.fis.logic.AverageCalculator;
import pl.fis.logic.Calculator;
import pl.fis.qualifiers.Average;

@WebServlet("/average-statistics")
public class AverageStatisticServlet extends HttpServlet
{
	@Inject
	private ServletContext servletContext;
	
	@Average @Inject
	private Calculator calculator;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		List<DataEntry> dataList = (List<DataEntry>) servletContext.getAttribute("list");
		//AverageCalculator calculator = new AverageCalculator();
		Map<String, Stats> results = calculator.calculateScorePerUni(dataList);
		getServletContext().setAttribute("averageMap", results);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Average Statistics</title>\r\n" + "</head>\r\n" + "<body>");
		
		Formatter formatter = new Formatter(req.getLocale());
		
		for (Map.Entry<String, Stats> entry : results.entrySet())
		{
			AverageStats avg = (AverageStats)(entry.getValue());
			writer.println(entry.getKey() + ":<br>");
			writer.println("Contact with teachers: "+ formatter.format("%.2f", avg.getContactWithTeachers())+"<br>");
			formatter.flush();
			writer.println("Quality: "+formatter.format("%.2f", avg.getQuality())+"<br>");
			formatter.flush();
			writer.println("Inclusion of work: "+formatter.format("%.2f", avg.getInclusionOfWork())+"<br>");
		}
		writer.println("<form method=\"get\" action=\"/lbd/download\">");
		writer.println("<button type=\"submit\">Download</button>");
		writer.println("</form>");
		writer.println("</body></html>");
	}

}
