package HomeWork5;

import java.util.HashMap;

public class EasySearch implements ISearchEngine {
    @Override
    public long search(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        int count = 0;
        int index = -1 * word.length();

        do {
            if ((index = text.indexOf(word, index + word.length())) >= 0
                    && (index == 0 || !Character.isLetter(text.charAt(index - 1)))
                    && (index + word.length() >= text.length()
                    || !Character.isLetter(text.charAt(index + word.length())))) {
                count++;
            }
        } while (index >= 0);

        return count;
    }
}