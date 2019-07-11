package pl.fis.logic;

import java.util.List;
import java.util.Map;

import pl.fis.data.AverageStats;
import pl.fis.data.DataEntry;
import pl.fis.data.Stats;

public interface Calculator
{
	Map<String, Stats> calculateScorePerUni(List<DataEntry> list);
}
