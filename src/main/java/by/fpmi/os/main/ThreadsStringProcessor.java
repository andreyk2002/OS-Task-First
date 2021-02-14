package by.fpmi.os.main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ThreadsStringProcessor implements StringProcessor {

    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) {
        Collections.sort(strings);
        List<StringThread> stringThreads = buildThreads(strings, threadsCount);
        for (StringThread thread : stringThreads) {
            thread.start();
        }
        for (StringThread thread : stringThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        for (StringThread thread : stringThreads) {
            result.addAll(thread.getStringsWithFrequency(n));
        }
        List<String> res = new ArrayList<>();
        result.sort((first, second) -> second.getValue() - first.getValue());
        for (int i = 0; i < n; i++) {
            String string = result.get(i).getKey();
            res.add(string);
        }
        return res;
    }

    private List<StringThread> buildThreads(List<String> strings, int threadsCount) {
        int size = strings.size();
        List<StringThread> threads = new ArrayList<>();
        int currentPosition = 0;
        int currentThread = 0;
        List<Integer> threadSlices = defineSlices(size, threadsCount);

        while (currentPosition < size) {
            int currentSlice = threadSlices.get(currentThread);
            StringThread thread = new StringThread(strings, currentPosition, currentSlice);
            threads.add(thread);
            currentPosition += currentSlice;
            currentThread++;
        }
        return threads;
    }

    private List<Integer> defineSlices(int size, int threadsCount) {
        int remains = size;
        ArrayList<Integer> slicesSizes = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            if (i == threadsCount - 1) {
                slicesSizes.add(remains);
            } else {
                int currentSize = size / threadsCount;
                remains -= currentSize;
                slicesSizes.add(currentSize);
            }
        }
        return slicesSizes;
    }
}
