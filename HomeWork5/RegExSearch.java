package HomeWork5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSearch implements ISearchEngine {
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
        Pattern pattern = Pattern.compile("\\b"+word+"\\b");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            result++;
        }
        System.out.println(result);
        return result;
    }
}
