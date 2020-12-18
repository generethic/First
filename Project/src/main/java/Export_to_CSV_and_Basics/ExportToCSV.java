package Export_to_CSV_and_Basics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
/*
    Класс, отвечающий за экпорт в CSV
 */
public class ExportToCSV {
    public void export(LinkedHashMap[] arrayMap) {
        String eol = System.getProperty("line.separator");
        try (Writer writer = new FileWriter("result.csv")) {
            for (LinkedHashMap linkedHashMap : arrayMap) {
                LinkedHashMap<String,Double> map = linkedHashMap;
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                    writer.append(entry.getKey())
                            .append('-')
                            .append(String.valueOf(entry.getValue()))
                            .append(eol);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
