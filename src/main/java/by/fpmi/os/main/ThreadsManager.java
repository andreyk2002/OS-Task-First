package by.fpmi.os.main;


import java.util.*;

public class ThreadsManager {

    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) throws InterruptedException {
        Collections.sort(strings);
        Map<String, Integer> stringsWithFrequency =  buildMap(strings);
        List<StringThread> stringThreads = new ArrayList<>();
        addThreads(stringsWithFrequency, threadsCount, stringThreads);
        List<String> frequentString = new ArrayList<>();
        for (StringThread thread : stringThreads) {
            thread.start();
        }

        for (StringThread thread : stringThreads) {
            thread.join();
        }

        for (StringThread thread : stringThreads) {
            frequentString.addAll(thread.getFrequentStrings(n));
        }

        StringThread masterThread = new StringThread(frequentString);

        return masterThread.getFrequentStrings(n);
    }

    private Map<String, Integer> buildMap(List<String> strings) {
        Map<String, Integer> stringsWithFrequency = new HashMap<>();
        for (String key : strings) {
            if (stringsWithFrequency.containsKey(key)) {
                int previousFrequency = stringsWithFrequency.get(key);
                stringsWithFrequency.replace(key, previousFrequency + 1);
            } else {
                stringsWithFrequency.put(key, 1);
            }
        }
        return stringsWithFrequency;
    }

    private void addThreads(Map<String,Integer>stringsWithFrequency, int threadsCount, List<StringThread> stringThreads) {
        int count = stringsWithFrequency.size();
        int countPerThread = count / threadsCount;
        if (countPerThread == 0) {
            countPerThread = count % threadsCount;
        }
        int i = 0;
        while (count > 0) {
            if (count < countPerThread) {
                countPerThread = count;
            }
            addThread(stringsWithFrequency, stringThreads, countPerThread, i);
            i += countPerThread;
            count -= countPerThread;
        }
    }

    private void addThread(Map<String, Integer> strings, List<StringThread> stringThreads, int countPerThread, int i) {

    }

}
