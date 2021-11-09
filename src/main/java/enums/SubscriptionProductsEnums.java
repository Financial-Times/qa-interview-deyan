package enums;

public enum SubscriptionProductsEnums {

    TRIAL("Trial"),
    DIGITAL("Digital", "38 € per month", "340 € for 1 year"),
    EPAPER("ePaper");
    private String title;
    private String price;
    private String promoPrice;

    private SubscriptionProductsEnums(String title) {
        this.title = title;
    }

    private SubscriptionProductsEnums(String title, String price, String promoPrice) {
        this.promoPrice = promoPrice;
        this.price = price;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getPromoPrice() {
        return promoPrice;
    }

}