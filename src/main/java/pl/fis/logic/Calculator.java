package pl.fis.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator
{
	public Map<String, AverageStats> calculateScorePerUni(List<DataEntry> list)
	{
		Map<String, AverageStats> map = this.sum(list);
		for(AverageStats avg: map.values())
		{
			avg.makeAverage();
		}
		
		return map;

	}

	private Map<String,AverageStats> sum(List<DataEntry> list)
	{
		Map<String, AverageStats> statistics = new HashMap<>();
		for(DataEntry data : list)
		{
			String uniName = data.getUniversityName();
			AverageStats avg = statistics.get(uniName);
			if(avg == null)
			{
				AverageStats tmp = new AverageStats();
				tmp.setContactWithTeachers(data.getContactWithTeachers());
				tmp.setQuality(data.getQuality());
				tmp.setInclusionOfWork(data.getInclusionOfWork());
				tmp.setNumberOfOpinions(1);
				statistics.put(uniName, tmp);
			}
			else
			{
				avg.setContactWithTeachers(avg.getContactWithTeachers()+data.getContactWithTeachers());
				avg.setInclusionOfWork(avg.getInclusionOfWork()+data.getInclusionOfWork());
				avg.setQuality(avg.getQuality()+data.getQuality());
				avg.setNumberOfOpinions(avg.getNumberOfOpinions()+1);
				statistics.put(uniName, avg);
			}
		}
		return statistics;
	}
}
