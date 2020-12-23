import currency_loader.LoadInfoFromCBR;
import currency_loader.LoadInfoFromNBRB;
import basics.BasicsAndPatterns;
import interfaces.IGetInformation;
import lme_loader.WestMetals;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        boolean flag = false;
        export_to_File.ExportToCSV exportToCSV = new export_to_File.ExportToCSV();
        export_to_File.ExportToDocx exportToDocx = new export_to_File.ExportToDocx();

        LocalDate date1 = LocalDate.of(2020,6,13);
        LocalDate date2 = LocalDate.of(2020,9,24);
        LocalDate date3 = LocalDate.of(2017,2,1);
        LocalDate date4 = LocalDate.of(2018,4,4);

        IGetInformation aluminum = new WestMetals(flag, BasicsAndPatterns.Aluminum.getName());
        IGetInformation copper = new WestMetals(flag,BasicsAndPatterns.Copper.getName());
        IGetInformation cbr_Usd = new LoadInfoFromCBR(flag,BasicsAndPatterns.USD_CBR.getName());
        IGetInformation cbr_Eur = new LoadInfoFromCBR(flag,BasicsAndPatterns.EUR_CBR.getName());
        IGetInformation nbrb_Usd = new LoadInfoFromNBRB(flag,BasicsAndPatterns.USD_NBRB.getName());
        IGetInformation nbrb_Eur= new LoadInfoFromNBRB(flag,BasicsAndPatterns.EUR_NBRB.getName());
        IGetInformation nbrb_Rub= new LoadInfoFromNBRB(flag,BasicsAndPatterns.RUB_NBRB.getName());

        LinkedHashMap<String, Double> aluminumMap = aluminum.getInformation(aluminum,date1,date2,date3,date4);
        LinkedHashMap<String, Double> copperMap = copper.getInformation(copper,date1,date2,date3,date4);
        LinkedHashMap<String, Double> mapCourseUSD = cbr_Usd.getInformation(cbr_Usd,date1,date2,date3,date4);
        LinkedHashMap<String, Double> mapCourseEUR = cbr_Eur.getInformation(cbr_Eur,date1,date2,date3,date4);
        LinkedHashMap<String, Double> mapCourseBYN_USD = nbrb_Usd.getInformation(nbrb_Usd,date1,date2,date3,date4);
        LinkedHashMap<String, Double> mapCourseBYN_EUR = nbrb_Eur.getInformation(nbrb_Eur,date1,date2,date3,date4);
        LinkedHashMap<String, Double> mapCourseBYN_RUB = nbrb_Rub.getInformation(nbrb_Rub,date1,date2,date3,date4);
        LinkedHashMap[] arrayMap = {aluminumMap,copperMap,mapCourseUSD,mapCourseEUR,mapCourseBYN_USD,mapCourseBYN_EUR,mapCourseBYN_RUB};

        exportToCSV.export(arrayMap);
        exportToDocx.export(arrayMap);

//        aluminumMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        copperMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseUSD.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseEUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_USD.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_EUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_RUB.forEach((k,v)-> System.out.println(k+"-"+v));
    }
}
