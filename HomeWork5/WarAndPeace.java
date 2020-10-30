package HomeWork5;

import java.util.*;
import java.io.*;


public class WarAndPeace {
    public static void main(String[] args) {
        String filename = "Война и Мир.txt";
        File file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            Set<String> set = new HashSet<>();
            List<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    set.add(s[i]);
                    list.add(s[i]);
                }
            }
            Set<String> set1 = new HashSet<>();
            Set<String> set2 = removeZnaki(set,set1);
            List<String> list1 = new ArrayList<>();
            List<String> list2 = removeZnaki(list,list1);
            list2.removeIf((item)->item.length() == 0 || item.isBlank() || item.equals(" "));
            obnulenie(set,set1);
            obnulenie(list,list1);
            EasySearch easySearch = new EasySearch();
            String word = easySearch.toWord(list2);
            System.out.println(easySearch.search(word,"МИР"));
//            System.out.println("Список уникальных значений");
//            for (String s:set2) {
//                System.out.println(s);
//            }
//            System.out.println("Топ - 10 чаще всего встречающихся слов");
//            printMap(sortByComparator(getMapWithQuantities(list2)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Set<String> removeZnaki(Set<String> set,Set<String> set1) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(Character.isLetterOrDigit(c[i])) {
                    stringBuilder.append(c[i]);
                }
            }
            set1.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return set1;
    }
    private static List<String> removeZnaki(List<String> set,List<String> set1) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            char[] c = s.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if(Character.isAlphabetic(c[i])) {
                    stringBuilder.append(c[i]);
                }
            }
            set1.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return set1;
    }

    private static void obnulenie(Collection<?> c1,Collection<?> c2) {
        c1 = null;
        c2 = null;
    }

    private static Map<String,Integer> vMap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), Collections.frequency(list,list.get(i)));
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))){
                    list.remove(j);
                    j--;
                }
            }
        }
        return map;
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
    private static void printMap(Map<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
        for(int i=0;i<10;i++) {
            Map.Entry entry = itr.next();
            System.out.println("Слово "+ entry.getKey()+" встречается "+entry.getValue()+" раз");
        }
    }
}