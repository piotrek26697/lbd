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

import pl.fis.logic.AverageStats;
import pl.fis.logic.DataEntry;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		@SuppressWarnings("unchecked")
		Map<String, AverageStats> data = (Map<String, AverageStats>) getServletContext().getAttribute("map");

		resp.setContentType("text/csv");
		resp.setHeader("Content-Disposition", "attachement; filename=\"SurveysData.csv\"");

		// OutputStream outputStream = resp.getOutputStream();

		try (OutputStream outputStream = resp.getOutputStream())
		{
			for (Map.Entry<String, AverageStats> entry : data.entrySet())
			{
				double avg = (entry.getValue().getQuality() + entry.getValue().getContactWithTeachers()
						+ entry.getValue().getInclusionOfWork()) / 3;

				String line = entry.getKey() + ", " + entry.getValue().getQuality() + ", "
						+ entry.getValue().getContactWithTeachers() + ", " + entry.getValue().getInclusionOfWork()
						+ ", " + avg + "\n";
				outputStream.write(line.getBytes());
				// outputStream.flush();
			}
		}

	}

}
