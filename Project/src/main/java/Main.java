import Currency_Parsers.CBR;
import Currency_Parsers.NBRB;
import Export_to_CSV_and_Basics.BasicsAndPatterns;
import LME_Parser.GetInfoMetals;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        Export_to_CSV_and_Basics.ExportToCSV exportToCSV = new Export_to_CSV_and_Basics.ExportToCSV();

        String date1 = String.valueOf(LocalDate.of(2020,11, 1));
        String date2 = String.valueOf(LocalDate.of(2020,11,30));
        String[] array = {date1,date2};

        LinkedHashMap<String, Double> aluminumMap = new GetInfoMetals().getInformation(BasicsAndPatterns.Aluminum.getName(), array);
        LinkedHashMap<String, Double> copperMap = new GetInfoMetals().getInformation(BasicsAndPatterns.Copper.getName(), array);
        LinkedHashMap<String, Double> mapCourseUSD = new CBR().getInformation(array, BasicsAndPatterns.USD_CBR.getName());
        LinkedHashMap<String, Double> mapCourseEUR = new CBR().getInformation(array, BasicsAndPatterns.EUR_CBR.getName());
        LinkedHashMap<String, Double> mapCourseBYN_USD = new NBRB().getInformation(array, BasicsAndPatterns.USD_NBRB.getName());
        LinkedHashMap<String, Double> mapCourseBYN_EUR = new NBRB().getInformation(array, BasicsAndPatterns.EUR_NBRB.getName());
        LinkedHashMap<String, Double> mapCourseBYN_RUB = new NBRB().getInformation(array, BasicsAndPatterns.RUB_NBRB.getName());
        LinkedHashMap[] arrayMap = {aluminumMap, copperMap,mapCourseUSD,mapCourseEUR,mapCourseBYN_USD,mapCourseBYN_EUR,mapCourseBYN_RUB};

        exportToCSV.export(arrayMap);
//        aluminumMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        copperMap.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseUSD.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseEUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_USD.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_EUR.forEach((k,v)-> System.out.println(k+"-"+v));
//        mapCourseBYN_RUB.forEach((k,v)-> System.out.println(k+"-"+v));
    }
}
