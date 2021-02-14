package by.fpmi.os.main;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

public class StringThreadTest {

    private static final List<String> TEST_STRINGS = Arrays.asList("a", "b", "b", "c", "c", "c");
    private static final List<String> SINGLE_ELEMENT = Collections.singletonList("a");


    @Test
    public void testRunShouldSortSingleElementListByFrequency(){
        StringThread thread = new StringThread(SINGLE_ELEMENT);
        thread.run();

        List<String>result = thread.getFrequentStrings(1);
        List<String>expected = Collections.singletonList("a");

        Assert.assertEquals(result, expected);
    }

    @Test
    public void testRunShouldSortListByFrequency(){
        StringThread thread = new StringThread(TEST_STRINGS);
        thread.run();

        List<String>result = thread.getFrequentStrings(3);
        List<String>expected = Arrays.asList("c", "b", "a");

        Assert.assertEquals(result, expected);
    }


}
