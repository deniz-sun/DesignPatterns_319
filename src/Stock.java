public class Stock {

    private String name;
    private String symbol;
    private double price;
    private double percentChange;
    private double volume;
    private double marketCap;

   public Stock(String name, String symbol, double price, double percentChange, double volume, double marketCap) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percentChange = percentChange;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public String toString(){
        return "--- Stock Information ---" +
                "\nName: " + name + " (" + symbol + ")" +
                "\nPrice: " + price + " TRY" +
                "\nPercent Change: " + percentChange +
                "\nVolume: " + volume +
                "\nMarket Cap: " + marketCap;
    }

    public void update(double v, double v1, int i, int i1) {
    }
}
