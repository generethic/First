package currency_loader;

import dates_сonvertion.ConvertArrayToListOfDates;
import basics.BasicsAndPatterns;
import interfaces.IGetInformation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
Класс отвечает за работу с получением информации с сайта НБРБ и преобразование её в мапу
 */
public class LoadInfoFromNBRB extends ParseUrlToResult {

    public LoadInfoFromNBRB(boolean flag, String basic) {
        super(flag, basic);
    }
    private final Object lock = new Object();
    private String patternForm2 = BasicsAndPatterns.DATE_PATTERN_1.getName();
    private String patternForm3 = BasicsAndPatterns.DATE_PATTERN_2.getName();
    private Pattern pattern = Pattern.compile("\\d+\\-\\d*\\-\\d+");
    private Pattern pattern1 = Pattern.compile("\\d\\.\\d+");

    @Override
    public LinkedHashMap<String,Double> getInformation(IGetInformation information, LocalDate...dates) {
        synchronized (lock) {
            List<LocalDate> list = new ConvertArrayToListOfDates().getTotalDates(isFlag(), dates);
            LinkedHashMap<String, Double> map;
            if (isFlag()) {
                String start = reworkDate(list.get(0));
                String end = reworkDate(list.get(list.size() - 1));
                setNewUrl(("https://www.nbrb.by/API/ExRates/Rates/Dynamics/" + getBasic() + "?startDate=" + start + "&endDate=" + end).replaceAll("-", "/"));
                String[] datesArray = getCurrencyArray(getNewUrl());
                map = getCurrency(datesArray);
            } else {
                List<String> newList = new LinkedList<>();
                for (LocalDate localDate : list) {
                    String date = reworkDate(localDate);
                    setNewUrl(("https://www.nbrb.by/api/exrates/rates/" + getBasic() + "?ondate=" + date).replaceAll("-", "/"));
                    String[] datesArray = getCurrencyArray(getNewUrl());
                    newList.add(Arrays.toString(datesArray));
                }
                String[] array = newList.toArray(new String[0]);
                map = getCurrency(array);
            }
            return map;
        }
    }
    private LinkedHashMap<String,Double> getCurrency(String[] array) {
        return matcher(pattern,pattern1,array);
    }
    protected String[] split(String word) {
        return word.split("}");
    }

    private LinkedHashMap<String,Double> matcher(Pattern pattern1,Pattern pattern2,String[] array) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        for (String s : array) {
            Matcher matcher = pattern1.matcher(s);
            Matcher matcher1 = pattern2.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                date = reworkDate(patternForm2,patternForm3,date);
                String value = matcher1.group().replace(",",".");
                map.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return map;
    }
    protected String reworkDate(LocalDate date) {
        String olDate = date.toString();
        String newDate;
        SimpleDateFormat sdf = new SimpleDateFormat(BasicsAndPatterns.DATE_PATTERN_2.getName());
        Date d = null;
        try {
            d = sdf.parse(olDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(BasicsAndPatterns.DATE_PATTERN_5.getName());
        newDate = sdf.format(d);
        return newDate;
    }
    private String reworkDate(String pattern1, String pattern2, String date) {
        String word = null;
        try {
            word = new SimpleDateFormat(pattern1).format(new SimpleDateFormat(pattern2, Locale.ENGLISH).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return word;
    }
}