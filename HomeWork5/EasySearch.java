package HomeWork5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EasySearch implements ISearchEngine {
    final static Object lock = new Object();
    static List<Integer> indexes = new ArrayList<>();
    static List<String> words = new ArrayList<>();
    static int count = 0;
    long result = 0;
    @Override

    public long search(String text, String word) {
        text = text.toLowerCase();
        text = " "+text;
        word = word.toLowerCase();
        synchronized (lock) {
            result = make(text,word);
            clear();
            return result;
        }
    }
    static void findSections(String word) {
        int index = 0;
        int nextIndex = 0;
        index = word.indexOf(" ");
        indexes.add(index);
        int i = word.indexOf(" ",index+1);
        while (nextIndex!=-1) {
            indexes.add(i);
            i = word.indexOf(" ",i+1);
            nextIndex = i;
        }
    }
    static ArrayList<String> wordToCollection(List<Integer> list, String word) {
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size()-1; i++) {
            list1.add(word.substring(list.get(i),list.get(i+1)).trim());
        }
        return list1;
    }
    static int find(List<String> list,String word) {
        char[] words = word.toCharArray();
        for (String s : list) {
            char[] line = s.toCharArray();
            boolean b = Arrays.equals(words,line);
            if(b) {
                count++;
            }
        }
        return count;
    }
    static void clear() {
        indexes.clear();
        words.clear();
        EasySearch.count = 0;
    }
    static long make(String word,String wordToFind) {
        long itog = 0;
        findSections(word);
            words = wordToCollection(indexes, word);
            itog = find(words, wordToFind);
            clear();
            return itog;
    }
}