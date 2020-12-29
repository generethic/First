package currency_loader;

import basics.ParseDateForCurrencies;
import basics.ValuesCodes;
import dates_сonvertion.ConvertArrayToListOfDates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс в котором определены методы для работы с сайтом nbrb.by
 */
public class LoadInfoFromNBRB extends AbstractTemplateForDefiningBanks {
    /** Свойство - значение типа enum*/
    private ValuesCodes values;
    /** Свойство - замок, по которому будет происходить синхронизация*/
    private final Object lock = new Object();
    /** Свойство - шаблон, отвечающий за получение даты из массива, полученного с сайта nbrb.by*/
    private String patternForm1 = ParseDateForCurrencies.DATE_PATTERN_1.getName();
    /** Свойство - шаблон, отвечающий за получение даты из массива, полученного с сайта nbrb.by*/
    private String patternForm2 = ParseDateForCurrencies.DATE_PATTERN_2.getName();
    /** Свойство - шаблон, отвечающий за получение даты из массива, полученного с сайта nbrb.by*/
    private Pattern pattern = Pattern.compile("\\d+\\-\\d*\\-\\d+");
    /** Свойство - шаблон, отвечающий за получение даты из массива, полученного с сайта nbrb.by*/
    private Pattern pattern1 = Pattern.compile("\\d\\.\\d+");
    /** Создает новый объект
     @param flag используется реализация родительского класса
     @param values Enum, содержащий валюту с кодом,поддерживающимся сайтом nbrb.by
     @see LoadInfoFromNBRB (boolean, ValuesCodes )
     */
    public LoadInfoFromNBRB(boolean flag, ValuesCodes values) {
        super(flag);
        this.values = values;
    }
    /** Метод, собирающий данные с сайта cbr.ru, согласно переданных дат
     @param dates - передаваемые даты
     */
    @Override
    public LinkedHashMap<String,Double> getInformation(LocalDate...dates) {
        String URL;
        synchronized (lock) {
            List<LocalDate> list = ConvertArrayToListOfDates.getTotalDates(isFlag(), dates);
            LinkedHashMap<String, Double> map;
            if (isFlag()) {
                String start = reworkDate(list.get(0));
                String end = reworkDate(list.get(list.size() - 1));
                URL = ("https://www.nbrb.by/API/ExRates/Rates/Dynamics/" + values.getNameNBRB() + "?startDate=" + start + "&endDate=" + end).replaceAll("-", "/");
                String[] datesArray = getCurrencyArray(URL);
                map = getCurrency(datesArray);
            } else {
                List<String> newList = new LinkedList<>();
                for (LocalDate localDate : list) {
                    String date = reworkDate(localDate);
                    URL = ("https://www.nbrb.by/api/exrates/rates/" + values.getNameNBRB() + "?ondate=" + date).replaceAll("-", "/");
                    String[] datesArray = getCurrencyArray(URL);
                    newList.add(Arrays.toString(datesArray));
                }
                String[] array = newList.toArray(new String[0]);
                map = getCurrency(array);
            }
            return map;
        }
    }
    /** Метод,вызывающий метод matchr для поисхка совпадений в полученном массиве
     @param array - передаваемый методу массив с данными
     @return Карта с данными( дата и курс валюты)
     */
    private LinkedHashMap<String,Double> getCurrency(String[] array) {
        return matcher(pattern,pattern1,array);
    }
    /** Метод, который раздяет строки в массив, согласно шаблона для сайта нац. банка РБ
     @param word - то, что полученно во время "перебора" сайта
     @return Массив строк
     */
    public String[] split(String word) {
        return word.split("}");
    }
    /** Метод, в котором согласно шаблонов и просходит поисх
     @param pattern1 - передаваемый методу шаблон
     @param pattern2 - передаваемый методу шаблон
     @param array - передаваемый методу массив с данными
     @return Карта с данными( дата и курс валюты)
     */
    private LinkedHashMap<String,Double> matcher(Pattern pattern1,Pattern pattern2,String[] array) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        for (String s : array) {
            Matcher matcher = pattern1.matcher(s);
            Matcher matcher1 = pattern2.matcher(s);
            if(matcher.find() && matcher1.find()) {
                String date = matcher.group();
                date = reworkDate(patternForm1, patternForm2,date);
                String value = matcher1.group().replace(",",".");
                map.putIfAbsent(date, Double.valueOf(value));
            }
        }
        return map;
    }
    /** Метод, отвечающий за правильное преобразование дат в соответствии с шаблонами, допустимыми на сайте банка
     @param date - передаваемые даты
     @return Дата в виде строки
     */
    protected String reworkDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParseDateForCurrencies.DATE_PATTERN_5.getName());
        return date.format(formatter);
    }
    /** Метод, отвечающий за правильное преобразование дат в соответствии с шаблонами, допустимыми на сайте банка,если необходимо получить диапазон дат
     @param date - передаваемые даты
     @return Дата в виде строки
     */
    private String reworkDate(String pattern1, String pattern2, String date) {
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern2));
        return date1.format(DateTimeFormatter.ofPattern(pattern1));
    }
}