package pl.fis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.data.MedianStats;
import pl.fis.data.Stats;
import pl.fis.logic.Calculator;
import pl.fis.qualifiers.Mediana;

@WebServlet("/median-statistics")
public class MedianStatisticServlet extends HttpServlet
{
	@Mediana
	@Inject
	private Calculator calculator;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		List<DataEntry> list = (List<DataEntry>) getServletContext().getAttribute("list");
		Map<String, Stats> medians = calculator.calculateScorePerUni(list);

		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Average Statistics</title>\r\n" + "</head>\r\n" + "<body>");

		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, req.getLocale());

		for (Map.Entry<String, Stats> entry : medians.entrySet())
		{
			MedianStats median = (MedianStats) (entry.getValue());
			writer.println(entry.getKey() + ":<br>");
			writer.println("Contact with teachers: "
					+ formatter.format("%.2f", median.getMedianContactWithTeachersMarks()) + "<br>");
			sb.setLength(0);
			writer.println("Quality: " + formatter.format("%.2f", median.getMedianOfQualityMarks()) + "<br>");
			sb.setLength(0);
			writer.println(
					"Inclusion of work: " + formatter.format("%.2f", median.getMedianInclusionOfWorkMarks()) + "<br>");
			sb.setLength(0);
		}
		// writer.println("<form method=\"get\" action=\"/lbd/download\">");
		// writer.println("<button type=\"submit\">Download</button>");
		// writer.println("</form>");
		writer.println("</body></html>");

	}

}
