package pl.fis.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MedianStats extends Stats
{
	private List<Integer> qualityMarks;
	private List<Integer> inclusionOfWorkMarks;
	private List<Integer> contactWithTeachersMarks;

	public MedianStats()
	{
		qualityMarks = new LinkedList<>();
		inclusionOfWorkMarks = new LinkedList<>();
		contactWithTeachersMarks = new LinkedList<>();
	}

	public List<Integer> getQualityMarks()
	{
		return qualityMarks;
	}



	public List<Integer> getInclusionOfWorkMarks()
	{
		return inclusionOfWorkMarks;
	}



	public List<Integer> getContactWithTeachersMarks()
	{
		return contactWithTeachersMarks;
	}



	public double getMedianOfQualityMarks()
	{
		Collections.sort(qualityMarks);

		double median;
		if (qualityMarks.size() % 2 == 0)
			median = ((double) qualityMarks.get(qualityMarks.size() / 2)
					+ (double) qualityMarks.get(qualityMarks.size() / 2 - 1)) / 2;
		else
			median = (double) qualityMarks.get(qualityMarks.size() / 2);

		return median;
	}
	
	public double getMedianContactWithTeachersMarks()
	{
		Collections.sort(contactWithTeachersMarks);

		double median;
		if (contactWithTeachersMarks.size() % 2 == 0)
			median = ((double) contactWithTeachersMarks.get(contactWithTeachersMarks.size() / 2)
					+ (double) contactWithTeachersMarks.get(contactWithTeachersMarks.size() / 2 - 1)) / 2;
		else
			median = (double) contactWithTeachersMarks.get(contactWithTeachersMarks.size() / 2);

		return median;
	}

	public double getMedianInclusionOfWorkMarks()
	{
		Collections.sort(inclusionOfWorkMarks);

		double median;
		if (inclusionOfWorkMarks.size() % 2 == 0)
			median = ((double) inclusionOfWorkMarks.get(inclusionOfWorkMarks.size() / 2)
					+ (double) inclusionOfWorkMarks.get(inclusionOfWorkMarks.size() / 2 - 1)) / 2;
		else
			median = (double) inclusionOfWorkMarks.get(inclusionOfWorkMarks.size() / 2);

		return median;
	}

}
