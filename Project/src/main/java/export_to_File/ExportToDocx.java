package export_to_File;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
/*
    Класс, отвечающий за экпорт в .docx
 */
public class ExportToDocx {

    public void export(LinkedHashMap[] arrayMap) {
        String direction = "result.docx";
        XWPFDocument document = new XWPFDocument();
        try {
            FileOutputStream out = new FileOutputStream(new File(direction));
            XWPFTable table = document.createTable();
            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("Дата");
            tableRowOne.addNewTableCell().setText("Стоимость");
            for (LinkedHashMap linkedHashMap : arrayMap) {
                linkedHashMap.forEach((k,v)-> {
                    XWPFTableRow tableRow = table.createRow();
                    tableRow.getCell(0).setText((String) k);
                    tableRow.getCell(1).setText(String.valueOf(v).replace(".",","));
                });
            }
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
