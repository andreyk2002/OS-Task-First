package by.fpmi.os.main;


import java.util.*;

public class ThreadsManager {


    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) throws InterruptedException {
        Collections.sort(strings);
        Map<String, Integer> stringsWithFrequency = buildMap(strings);
        List<StringThread> stringThreads = buildThreads(stringsWithFrequency, strings.size(), threadsCount);

        List<String> result = new ArrayList<>();
        for (StringThread thread : stringThreads) {
            thread.start();
        }

        for (StringThread thread : stringThreads) {
            thread.join();
        }

        for (StringThread thread : stringThreads) {
            result.addAll(thread.getFrequentStrings(n));
        }
        Map<String, Integer> masterMap = buildMap(result);
        StringThread masterThread = new StringThread(masterMap);

        return masterThread.getFrequentStrings(n);
    }

    private List<StringThread> buildThreads(Map<String, Integer> stringsWithFrequency, int size, int threadsCount) {
        List<StringThread> threads = new ArrayList<>();
        List<Integer> threadSlices = defineSlices(size, threadsCount);

        List<String> strings = new ArrayList(stringsWithFrequency.keySet());
        int begin = 0;
        for (int i = 0; i < threadsCount; i++) {
            Map<String, Integer> mapSlice = new HashMap<>();
            int sliceSize = threadSlices.get(i);
            int end = begin + sliceSize;
            for (; begin < end; begin++) {
                String key = strings.get(begin);
                int frequency = stringsWithFrequency.get(key);
                mapSlice.put(key, frequency);
            }
            threads.add(new StringThread(mapSlice));
        }
    }

    private List<Integer> defineSlices(int size, int threadsCount) {
        //

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

}
