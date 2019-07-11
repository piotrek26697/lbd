package pl.fis.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.data.Stats;
import pl.fis.qualifiers.Average;

@Average
public class AverageCalculator implements Calculator
{
	@Override
	public Map<String, Stats> calculateScorePerUni(List<DataEntry> list)
	{
		Map<String, Stats> map = this.sum(list);
		for (Stats tmp : map.values())
		{
			AverageStats avg = (AverageStats) tmp;
			avg.setContactWithTeachers(avg.getContactWithTeachers()/avg.getNumberOfOpinions());
			avg.setInclusionOfWork(avg.getInclusionOfWork()/avg.getNumberOfOpinions());
			avg.setQuality(avg.getQuality()/avg.getNumberOfOpinions());
			avg.setOverall((avg.getContactWithTeachers()+avg.getInclusionOfWork()+avg.getQuality())/3);
		}

		return map;

	}

	private Map<String, Stats> sum(List<DataEntry> list)
	{
		Map<String, Stats> statistics = new HashMap<>();
		if (list != null)
		{
			for (DataEntry data : list)
			{
				String uniName = data.getUniversityName();
				Stats avg = statistics.get(uniName);
				if (avg == null)
				{
					AverageStats tmp = new AverageStats();
					tmp.setContactWithTeachers(data.getContactWithTeachers());
					tmp.setQuality(data.getQuality());
					tmp.setInclusionOfWork(data.getInclusionOfWork());
					tmp.setNumberOfOpinions(1);
					statistics.put(uniName, tmp);
				} else
				{
					AverageStats tmp = (AverageStats) avg;
					tmp.setContactWithTeachers(tmp.getContactWithTeachers() + data.getContactWithTeachers());
					tmp.setInclusionOfWork(tmp.getInclusionOfWork() + data.getInclusionOfWork());
					tmp.setQuality(tmp.getQuality() + data.getQuality());
					tmp.setNumberOfOpinions(tmp.getNumberOfOpinions() + 1);
					statistics.put(uniName, tmp);
				}
			}
		}
		return statistics;
	}
}
