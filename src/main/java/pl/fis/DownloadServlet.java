package pl.fis;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.logic.MapSorter;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		Map<String, AverageStats> data = (Map<String, AverageStats>) getServletContext().getAttribute("averageMap");
		MapSorter<String, AverageStats> mapSorter = new MapSorter<>();
		//MapSorter<String, AverageStats> mapSorter = new MapSorter<>();
		Map<String, AverageStats> sortedData = mapSorter.sortByValue(data);

		resp.setContentType("text/csv");
		resp.setHeader("Content-Disposition", "attachement; filename=\"SurveysData.csv\"");

		// OutputStream outputStream = resp.getOutputStream();

		try (OutputStream outputStream = resp.getOutputStream())
		{
			for (Map.Entry<String, AverageStats> entry : sortedData.entrySet())
			{
				double avg = entry.getValue().getOverall();

				String line = entry.getKey() + ", " + entry.getValue().getQuality() + ", "
						+ entry.getValue().getContactWithTeachers() + ", " + entry.getValue().getInclusionOfWork()
						+ ", " + avg + "\n";
				outputStream.write(line.getBytes());
				// outputStream.flush();
			}
		}

	}

}
