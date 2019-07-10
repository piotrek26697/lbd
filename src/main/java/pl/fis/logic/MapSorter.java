package pl.fis.logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSorter <K, V extends Comparable<? super V>>
{
	public  Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
	/*
	 * public Map<String, AverageStats> sortMap(Map<String, AverageStats> map) {
	 * List<Map.Entry<String, AverageStats>> list = new
	 * LinkedList<>(map.entrySet()); Collections.sort(list, new
	 * Comparator<Map.Entry<String, AverageStats>>() {
	 * 
	 * @Override public int compare(Entry<String, AverageStats> o1, Entry<String,
	 * AverageStats> o2) { return (o1.getValue().compareTo(o2.getValue())); }});
	 * Map<String, AverageStats> sortedMap = new LinkedHashMap<>();
	 * for(Map.Entry<String, AverageStats> entry : list) {
	 * sortedMap.put(entry.getKey(), entry.getValue()); } return sortedMap; }
	 */
}
