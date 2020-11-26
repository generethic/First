package HomeWork7;

import java.io.*;

public class Test {
    static String line = null;
    static String writePath = null;
    static String filename = File.separator+"currency.txt";
    public static void main(String[] args) {
        /*
          метод alfBankCurrency - Для вывода курсов с сайта банка Альфа-Банк
         */
//        alfBankCurrency();
        /*
          метод printRatesDates - Для вывода курсов с сайта Нац. Банка за период,укащзываемых в параметрах
         */
        printRatesDates(new NBRBLoader(),args);
//        printRates(new NBRBLoader());
        /*
          Разовое сохранение в файл
         */
//        saveToFileOnce("",new NBRBLoader());
        /*
          запись в файл с проверкой на наличие курсов ранее
         */
//        readAndWriteCurrency();
    }
    public static void saveToFileOnce(String path, SiteLoader loader) {
        double d1 = loader.load(SiteLoader.Currency.EUR);
        double d2 = loader.load(SiteLoader.Currency.RUB);
        double d3 = loader.load(SiteLoader.Currency.USD);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
            path = line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!path.isBlank()) {
            try {
                PrintWriter writer = new PrintWriter(path+filename);
                writer.println(SiteLoader.Currency.EUR+" is "+d1);
                writer.println(SiteLoader.Currency.RUB+" is "+d2);
                writer.println(SiteLoader.Currency.USD+" is "+d3);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            line = "c:\\Users\\sturchenko.a\\IdeaProjects\\HomeWork\\src\\HomeWork7\\";
            try {
                PrintWriter writer = new PrintWriter(line+filename);
                writer.println(SiteLoader.Currency.EUR+" is "+d1);
                writer.println(SiteLoader.Currency.RUB+" is "+d2);
                writer.println(SiteLoader.Currency.USD+" is "+d3);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        writePath = line;
    }

    public static void printRates(SiteLoader loader){
        /**
         * Для вывода курсов в консоль
         */
        System.out.println(loader.load(SiteLoader.Currency.EUR));
        System.out.println(loader.load(SiteLoader.Currency.RUB));
        System.out.println(loader.load(SiteLoader.Currency.USD));

    }

    public static void printRatesDates(NBRBLoader loader,String[] array) {
        loader.loadDates(SiteLoader.Currency.EUR,array);
    }
    public static void alfBankCurrency() {
        System.out.println(new AlfaLoader().load(CurrencyСodes.getEUR()));
        System.out.println(new AlfaLoader().load(CurrencyСodes.getRUB()));
        System.out.println(new AlfaLoader().load(CurrencyСodes.getUSD()));
    }

}
