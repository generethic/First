package basics;

/* enum,включающий в себя значения дат, согласно которых будет проводиться форматирование
   для их дальнейшего использования в ссылках на сайты банков */

public enum ParseDateForCurrencies {

    DATE_PATTERN_1("dd.MM.yyyy"),
    DATE_PATTERN_2("yyyy-MM-dd"),
    DATE_PATTERN_3("dd MMM yyyy"),
    DATE_PATTERN_4("dd/MM/yyyy"),
    DATE_PATTERN_5("yyyy-M-d");

    private String name;
    ParseDateForCurrencies(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
