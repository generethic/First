package Currency_Parsers;

import Convert_Dates.ConvertArrayToDates;
import Convert_Dates.EditingDates;
import Export_to_CSV_and_Basics.BasicsAndPatterns;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
/*
Класс отвечает за работу с получением информации с сайта ЦБРФ и преобразование её в мапу
 */

public class CBR extends ParseUrlToResult {
    private String patternForm = BasicsAndPatterns.DATE_PATTERN_4.getName();
    private SimpleDateFormat toCBR = new SimpleDateFormat(patternForm);
    private LinkedHashMap<String,Double> getCurrency(String[] array){
        return matcher(pattern,pattern1,array);
    }
    private LinkedHashMap<String,Double> matcher(Pattern pattern1, Pattern pattern2, String[] array) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        for (String s : array) {
            matcher = pattern1.matcher(s);
            matcher1 = pattern2.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                String value = matcher1.group().replace(",",".");
                map.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return map;
    }

    public LinkedHashMap<String,Double> getInformation(String[] array, String basic) {
        list = new ConvertArrayToDates().getTotalDates(array);
        dayFirst = new EditingDates().edit(toCBR,list)[0];
        dayLast = new EditingDates().edit(toCBR,list)[1];
        newUrl = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1="+ dayFirst +"&date_req2="+ dayLast +"&VAL_NM_RQ="+basic;
        newUrl = newUrl.replaceAll("-","/");
        datesArray = getCurrencyArray(newUrl);
        map = getCurrency(datesArray);
        return map;
    }
}
