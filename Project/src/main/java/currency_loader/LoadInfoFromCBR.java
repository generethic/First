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
Класс отвечает за работу с получением информации с сайта ЦБРФ и преобразование её в мапу
 */

public class LoadInfoFromCBR extends ParseUrlToResult {

    public LoadInfoFromCBR(boolean flag, String basic) {
        super(flag, basic);
    }

    private final Object lock = new Object();
    private Pattern pattern = Pattern.compile("\\d+\\.\\d*\\.\\d+");
    private Pattern pattern1 = Pattern.compile("\\d+\\,\\d+");
    private LinkedHashMap<String,Double> getCurrency(String[] array){
        return matcher(pattern,pattern1,array);
    }
    private LinkedHashMap<String,Double> matcher(Pattern pattern1, Pattern pattern2, String[] array) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        for (String s : array) {
            Matcher matcher = pattern1.matcher(s);
            Matcher matcher1 = pattern2.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                String value = matcher1.group().replace(",",".");
                map.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return map;
    }
    @Override
    public LinkedHashMap<String,Double> getInformation(IGetInformation information, LocalDate...dates) {
        synchronized (lock) {
            List<LocalDate> list = new ConvertArrayToListOfDates().getTotalDates(isFlag(), dates);
            LinkedHashMap<String, Double> map;
            if (isFlag()) {
                String start = reworkDate(list.get(0));
                String end = reworkDate(list.get(list.size() - 1));
                setNewUrl(("http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + start + "&date_req2=" + end + "&VAL_NM_RQ=" + getBasic()).replaceAll("-", "/"));
                String[] datesArray = getCurrencyArray(getNewUrl());
                map = getCurrency(datesArray);
            } else {
                List<String> newList = new LinkedList<>();
                for (LocalDate localDate : list) {
                    String date = reworkDate(localDate);
                    setNewUrl(("http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + date + "&date_req2=" + date + "&VAL_NM_RQ=" + getBasic()).replaceAll("-", "/"));
                    String[] datesArray = getCurrencyArray(getNewUrl());
                    newList.add(Arrays.toString(datesArray));
                }
                String[] array = newList.toArray(new String[0]);
                map = getCurrency(array);
            }
            return map;
        }
    }

    @Override
    protected String[] split(String word) {
        return word.split("<Record Date=");
    }

    @Override
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
        sdf.applyPattern(BasicsAndPatterns.DATE_PATTERN_4.getName());
        newDate = sdf.format(d);
        return newDate;
    }
}
