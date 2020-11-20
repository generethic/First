package HomeWork5;

import java.util.regex.*;

public class RegExSearch implements ISearchEngine {

    @Override
    public long search(String text, String word) {
        text = text.toLowerCase();
        word = word.toLowerCase();
        long result = 0;
        Pattern pattern = Pattern.compile("\\b"+word+"\\b");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            result++;
        }
        return result;
    }
}
