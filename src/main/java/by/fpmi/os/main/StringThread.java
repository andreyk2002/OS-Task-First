package by.fpmi.os.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringThread extends Thread {

    private final List<String> result = new ArrayList<>();
    private Map<String, Integer> stringsWithFrequency = new HashMap<>();

    public StringThread(Map<String, Integer> stringsWithFrequency) {
        this.stringsWithFrequency = stringsWithFrequency;
    }

    @Override
    public void run() {
        List<Entry<String, Integer>> list = new ArrayList<>(stringsWithFrequency.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        for (Entry<String, Integer> element : list) {
            result.add(element.getKey());
        }
    }

    public List<String> getFrequentStrings(int n) {
        if (n >= result.size()) {
            return result;
        }
        return result.subList(0, n);
    }
}