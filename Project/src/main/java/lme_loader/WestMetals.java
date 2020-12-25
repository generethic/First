package lme_loader;

import basics.ParseDateForCurrencies;
import basics.PatternsForParseWestMetals;
import basics.WestMetalsValues;
import dates_сonvertion.ConvertArrayToListOfDates;
import interfaces.IGetInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WestMetals extends GetMetalsInformation implements IGetInformation {
    //flag определяет как необходимо будет формировать даты (каждую конкретно, или диапазон от ранней к поздней дате
    private boolean flag;
    //это список с данными, согласно которых будут получены данные
    private List<String> list = new LinkedList<>();
    //экземпляр класса Object, по которому происходит синхронизация
    private final Object lock = new Object();
    //переменная, отвечающая за enum,который будет передаваться в конструктор класса
    private WestMetalsValues values;

    public WestMetals(boolean flag, WestMetalsValues values) {
        this.flag = flag;
        this.values = values;
    }
    //метод родительского класса, собирающий данные с сайта, согласно переданных дат
    @Override
    public LinkedHashMap<String, Double> getInformation(LocalDate... dates) {
        String URL = "https://www.westmetall.com/en/markdaten.php?action=table&field=" + values.getName();
        synchronized (lock) {
            List<LocalDate> listLocalDates;
            listLocalDates = ConvertArrayToListOfDates.getTotalDates(flag, dates);
            for (LocalDate listLocalDate : listLocalDates)
                list.add(listLocalDate.format(DateTimeFormatter.ofPattern(ParseDateForCurrencies.DATE_PATTERN_1.getName())));
        }
        return loadResultsToMap(URL);
    }
    //метод, который с помощью JSOUP преобразовывает строку,загруженную по ссылке в карту с итоговыми значениями
    private LinkedHashMap<String, Double> loadResultsToMap(String link) {
        Document doc = Jsoup.parse(handleFromUrl(link));
        int count = 0;
        String date = null;
        Double value = null;
        LinkedHashMap<String, Double> mapWithRates = new LinkedHashMap<>();
        LinkedHashMap<String, Double> mapResult = new LinkedHashMap<>();
        if (doc != null) {
            Elements rows = doc.select(PatternsForParseWestMetals.PATTERN1.getName());
            if (rows != null) {
                for (org.jsoup.nodes.Element row : rows) {
                    Elements columns = row.select(PatternsForParseWestMetals.PATTERN2.getName());
                    for (org.jsoup.nodes.Element column : columns) {
                        ++count;
                        if (count == 1) {
                            date = column.text().replace(".", "");
                            date = reworkDate(ParseDateForCurrencies.DATE_PATTERN_1.getName(), ParseDateForCurrencies.DATE_PATTERN_3.getName(), date);
                        }
                        if (count == 2) {
                            String result = column.text().replace(",", "");
                            value = Double.parseDouble(result);
                        }
                        if (count == 3) {
                            mapWithRates.putIfAbsent(date, value);
                            for (Map.Entry<String, Double> entry : mapWithRates.entrySet()) {
                                for (String s : list) {
                                    if (entry.getKey().equals(s)) {
                                        mapResult.put(entry.getKey(), entry.getValue());
                                    }
                                }
                            }
                            count = 0;
                            break;
                        }
                    }
                }
            }
        }
        return mapResult;
    }
    //метод, отвечающий за правильное преобразование дат в соответствии с шаблонами, допустимыми на сайте банка,если нужно получить диапазон дат
    private String reworkDate(String pattern1,String pattern2,String date) {
        String word = null;
        try {
            word = new SimpleDateFormat(pattern1).format(new SimpleDateFormat(pattern2, Locale.ENGLISH).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return word;
    }
}