package enums;

public enum CountryEnums{
    BULGARIA("Bulgaria", "€249 per year"),
    UNITED_KINGDOM("United Kingdom");
    private String country;
    private String price;
    private CountryEnums(String country) {
        this.country = country;
    }
    private CountryEnums(String country, String price){ this.country = country;
        this.price = price;}
    public String getCountry() {
        return country;
    }
    public String getPrice() {
        return price;
    }
}
