package by.fpmi.os.main;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamStringProcessor implements StringProcessor {
    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount)
            throws ExecutionException, InterruptedException {
        ForkJoinPool threadPool = new ForkJoinPool(threadsCount);
        return threadPool.submit(() -> strings.parallelStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                .get()
                .entrySet()
                .stream()
                .sorted((first, second) -> (int) (second.getValue() - first.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .subList(0, n);
    }
}
