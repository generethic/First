package LME_Parser;

import java.util.LinkedHashMap;

public interface IGetInformation {
    String link = "https://www.westmetall.com/en/markdaten.php?action=table&field=";
    LinkedHashMap<String,Double> getInformation(String URL, String[] datesArray);
}
