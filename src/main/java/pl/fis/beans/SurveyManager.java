package pl.fis.beans;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import pl.fis.data.DataEntry;
import pl.fis.data.Degree;

@Stateless
public class SurveyManager
{
	@Inject
	private ServletContext servletContext;

	public void saveAnswers(Map<String, String[]> parameters)
	{
		Degree degree;

		if (parameters.get("degree")[0].equalsIgnoreCase((Degree.MASTER.name())))
			degree = Degree.MASTER;
		else
			degree = Degree.BACHELOR;
		DataEntry dataEntry = new DataEntry();
		dataEntry.setFirstName(parameters.get("firstName")[0]);
		dataEntry.setLastName(parameters.get("lastName")[0]);
		dataEntry.setUniversityName(parameters.get("universityName")[0].toUpperCase());
		dataEntry.setFacultyName(parameters.get("facultyName")[0]);
		dataEntry.setDegree(degree);
		dataEntry.setQuality(Integer.parseInt(parameters.get("quality")[0]));
		dataEntry.setContactWithTeachers(Integer.parseInt(parameters.get("contactWithTeachers")[0]));
		dataEntry.setInclusionOfWork(Integer.parseInt(parameters.get("practicalExperience")[0]));
		
		@SuppressWarnings("unchecked")
		List<DataEntry> dataList = (List<DataEntry>) servletContext.getAttribute("list");

		Integer counter = (Integer) servletContext.getAttribute("counter");
		
		dataList.add(dataEntry);
		counter++;
		servletContext.setAttribute("list", dataList);
		servletContext.setAttribute("counter", counter);
	}
}
