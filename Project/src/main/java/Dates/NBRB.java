package Dates;

import Other_Methods.BasicsAndPatterns;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
/*
Класс отвечает за работу с получением информации с сайта НБРБ и преобразование её в мапу
 */
public class NBRB extends ParseUrlToResult {

    private String patternForm1 = BasicsAndPatterns.DATE_PATTERN_5.getName();
    private String patternForm2 = BasicsAndPatterns.DATE_PATTERN_1.getName();
    private String patternForm3 = BasicsAndPatterns.DATE_PATTERN_2.getName();
    Pattern pattern = Pattern.compile("\\d+\\-\\d*\\-\\d+");
    Pattern pattern1 = Pattern.compile("\\d\\.\\d+");
    private SimpleDateFormat toNBRB = new SimpleDateFormat(patternForm1);
    public LinkedHashMap<String,Double> getInformation(String[] array, String basic) {
        list = new ConvertArrayToDates().getTotalDates(array);
        dayFirst = new EditingDates().edit(toNBRB,list)[0];
        dayLast = new EditingDates().edit(toNBRB,list)[1];
        newUrl = "https://www.nbrb.by/API/ExRates/Rates/Dynamics/"+basic+"?startDate="+ dayFirst +"&endDate="+ dayLast;
        datesArray = getCurrencyArray(newUrl);
        map = this.getCurrency(datesArray);
        return map;
    }
    protected LinkedHashMap<String,Double> getCurrency(String[] array) {
        return matcher(pattern,pattern1,array);
    }
    protected String[] split(String word) {
        return word.split("}");
    }

    protected LinkedHashMap<String,Double> matcher(Pattern pattern1,Pattern pattern2,String[] array) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        for (String s : array) {
            matcher = pattern1.matcher(s);
            matcher1 = pattern2.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                date = new EditingDates().edit(patternForm2,patternForm3,date);
                String value = matcher1.group().replace(",",".");
                map.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return map;
    }
}
