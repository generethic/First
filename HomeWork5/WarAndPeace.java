package HomeWork5;

import java.io.*;
import java.util.*;

public class WarAndPeace {
    public static void main(String[] args) {
        String filename = "Война и Мир.txt";
        File file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {
            Set<String> set = new HashSet<>();
            List<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    set.add(s[i]);
                    list.add(s[i]);
                }
            }
            /*
            originalSet - это коллекция с уникальными значениями
             */
            Set<String> originalSet = removeExcludeWords(set,new HashSet<>());

            /*
            onlyWordsSet - экто коллекция с повторяющимися значениями
             */
            List<String> onlyWordsSet = removeExcludeWords(list,new ArrayList<>());
            onlyWordsSet.removeIf((item)->item.length() == 0 || item.isBlank() || item.equals(" ")); //удаление пробелов

            /*
                Поиск слов,встречающихся в тексе, используя класс RegExSearch
            */
            RegExSearch regExSearch = new RegExSearch();
            String wordForRegExp = regExSearch.toWord(onlyWordsSet); //перевод коллекции в строку
            regExSearch.search(wordForRegExp,"ВойНа");
            regExSearch.search(wordForRegExp,"И");
            regExSearch.search(wordForRegExp,"мир");

            /*
                Поиск слов,встречающихся в тексе, используя класс EasySearch
             */
            EasySearch easySearch = new EasySearch();
            String word = easySearch.toWord(onlyWordsSet); //перевод коллекции в строку
            easySearch.search(word,"ВОЙНа");
            easySearch.search(word,"И");
            easySearch.search(word,"мир");

            /*
                Метод, печатающий 10 чаще всего втречающихся слов
             */
//            printMapRatedWords(sortByComparator(getMapWithQuantities(onlyWordsSet)));
        } catch (IOException e) {

        }
    }

    private static Set<String> removeExcludeWords(Set<String> set, Set<String> set1) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(Character.isLetterOrDigit(c[i])) {
                    stringBuilder.append(c[i]);
                }else {
                    set1.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }
            set1.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return set1;
    }
    private static List<String> removeExcludeWords(List<String> set, List<String> set1) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(Character.isAlphabetic(c[i])) {
                    stringBuilder.append(c[i]);
                }else {
                    set1.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }
            set1.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return set1;
    }

    private static Map<String, Integer> getMapWithQuantities(List<String> list)
    {
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

    private static Map<String,Integer> sortByComparator(Map<String,Integer> unsortedMap) {
        List<Map.Entry<String,Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Map<String,Integer> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> entry : list) {
            sortedMap.put(entry.getKey(),entry.getValue());
        }
        return sortedMap;
    }
    private static void printMapRatedWords(Map<String, Integer> map) {
        System.out.println("Топ - 10 чаще всего встречающихся слов");
        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
        for(int i=0;i<10;i++) {
            Map.Entry entry = itr.next();
            System.out.println("Слово \""+ entry.getKey()+"\" встречается "+entry.getValue()+" раз");
        }
    }
}