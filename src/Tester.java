public class Tester {
    public static void main(String[] args) {
        Stock stock = new Stock("Apple", "AAPL", 100.00, -3.28, 1001513.0, 1024150.0);
        System.out.println(stock);
        Stock microsoft = new Stock("Microsoft", "MSFT", 123.4, 3.28, 1001513.0, 1024150.0);
        StockMarket stockMarket = new StockMarket("New York Stock Exchange", "NYSE");
        stockMarket.open();
        stockMarket.addStock(stock);
        stockMarket.addStock(microsoft);
        System.out.println(stockMarket);
        StockMarket emptymarket = new StockMarket("NASDAQ", "NASDAQ");
        System.out.println(emptymarket);
        AggressiveInvestor jeff = new AggressiveInvestor(stockMarket, "Beff Jezos", 990023.21);

        //962 vs 802
        stockMarket.buyStock(microsoft, 802, jeff);
        stockMarket.sellStock(microsoft, 802, jeff);






    }
}
