package HomeWork5;

import java.util.*;

public class EasySearch implements ISearchEngine  {
    public String toWord(List<String> list) {
        String word = String.join(" ",list);
        return word;
    }

    @Override
    public long search(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        long result = 0;
        int enter = text.indexOf(word);
        while (enter != -1) {
            String txt1 = text.substring(enter,word.length());
            String txt2 = text.substring(enter,word.length()+1);
//            String txt3 =
//            if() {
//                result++;
//            }
            enter = text.indexOf(word, enter + word.length());
        }
        return result;
    }
}
