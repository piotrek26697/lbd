package pl.fis.logic;

public class AverageStats
{
	private double quality;
	private double contactWithTeachers;
	private double inclusionOfWork;
	private double numberOfOpinions;

	public void makeAverage()
	{
		quality = quality / numberOfOpinions;
		contactWithTeachers = contactWithTeachers / numberOfOpinions;
		inclusionOfWork = inclusionOfWork / numberOfOpinions;
	}

	public double getQuality()
	{
		return quality;
	}

	public void setQuality(double quality)
	{
		this.quality = quality;
	}

	public double getContactWithTeachers()
	{
		return contactWithTeachers;
	}

	public void setContactWithTeachers(double contactWithTeachers)
	{
		this.contactWithTeachers = contactWithTeachers;
	}

	public double getInclusionOfWork()
	{
		return inclusionOfWork;
	}

	public void setInclusionOfWork(double inclusionOfWork)
	{
		this.inclusionOfWork = inclusionOfWork;
	}

	public double getNumberOfOpinions()
	{
		return numberOfOpinions;
	}

	public void setNumberOfOpinions(double numberOfOpinions)
	{
		this.numberOfOpinions = numberOfOpinions;
	}

}
