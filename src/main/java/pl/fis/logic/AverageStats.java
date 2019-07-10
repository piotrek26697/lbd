package pl.fis.logic;

public class AverageStats implements Comparable<AverageStats>
{
	private double quality;
	private double contactWithTeachers;
	private double inclusionOfWork;
	private double overall;
	private double numberOfOpinions;

	@Override
	public int compareTo(AverageStats o)
	{
		if (overall > o.overall)
			return 1;
		else if (overall < o.overall)
			return -1;
		else
			return 0;
	}

	public double getOverall()
	{
		return overall;
	}

	public void setOverall(double overall)
	{
		this.overall = overall;
	}

	public void makeAverage()
	{
		quality = quality / numberOfOpinions;
		contactWithTeachers = contactWithTeachers / numberOfOpinions;
		inclusionOfWork = inclusionOfWork / numberOfOpinions;
		overall = (quality + contactWithTeachers + inclusionOfWork) / 3;
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
