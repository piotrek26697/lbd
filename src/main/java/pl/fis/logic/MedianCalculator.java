package pl.fis.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.data.MedianStats;
import pl.fis.data.Stats;
import pl.fis.qualifiers.Mediana;

@Mediana
public class MedianCalculator implements Calculator
{

	@Override
	public Map<String, Stats> calculateScorePerUni(List<DataEntry> list)
	{
		Map<String, Stats> statistics = new HashMap<>();
		if (list != null)
		{
			for (DataEntry data : list)
			{
				String uniName = data.getUniversityName();
				Stats avg = statistics.get(uniName);
				MedianStats tmp;
				if (avg == null)
					tmp = new MedianStats();
				else
					tmp = (MedianStats) avg;
				tmp.getContactWithTeachersMarks().add(data.getContactWithTeachers());
				tmp.getInclusionOfWorkMarks().add(data.getInclusionOfWork());
				tmp.getQualityMarks().add(data.getQuality());
				statistics.put(uniName, tmp);
			}
		}
		return statistics;
	}

}
