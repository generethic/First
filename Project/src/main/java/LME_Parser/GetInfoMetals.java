package LME_Parser;

import Convert_Dates.ConvertArrayToDates;
import Convert_Dates.EditingDates;
import Export_to_CSV_and_Basics.BasicsAndPatterns;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetInfoMetals implements IGetInformation {
    private LinkedHashMap<String,Double> mapWithRates;
    private LinkedList<String> list;
    private int count;
    private String date = null;
    private Double value = null;
    private String URL = null;

    @Override
    public LinkedHashMap<String,Double> getInformation(String metal, String[] datesArray) {
        URL = link+metal;
        initialize(datesArray);
        return getLmeValues(URL);
    }
    private void initialize(String[] array) {
        list = new LinkedList<>();
        mapWithRates = new LinkedHashMap<>();
        List<LocalDate> listLocalDates = new ConvertArrayToDates().getTotalDates(array);
        for (LocalDate listLocalDate : listLocalDates) {
            list.add(listLocalDate.format(DateTimeFormatter.ofPattern(BasicsAndPatterns.DATE_PATTERN_1.getName())));
        }
    }
    private LinkedHashMap<String, Double> getLmeValues(String link) {
        LinkedHashMap<String,Double> map = new LinkedHashMap<>();
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(link).get();
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
                                            map.put(entry.getKey(), entry.getValue());
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
        return map;
    }
}
