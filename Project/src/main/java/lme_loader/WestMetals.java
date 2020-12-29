package lme_loader;

import basics.ParseDateForCurrencies;
import basics.PatternsForParseWestMetals;
import basics.WestMetalsValues;
import dates_сonvertion.ConvertArrayToListOfDates;
import interfaces.IGetInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Класс, который занимается получением информации с сайта WestMetals.com
 */
public class WestMetals extends GetMetalsInformation implements IGetInformation {
    /** Свойство - флаг, отвечающие за то, за какие даты будет получена информация */
    private boolean flag;
    /** Свойство - список, в который будут добавляться данные с использованием свойства flag*/
    private List<String> list = new LinkedList<>();
        /** Свойство - замок, по которому будет происходить синхронизация*/
    private final Object lock = new Object();
    /** Свойство - значение типа enum*/
    private WestMetalsValues values;

    /** Создает новый объект
     @param flag Как будут передаваться даты
     @param values Enum, содержащий металлы
     @see WestMetals(boolean,WestMetalsValues)
     */
    public WestMetals(boolean flag, WestMetalsValues values) {
        this.flag = flag;
        this.values = values;
    }
    /** Получает карту с информацией о дате и стоимостью металла на жту дату
     @return Map<String,Double> содержащий итоговые значения
     */
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
    /** Метод, который преобразовывает строку,загруженную по ссылке в карту с итоговыми значениями
     @return Map<String,Double> содержащий значения по результатам преобразования строки
     */
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
    /** Метод, отвечающий за правильное преобразование дат в соответствии с шаблонами, допустимыми на сайте банка,если нужно получить диапазон дат
     @return преобразованная дата
     */
    //метод,
    private String reworkDate(String pattern1,String pattern2,String date) {
        LocalDate date1 = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern(pattern2, Locale.ENGLISH));
        return date1.format(DateTimeFormatter.ofPattern(pattern1));
    }
}