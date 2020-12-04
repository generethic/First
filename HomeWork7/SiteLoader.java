package HomeWork7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Реализация загрузчика сайтов
 */
public abstract class SiteLoader {

    public enum Currency{
        USD("145"),
        EUR("292"),
        RUB("298");

        private String id;

        Currency(String id) {
            this.id = id;
        }

        public String getId(){
            return this.id;
        }
    }

}
