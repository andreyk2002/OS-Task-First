package by.fpmi.os.main;

import java.util.*;
import java.util.Map.Entry;

public class StringThread extends Thread {

    private final List<String> strings;
    private final int begin;

    public StringThread(List<String>strings, int begin) {
        this.strings = strings;
        this.begin = begin;
    }


    @Override
    public void run() {
        strings = new ArrayList<>();
        List<Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
        list.sort(Entry.comparingByValue((a, b) -> b - a));
        for (Entry<String, Integer> entry : list) {
            strings.add(entry.getKey());
        }
    }

    public List<String, Integer> getFrequentStrings(int n) {
        if(n >= strings.size()){
            return strings;
        }
       return strings.subList(0, n);
    }
}