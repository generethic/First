package HomeWork5;

import java.util.*;


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
    static long searchWithClassesInterfaces(ISearchEngine iSearchEngine, String whereSearch, String whatSearch) {
        return iSearchEngine.search(whereSearch,whatSearch);
    }
}
