import Dates.CBR;
import Dates.NBRB;
import Other_Methods.BasicsAndPatterns;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class MainClass {
    public static void main(String[] args) {
        String URL = "https://www.westmetall.com/en/markdaten.php?action=table&field=";
        String url_Al = URL + BasicsAndPatterns.Aluminum.getName();
        String url_Cu = URL + BasicsAndPatterns.Copper.getName();

        LocalDate date = LocalDate.of(2020,11,7);
        LocalDate date2 = LocalDate.of(2020,11,27);
        String[] array = {date.toString(),date2.toString()};

        LinkedHashMap<String, Double> aluminumMap = new Other_Methods.GetInfoMetals().getInformation(url_Al, array);
        LinkedHashMap<String, Double> copperMap = new Other_Methods.GetInfoMetals().getInformation(url_Cu, array);
        LinkedHashMap<String, Double> mapCourseUSD = new CBR().getInformation(array, BasicsAndPatterns.USD_CBR.getName());
        LinkedHashMap<String, Double> mapCourseEUR = new CBR().getInformation(array, BasicsAndPatterns.EUR_CBR.getName());
        LinkedHashMap<String, Double> mapCourseBYN_USD = new NBRB().getInformation(array, BasicsAndPatterns.USD_NBRB.getName());
        LinkedHashMap<String, Double> mapCourseBYN_EUR = new NBRB().getInformation(array, BasicsAndPatterns.EUR_NBRB.getName());
        LinkedHashMap<String, Double> mapCourseBYN_RUB = new NBRB().getInformation(array, BasicsAndPatterns.RUB_NBRB.getName());
//        LinkedHashMap[] arrayMap = {aluminumMap, copperMap,mapCourseUSD,mapCourseEUR,mapCourseBYN_USD,mapCourseBYN_EUR,mapCourseBYN_RUB};
//        Other_Methods.ExportToCSV exportToCSV = new Other_Methods.ExportToCSV();
//        exportToCSV.export(arrayMap);
        aluminumMap.forEach((k,v)-> System.out.println(k+"-"+v));
        copperMap.forEach((k,v)-> System.out.println(k+"-"+v));
        mapCourseUSD.forEach((k,v)-> System.out.println(k+"-"+v));
        mapCourseEUR.forEach((k,v)-> System.out.println(k+"-"+v));
        mapCourseBYN_USD.forEach((k,v)-> System.out.println(k+"-"+v));
        mapCourseBYN_EUR.forEach((k,v)-> System.out.println(k+"-"+v));
        mapCourseBYN_RUB.forEach((k,v)-> System.out.println(k+"-"+v));
    }
}
