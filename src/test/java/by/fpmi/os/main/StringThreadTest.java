package by.fpmi.os.main;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class StringThreadTest {

    private static final List<String> TEST_STRINGS = Arrays.asList("a", "b", "b", "c", "c", "c");
    private static final List<String> SINGLE_ELEMENT = Collections.singletonList("a");


    @Test
    public void testRunShouldSortSingleElementListByFrequency(){
        Map<String, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put("a", 1);
        StringThread thread = new StringThread(frequencyMap);
        thread.run();

        List<String> result = thread.getFrequentStrings(1);
        List<String> expected = Collections.singletonList("a");

        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRunShouldSortListByFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        frequencyMap.put("a", 1);
        frequencyMap.put("b", 2);
        frequencyMap.put("c", 3);
        frequencyMap.put("d", 4);
        StringThread thread = new StringThread(frequencyMap);
        thread.run();

        List<String> result = thread.getFrequentStrings(2);
        List<String> expected = Arrays.asList("d", "c");

        Assert.assertEquals(result, expected);
    }


}
