package enums;

public enum WeekendPrintProductsEnums{
    WEEKEND_STANDARD_DIGITAL("Weekend Print + Standard Digital"),
    WEEKEND_PRINT("Weekend Print", "£14.74 per month"),
    WEEKEND_PREMIUM_DIGITAL("Weekend Print + Premium Digital", "£55.42 per month");
    private String weekendPrintTitle;
    private String price;

    private WeekendPrintProductsEnums(String weekendPrintTitle, String price) {
        this.weekendPrintTitle = weekendPrintTitle;
        this.price = price;
    }

    private WeekendPrintProductsEnums(String weekendPrintTitle) {
        this.weekendPrintTitle = weekendPrintTitle;
    }
    public String getTitle() { return weekendPrintTitle; }
    public String getPrice() {
        return price;
    }
}
