package enums;

public enum Browser{
    FIREFOX("firefox"),
    CHROME("chrome"),
    IE10("ie10"),
    SAFARI("safari");
    private String browserName;

    private Browser(String browserName) {
        this.browserName = browserName;
    }
    public String getBrowserName() {
        return browserName;
    }
}