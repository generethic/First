package Export_to_CSV_and_Basics;

public enum BasicsAndPatterns {
    EUR_NBRB("292"),
    EUR_CBR("R01239"),
    USD_NBRB("145"),
    RUB_NBRB("298"),
    USD_CBR("R01235"),
    Aluminum("LME_Al_cash"),
    Copper("LME_Cu_cash"),
    DATE_PATTERN_1("dd.MM.yyyy"),
    DATE_PATTERN_2("yyyy-MM-dd"),
    DATE_PATTERN_3("dd MMM yyyy"),
    DATE_PATTERN_4("dd/MM/yyyy"),
    DATE_PATTERN_5("yyyy-M-d"),
    PATTERN1("tr"),
    PATTERN2("td");
    private String name;
    BasicsAndPatterns(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
