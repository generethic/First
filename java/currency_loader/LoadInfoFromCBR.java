package currency_loader;

import basics.ParseDateForCurrencies;
import basics.ValuesCodes;
import dates_сonvertion.ConvertArrayToListOfDates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
Класс отвечает за работу с получением информации с сайта ЦБРФ и преобразование её в мапу
 */

public class LoadInfoFromCBR extends AbstractConnection {
    //переменная, отвечающая за enum,который будет передаваться в конструктор класса
    private ValuesCodes values;

    public LoadInfoFromCBR(boolean flag, ValuesCodes values) {
        super(flag);
        this.values = values;
    }
    //экземпляр класса Object, по которому происходит синхронизация
    private final Object lock = new Object();
    //переменная,отвечающая за получение даты со строки массива, полученного с сайта
    private Pattern pattern = Pattern.compile("\\d+\\.\\d*\\.\\d+");
    //переменная,отвечающая за получение курса со строки массива, полученного с сайта
    private Pattern pattern1 = Pattern.compile("\\d+\\,\\d+");
    //метод,создающий Map с полученными значениями дат и курсов
    private LinkedHashMap<String,Double> getCurrency(String[] array){
        return matcher(pattern,pattern1,array);
    }
    //метод,ищущий совпадения в переданном массиве согласно определённых шаблонов
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
    //переопределённый метод родительского класса, собирающий данные с сайта, согласно переданных дат
    @Override
    public LinkedHashMap<String,Double> getInformation(LocalDate...dates) {
        String URL;
        synchronized (lock) {
            List<LocalDate> list = ConvertArrayToListOfDates.getTotalDates(isFlag(), dates);
            LinkedHashMap<String, Double> map;
            if (isFlag()) {
                String start = reworkDate(list.get(0));
                String end = reworkDate(list.get(list.size() - 1));
                URL=("http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + start + "&date_req2=" + end + "&VAL_NM_RQ=" + values.getNameCBR()).replaceAll("-", "/");
                String[] datesArray = getCurrencyArray(URL);
                map = getCurrency(datesArray);
            } else {
                List<String> newList = new LinkedList<>();
                for (LocalDate localDate : list) {
                    String date = reworkDate(localDate);
                    URL = ("http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + date + "&date_req2=" + date + "&VAL_NM_RQ=" + values.getNameCBR()).replaceAll("-", "/");
                    String[] datesArray = getCurrencyArray(URL);
                    newList.add(Arrays.toString(datesArray));
                }
                String[] array = newList.toArray(new String[0]);
                map = getCurrency(array);
            }
            return map;
        }
    }
    //метод, который раздяет строки в массив, согласно шаблона для сайта нац. банка РФ
    @Override
    protected String[] split(String word) {
        return word.split("<Record Date=");
    }
    //метод, отвечающий за правильное преобразование дат в соответствии с шаблонами, допустимыми на сайте банка
    @Override
    protected String reworkDate(LocalDate date) {
        String olDate = date.toString();
        String newDate;
        SimpleDateFormat sdf = new SimpleDateFormat(ParseDateForCurrencies.DATE_PATTERN_2.getName());
        Date d = null;
        try {
            d = sdf.parse(olDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(ParseDateForCurrencies.DATE_PATTERN_4.getName());
        newDate = sdf.format(d);
        return newDate;
    }
}
