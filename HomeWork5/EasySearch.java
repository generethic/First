package HomeWork5;

import java.util.*;

public class EasySearch implements ISearchEngine  {
    public String toWord(List<String> list) {
        String word = String.join(" ",list);
        StringBuilder stringBuilder = new StringBuilder(word);
        return stringBuilder.append(" ").toString();
    }

    @Override
    public long search(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        long result = 0;
        int indexOf = text.indexOf(" ");
        String newText = text.substring(0,indexOf);
        try {
            while (text.length() != 0) {
                if (newText.trim().equals(word)) {
                    result++;
                }
                text = text.substring(newText.length());
                indexOf = text.indexOf(" ", 1);
                newText = text.substring(0, indexOf);
            }
            System.out.println(result);
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println(result);
        }
        return result;
    }
}