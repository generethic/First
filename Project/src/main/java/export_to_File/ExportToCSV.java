package export_to_File;

import interfaces.IExport;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Класс служащий за преобразование полученных дат в формат .csv
 */
public class ExportToCSV implements IExport {
    /** Метод, в котором происходит запись передаваемых данных в файл с разрешением .csv по пути из переменной destination */
    public void export(List<LinkedHashMap<String,Double>> list, String destination) {
        String separator = System.lineSeparator();
        try (Writer writer = new FileWriter(destination)) {
            for (LinkedHashMap<String, Double> linkedHashMap : list) {
                for (Map.Entry<String, Double> entry : (linkedHashMap).entrySet()) {
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
