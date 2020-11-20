package HomeWork5;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static HomeWork5.Utils.*;

public class WarAndPeace {

    static String filename = "Война и Мир.txt"; //адрес для чтения с ПК
    private static String name = "HomeWork" + File.separator + "resources" + File.separator + "Война и мир_книга.txt"; //адрес для общего чтения (не знаю, почему не работает)
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        BufferedReader reader;
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            set = new HashSet<>();
            list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < s.length; i++) {
                    set.add(s[i]);
                    list.add(s[i]);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        /*
        originalSet - это коллекция с уникальными значениями
        */
        Set<String> originalSet = removeExcludeWords(set, new HashSet<>());
//
//      /*
//      onlyWordsSet - экто коллекция с повторяющимися значениями
//      */
        List<String> onlyWordsSet = removeExcludeWords(list, new ArrayList<>());
        /*
//      удаление пробелов,используя Predicate
//      */
//        Predicate<String> pred1 = item -> item.length() == 0;
//        Predicate<String> pred2 = item -> item.isBlank();
//        Predicate<String> pred3 = item -> item.equals(" ");
//        onlyWordsSet.removeIf(pred1.or(pred2.or(pred3)));
//
//        /*
//        Поиск слов,встречающихся в тексе, используя класс RegExSearch
//        */
        String wordForInterfaces = converseListToWord(onlyWordsSet); //перевод коллекции в строку
        String wordToFind1 = "ВоЙнА";
        String wordToFind2 = "И";
        String wordToFind3 = "МиР";
          RegExSearch regExSearch = new RegExSearch();
          EasySearch easySearch = new EasySearch();
          long resReg1 = searchWithClassesInterfaces(easySearch, wordForInterfaces, wordToFind1);
          long resReg2 = searchWithClassesInterfaces(easySearch, wordForInterfaces, wordToFind2);
          long resReg3 = searchWithClassesInterfaces(easySearch, wordForInterfaces, wordToFind3);
          System.out.println("Слово "+ wordToFind1 +" встречается: "+resReg1+" раз.");
          System.out.println("Слово "+ wordToFind2 +" встречается: "+resReg2+" раз.");
          System.out.println("Слово "+ wordToFind3 +" встречается: "+resReg3+" раз.");
          /*
          Поиск слов,встречающихся в тексте, используя класс EasySearch
          */
//          long resEasy1 = searchWithClassesInterfaces(easySearch, wordForInterfaces,"ВОЙНа");
//          long resEasy2 = searchWithClassesInterfaces(easySearch, wordForInterfaces,"И");
//          long resEasy3 = searchWithClassesInterfaces(easySearch, wordForInterfaces,"мир");
//          System.out.println(resEasy1);
//          System.out.println(resEasy2);
//          System.out.println(resEasy3);
//
//        /*
//        Метод, печатающий 10 чаще всего втречающихся слов
//        */
//        printMapRatedWords(sortByComparator(getMapWithQuantities(onlyWordsSet)));

        HashMap<String,Integer> map = map(4);
        searchByMultithreadingByLambda(easySearch,map, wordToFind1, wordToFind2, wordToFind3);
        searchByMultithreadingByClass(easySearch,map, wordToFind1, wordToFind2, wordToFind3);
    }
}