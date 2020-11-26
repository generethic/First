package HomeWork6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

import static HomeWork6.WarAndPeace.filename;

public class Utils {
    static Set<String> removeExcludeWords(Set<String> set, Set<String> set1) {
        StringBuilder stringBuilder = new StringBuilder();
        set.forEach(s -> {
            s = s.replaceAll("[^A-Za-zА-Яа-я0-9]", " ");
            set1.add(s);
        });
        return set1;
    }
    static List<String> removeExcludeWords(List<String> list, List<String> list1) {
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(s -> {
            s = s.replaceAll("[^A-Za-zА-Яа-я0-9]", " ");
            list1.add(s);
        });
        return list1;
    }
    static Map<String, Integer> getMapWithQuantities(List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < list.size(); ++i) {
            if (map.containsKey(list.get(i))) {
                Integer val = map.get(list.get(i));
                map.put(list.get(i), ++val);
            } else {
                map.put(list.get(i), 1);
            }
        }

        return map;
    }
    static Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(unsortedMap.entrySet());
        list.sort((o2, o1) -> o1.getValue() - o2.getValue());
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    static long searchWithClassesInterfaces(ISearchEngine iSearchEngine, String whereSearch, String whatSearch) {
        return iSearchEngine.search(whereSearch,whatSearch);
    }
    static String converseListToWord(List<String    > list) {
        String word = String.join(" ",list);
        StringBuilder stringBuilder = new StringBuilder(word);
        return stringBuilder.append(" ").toString();
    }

    static void printMapRatedWords(Map<String, Integer> map) {
        System.out.println("Топ - 10 чаще всего встречающихся слов");
        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
        for (int i = 0; i < 10; i++) {
            Map.Entry entry = itr.next();
            System.out.println("Слово \"" + entry.getKey() + "\" встречается " + entry.getValue() + " раз");
        }
    }
    public static HashMap<String,Integer> map(int delimetr) {
        String newLine = null;
        int count = 0;
        try {
            newLine = new String(Files.readAllBytes(Path.of(filename)));
            newLine = newLine.replaceAll("[^A-Za-zА-Яа-я0-9]", " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < newLine.length(); i++) {
            if(newLine.charAt(i)==' ') {
                count++;
            }
        }
        int poSkolkoNadoDelit = count/delimetr;
        int countZapolnenie = 0;
        int kolichestvoDeleniy = count/poSkolkoNadoDelit;
        for (int i = 0; i < kolichestvoDeleniy; i++) {
            for (int j = 0; j < newLine.length(); j++) {
                if (newLine.charAt(j) == ' ') {
                    countZapolnenie++;
                    if (countZapolnenie == poSkolkoNadoDelit) {
                        if(i + 1 <= kolichestvoDeleniy) {
                            countZapolnenie = 0;
                            map.putIfAbsent(newLine.substring(0, j).trim(), 1);
                            newLine = newLine.substring(j);
                            j=0;
                            i++;
                        }
                        else {
                            map.putIfAbsent(newLine.trim(), 1);
                        }
                    }
                    else {
                        if(i+1==kolichestvoDeleniy) {
                            map.putIfAbsent(newLine.trim(),1);
                            break;
                        }
                    }
                }
            }
        }
        return map;
    }
    private static int searchCallableByClass(ISearchEngine iSearchEngine, Map<String,Integer> map, String whatSearch) {
        int count = 0;
        HashMap<String,Integer> map1 = new HashMap<>(map);
        for(Map.Entry<String,Integer> entry : map1.entrySet()) {
            if(entry.getKey()!=null) {
                String whereSearch = entry.getKey();
                count +=iSearchEngine.search(whereSearch, whatSearch);
            }
        }
        return count;
    }
    static void searchByMultithreadingByLambda(ISearchEngine iSearchEngine, HashMap<String,Integer> map, String whatSearch1, String whatSearch2, String whatSearch3) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future1 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            return searchCallableByClass(iSearchEngine,map,whatSearch1);
        });
        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            return searchCallableByClass(iSearchEngine,map,whatSearch2);
        });
        Future<Integer> future3 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            return searchCallableByClass(iSearchEngine,map,whatSearch3);
        });
        executorService.shutdown();
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        try {
            result1 = future1.get();
            result2 = future2.get();
            result3 = future3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Слово "+whatSearch1+" встречается: "+result1+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
        System.out.println("Слово "+whatSearch2+" встречается: "+result2+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
        System.out.println("Слово "+whatSearch3+" встречается: "+result3+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
    }
    static void searchByMultithreadingByClass(ISearchEngine iSearchEngine, HashMap<String,Integer> map, String whatSearch1, String whatSearch2, String whatSearch3) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future1 = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return searchCallableByClass(iSearchEngine,map,whatSearch1);
            }
        });
        Future<Integer> future2 = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return searchCallableByClass(iSearchEngine,map,whatSearch2);
            }
        });
        Future<Integer> future3 = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return searchCallableByClass(iSearchEngine,map,whatSearch3);
            }
        });
        executorService.shutdown();
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        try {
            result1 = future1.get();
            result2 = future2.get();
            result3 = future3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Слово "+whatSearch1+" встречается: "+result1+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
        System.out.println("Слово "+whatSearch2+" встречается: "+result2+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
        System.out.println("Слово "+whatSearch3+" встречается: "+result3+" раз."+"Тип интерфеса - " + iSearchEngine.toString());
    }
}
