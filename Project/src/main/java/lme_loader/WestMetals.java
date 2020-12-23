package lme_loader;

import dates_сonvertion.ConvertArrayToListOfDates;
import basics.BasicsAndPatterns;
import interfaces.IGetInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WestMetals implements IGetInformation {
    private boolean flag;
    private String basic;
    private List<String> list = new LinkedList<>();
    private final Object lock = new Object();

    public WestMetals(boolean flag, String basic) {
        this.flag = flag;
        this.basic = basic;
    }

    @Override
    public LinkedHashMap<String, Double> getInformation(IGetInformation information, LocalDate... dates) {
        synchronized (lock) {
            String URL = "https://www.westmetall.com/en/markdaten.php?action=table&field=" + basic;
            List<LocalDate> listLocalDates;
            if (flag) {
                listLocalDates = new ConvertArrayToListOfDates().getTotalDates(true, dates);
                for (LocalDate listLocalDate : listLocalDates) {
                    list.add(listLocalDate.format(DateTimeFormatter.ofPattern(BasicsAndPatterns.DATE_PATTERN_1.getName())));
                }
            } else {
                listLocalDates = new ConvertArrayToListOfDates().getTotalDates(false, dates);
                for (LocalDate listLocalDate : listLocalDates) {
                    list.add(listLocalDate.format(DateTimeFormatter.ofPattern(BasicsAndPatterns.DATE_PATTERN_1.getName())));
                }
            }
            return getLmeValues(URL);
        }
    }

    private LinkedHashMap<String, Double> getLmeValues(String link) {

        LinkedHashMap<String, Double> map = new LinkedHashMap<>();
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(link).get();
            map = loadToMap(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private LinkedHashMap<String, Double> loadToMap(Document document) {
        int count = 0;
        String date = null;
        Double value = null;
        LinkedHashMap<String, Double> mapWithRates = new LinkedHashMap<>();
        LinkedHashMap<String, Double> mapResult = new LinkedHashMap<>();
        if (document != null) {
            org.jsoup.select.Elements rows = document.select(BasicsAndPatterns.PATTERN1.getName());
            if (rows != null) {
                for (org.jsoup.nodes.Element row : rows) {
                    org.jsoup.select.Elements columns = row.select(BasicsAndPatterns.PATTERN2.getName());
                    for (org.jsoup.nodes.Element column : columns) {
                        ++count;
                        if (count == 1) {
                            date = column.text().replace(".", "");
                            date = reworkDate(BasicsAndPatterns.DATE_PATTERN_1.getName(), BasicsAndPatterns.DATE_PATTERN_3.getName(), date);
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
