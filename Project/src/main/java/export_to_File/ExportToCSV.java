package export_to_File;

import interfaces.IExport;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
/*
    Класс, отвечающий за экпорт в .csv
 */
public class ExportToCSV implements IExport {
    //метод, отвечающий за жкспорт данных в файл типа CSV
    public void export(LinkedHashMap[] arrayMap, String destination) {
        String separator = System.lineSeparator();
        try (Writer writer = new FileWriter(destination)) {
            for (LinkedHashMap linkedHashMap : arrayMap) {
                LinkedHashMap<String,Double> map = linkedHashMap;
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                    writer.append(entry.getKey())
                            .append(':')
                            .append(String.valueOf(entry.getValue()))
                            .append(separator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
