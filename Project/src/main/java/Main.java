import basics.ValuesCodes;
import basics.WestMetalsValues;
import currency_loader.LoadInfoFromCBR;
import currency_loader.LoadInfoFromNBRB;
import export_to_File.ExportToCSV;
import export_to_File.ExportToDoc;
import interfaces.IExport;
import interfaces.IGetInformation;
import lme_loader.WestMetals;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        String destinationCsv = "resCSV.csv";
        String destinationDoc = "resDoc.docx";
        IExport exportToCSV = new ExportToCSV();
        IExport exportToDoc = new ExportToDoc();

        LocalDate date1 = LocalDate.of(2020,7,13);
        LocalDate date2 = LocalDate.of(2019,9,24);
        LocalDate date3 = LocalDate.of(2018,1,11);
        LocalDate date4 = LocalDate.of(2017,4,4);
        LocalDate date5 = LocalDate.of(2020,12,22);

        IGetInformation aluminum = new WestMetals(flag, WestMetalsValues.Aluminum);
        IGetInformation copper = new WestMetals(flag, WestMetalsValues.Copper);
        IGetInformation cbr_Usd = new LoadInfoFromCBR(flag, ValuesCodes.USD);
        IGetInformation cbr_Eur = new LoadInfoFromCBR(flag, ValuesCodes.EUR);
        IGetInformation nbrb_Usd = new LoadInfoFromNBRB(flag, ValuesCodes.USD);
        IGetInformation nbrb_Eur= new LoadInfoFromNBRB(flag, ValuesCodes.EUR);
        IGetInformation nbrb_Rub= new LoadInfoFromNBRB(flag, ValuesCodes.RUB);

        LinkedHashMap<String, Double> aluminumMap = aluminum.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> copperMap = copper.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> rub_USD = cbr_Usd.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> rub_EUR = cbr_Eur.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> byn_USD = nbrb_Usd.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> byn_EUR = nbrb_Eur.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap<String, Double> byn_RUB = nbrb_Rub.getInformation(date1,date2,date3,date4,date5);
        LinkedHashMap[] arrayMap = new LinkedHashMap[]{aluminumMap, copperMap, rub_USD, rub_EUR, byn_USD, byn_EUR, byn_RUB};

        exportToCSV.export(arrayMap,destinationCsv);
        exportToDoc.export(arrayMap,destinationDoc);

//        aluminumMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        copperMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        rub_USD.forEach((k,v)-> System.out.println(k+"-"+v));
//        rub_EUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        byn_USD.forEach((k,v)-> System.out.println(k+"-"+v));
//        byn_EUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        byn_RUB.forEach((k,v)-> System.out.println(k+"-"+v));
    }
}
