package HomeWork7;


import static HomeWork7.SiteLoader.Currency.*;

public class Test {
    private static boolean readyToWrite = true;
    private static SiteLoader.Currency[] currencies;
    public static void main(String[] args) {
        currencies = new SiteLoader.Currency[]{EUR,USD,RUB};
        /*
          метод alfBankCurrency - Для вывода курсов с сайта банка Альфа-Банк
         */
        alfBankCurrency(new AlfaLoader(),currencies);
        /*
        метод printRatesDates - Для вывода курсов с сайта Нац. Банка за период,укащзываемых в параметрах
         */
//        printRatesDates(new NBRBLoader(),args,currencies);
        /*
          метод printRatesDates - Для вывода курсов с сайта Нац. Банка за текущую дату
         */
//        printRates(new NBRBLoader(),currencies);

        if(readyToWrite) {
            new ToFile().writeToFile(null, new AlfaLoader());
//            new ToFile().writeToFile(null,new NBRBLoader());
        }

    }

    public static void printRates(NBRBLoader loader,SiteLoader.Currency[] currencies){
        /*
          Для вывода курсов в консоль
         */
        loader.loadDates(currencies);


    }

    public static void printRatesDates(NBRBLoader loader,String[] array,SiteLoader.Currency[] currencies) {
        loader.loadDates(array,currencies);
    }
    public static void alfBankCurrency(AlfaLoader loader, SiteLoader.Currency[] currencies) {
        loader.load(currencies);
    }

}
