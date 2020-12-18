package Other_Methods;

import Dates.ConvertArrayToDates;
import Dates.EditingDates;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetInfoMetals {
    private LinkedHashMap<String,Double> mapWithRates;
    private LinkedList<String> list = new LinkedList<>();
    private LinkedHashMap<String,Double> mapWithResults;
    private int count = 0;
    private String date = null;
    private Double value = null;

    public LinkedHashMap<String,Double> getInformation(String URL, String[] datesArray) {
        initializeMaps(datesArray);
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(URL).get();
            if (doc != null) {
                org.jsoup.select.Elements rows = doc.select(BasicsAndPatterns.PATTERN1.getName());
                if (rows != null) {
                    for (org.jsoup.nodes.Element row : rows) {
                        org.jsoup.select.Elements columns = row.select(BasicsAndPatterns.PATTERN2.getName());
                        for (org.jsoup.nodes.Element column : columns) {
                            ++count;
                            if (count == 1) {
                                date = column.text().replace(".", "");
                                date = new EditingDates().edit(BasicsAndPatterns.DATE_PATTERN_1.getName(), BasicsAndPatterns.DATE_PATTERN_3.getName(), date);
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
                                            mapWithResults.put(entry.getKey(), entry.getValue());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapWithResults;
    }
    private void initializeMaps(String[] array) {
        mapWithResults = new LinkedHashMap<>();
        mapWithRates = new LinkedHashMap<>();
        List<LocalDate> listLocalDates = new ConvertArrayToDates().getTotalDates(array);
        for (LocalDate listLocalDate : listLocalDates) {
            list.add(listLocalDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }
    }
}
