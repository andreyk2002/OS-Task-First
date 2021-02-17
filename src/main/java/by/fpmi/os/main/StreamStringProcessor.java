package by.fpmi.os.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class StreamStringProcessor implements StringProcessor {
    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount) {
        ForkJoinPool threadPool = new ForkJoinPool(threadsCount);
        Map<String, Long> stringFrequency = new HashMap<>();
        strings.parallelStream()
                .forEach(s -> stringFrequency.put(s, Stream.of(s).count()));
        return null;
    }
}
