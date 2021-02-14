package by.fpmi.os.main;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class StringProcessorTest {

    private static final List<String> SINGLE_ELEMENT = Collections.singletonList("a");
    private static final List<String> TEST_STRINGS = Arrays.asList("a", "b", "b", "c", "c", "c", "d", "d", "d", "d");
    private final StringProcessor processor = getProcessor();

    protected abstract StringProcessor getProcessor();

    @Test
    public void testFindFrequentStringShouldFindWhenSingleElementListApplied() {
        List<String> result = processor.findFrequentStrings(SINGLE_ELEMENT, 1, 5);
        Assert.assertEquals(result, SINGLE_ELEMENT);
    }

    @Test
    public void testFindFrequentStringShouldFindWhenMultiElementListApplied() {
        List<String> result = processor.findFrequentStrings(TEST_STRINGS, 2, 3);
        List<String> expected = Arrays.asList("d", "c");
        Assert.assertEquals(result, expected);
    }
}
