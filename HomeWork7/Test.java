package HomeWork7;

import static HomeWork7.SiteLoader.*;
import static HomeWork7.SiteLoader.Currency.*;

public class Test {
    static boolean readyToWrite = true;
    public static void main(String[] args) {
        /*
          метод alfBankCurrency - Для вывода курсов с сайта банка Альфа-Банк
         */
        alfBankCurrency(new AlfaLoader());
        if(readyToWrite) {
            new ToFile().writeToFile(null, new AlfaLoader().toFile(new Currency[]{USD, EUR}));
        }
        /*
          метод printRatesDates - Для вывода курсов с сайта Нац. Банка за период,укащзываемых в параметрах
         */
//        printRatesDates(new NBRBLoader(),args);

        /*
          метод printRatesDates - Для вывода курсов с сайта Нац. Банка за текущую дату
         */
//        printRates(new NBRBLoader());


        /*
          Разовое сохранение в файл
         */

        /*
          запись в файл с проверкой на наличие курсов ранее
         */

    }

    public static void printRates(NBRBLoader loader){
        /*
          Для вывода курсов в консоль
         */
        loader.loadDates(USD);
        loader.loadDates(EUR);
        loader.loadDates(RUB);

    }

    public static void printRatesDates(NBRBLoader loader,String[] array) {
        loader.loadDates(USD,array);
        loader.loadDates(EUR,array);
        loader.loadDates(RUB,array);
    }
    public static void alfBankCurrency(AlfaLoader loader) {
        loader.load(USD);
        loader.load(EUR);
        loader.load(RUB);
    }

}
