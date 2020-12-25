package basics;
/* enum c указанием значний, которые будут использоваться в классе WestMetals для получения итоговый ссылки с таблицей результатов
для металлов: медь(Copper) и алюминий (Aluminum)
   */
public enum WestMetalsValues {
    Aluminum("LME_Al_cash"),
    Copper("LME_Cu_cash");
    private String name;
    WestMetalsValues(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
