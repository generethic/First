package HomeWork6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
