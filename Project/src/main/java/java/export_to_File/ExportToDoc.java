package export_to_File;

import interfaces.IExport;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Класс служащий за преобразование полученных дат в формат .csv
 */
public class ExportToDoc implements IExport {
    /** Метод, в котором происходит запись передаваемых данных в файл с разрешением .docx по пути из переменной destination */
    public void export(List<LinkedHashMap<String,Double>> list, String destination) {
        XWPFDocument document = new XWPFDocument();
        try(FileOutputStream out = new FileOutputStream(new File(destination))) {
            XWPFTable table = document.createTable();
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("Дата");
            tableRowOne.addNewTableCell().setText("Стоимость");
            for (LinkedHashMap<String,Double> linkedHashMap : list) {
                for (Map.Entry<String, Double> entry : linkedHashMap.entrySet()) {
                    String k = entry.getKey();
                    Double v = entry.getValue();
                    XWPFTableRow tableRow = table.createRow();
                    tableRow.getCell(0).setText(k);
                    tableRow.getCell(1).setText(String.valueOf(v).replace(".", ","));
                }
            }
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}