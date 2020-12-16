public enum Basics {
    EUR_NBRB("292"),
    EUR_CBR("R01239"),
    USD_NBRB("145"),
    RUB_NBRB("298"),
    USD_CBR("R01235"),
    Aluminum("LME_Al_cash"),
    Copper("LME_Cu_cash");
    private String name;
    Basics(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
