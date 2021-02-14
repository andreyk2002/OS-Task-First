package by.fpmi.os.main;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreadsManagerTest {
    private static final List<String> SINGLE_ELEMENT = Collections.singletonList("a");
    private static final List<String> TEST_STRINGS = Arrays.asList("a", "b", "b", "c", "c", "c" , "d", "d", "d", "d");
    private final ThreadsManager manager = new ThreadsManager();

    @Test
    public void testFindFrequentStringShouldFindWhenSingleElementListApplied() throws InterruptedException {
        List<String>result = manager.findFrequentStrings(SINGLE_ELEMENT, 1,5);

        Assert.assertEquals(SINGLE_ELEMENT, result);
    }

    @Test
    public void testFindFrequentStringShouldFindWhenMultiElementListApplied() throws InterruptedException {
        List<String>result = manager.findFrequentStrings(TEST_STRINGS, 2,3);
        List<String>expected = Arrays.asList("d", "c");

        Assert.assertEquals(SINGLE_ELEMENT, result);
    }
}
