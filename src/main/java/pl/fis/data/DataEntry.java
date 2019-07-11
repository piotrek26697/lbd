package pl.fis.data;

public class DataEntry
{
	private String firstName;
	private String lastName;
	private String universityName;
	private String facultyName;
	private Degree degree;
	private int quality;
	private int contactWithTeachers;
	private int inclusionOfWork;
	

	public DataEntry() {}
	
	public DataEntry(String firstName, String lastName, String universityName, String facultyName, Degree degree,
			int quality, int contactWithTeachers, int inclusionOfWork)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.universityName = universityName;
		this.facultyName = facultyName;
		this.degree = degree;
		this.quality = quality;
		this.contactWithTeachers = contactWithTeachers;
		this.inclusionOfWork = inclusionOfWork;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUniversityName()
	{
		return universityName;
	}

	public void setUniversityName(String universityName)
	{
		this.universityName = universityName;
	}

	public String getFacultyName()
	{
		return facultyName;
	}

	public void setFacultyName(String facultyName)
	{
		this.facultyName = facultyName;
	}

	public Degree getDegree()
	{
		return degree;
	}

	public void setDegree(Degree degree)
	{
		this.degree = degree;
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}

	public int getContactWithTeachers()
	{
		return contactWithTeachers;
	}

	public void setContactWithTeachers(int contactWithTeachers)
	{
		this.contactWithTeachers = contactWithTeachers;
	}

	public int getInclusionOfWork()
	{
		return inclusionOfWork;
	}

	public void setInclusionOfWork(int inclusionOfWork)
	{
		this.inclusionOfWork = inclusionOfWork;
	}

}
