package by.fpmi.os.main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThreadsStringProcessor implements StringProcessor {
    
    //Логика StringThread следущая : он считает частоту встречаемости фиксированого числа строк 
    //и возвращает из этих строк n наиболее часто встречающихся

    @Override
    public List<String> findFrequentStrings(List<String> strings, int n, int threadsCount)
            throws InterruptedException {
        List<StringThread> stringThreads = buildThreads(strings, threadsCount);
        for (StringThread thread : stringThreads) {
            thread.start();
        }
        for (StringThread thread : stringThreads) {
            thread.join();
        }
        //Ключ - строка, value - частота его встечаемости
        List<Map.Entry<String, Integer>> stringsWithFrequency = new ArrayList<>();
        //Добавляем наиболее часто встечающиеся строки из каждого потока
        for (StringThread thread : stringThreads) {
            stringsWithFrequency.addAll(thread.getStringsWithFrequency(n));
        }
        List<String> result = new ArrayList<>();
        //сортируем их по убыванию
        stringsWithFrequency.sort((first, second) -> second.getValue() - first.getValue());

        //Т.к частота встречаемости больше не нужна, перекидываем строки в простой лист сток
        for (int i = 0; i < n; i++) {
            String string = stringsWithFrequency.get(i).getKey();
            result.add(string);
        }
        return result;
    }

    //каждый i - тый поток считает частоту встречаемости строк в диапазоне от beginI до beginI + sliceI
    // здесь определяем параметры beginI и sliceI
    private List<StringThread> buildThreads(List<String> strings, int threadsCount) {
       
        int size = strings.size();
        List<StringThread> threads = new ArrayList<>();
        int threadBegin = 0;
        int i = 0;
        //sliceI для каждого потока
        List<Integer> threadSlices = defineSlices(size, threadsCount);
        for (var currentSlice : threadSlices) {
            StringThread thread = new StringThread(strings, threadBegin, currentSlice);
            threads.add(thread);
            threadBegin += currentSlice;
        }
        return threads;
    }

    //size - размер всего списка, threadsCount - число потока
    // Тогда все потоки кроме последнего обрабатывают size/threadsCount елементов
    // A последний, то что осталось (size - size / threadsCount * threadsCount)
    private List<Integer> defineSlices(int size, int threadsCount) {
        int remainPart = size;
        ArrayList<Integer> slicesSizes = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            if (i == threadsCount - 1) {
                slicesSizes.add(remainPart);
            } else {
                int currentSize = size / threadsCount;
                remainPart -= currentSize;
                slicesSizes.add(currentSize);
            }
        }
        return slicesSizes;
    }
}
