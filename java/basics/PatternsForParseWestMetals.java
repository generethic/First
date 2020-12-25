package basics;

/* enum,включающий в себя значения дат, на основании которых будет прповодиться форматирование полученного
   документа в классе WestMetals
   */
public enum PatternsForParseWestMetals {
    PATTERN1("tr"),
    PATTERN2("td");
    private String name;
    PatternsForParseWestMetals(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
