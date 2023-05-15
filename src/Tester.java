public class Tester {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket("New York Stock Exchange", "NYSE");
        Stock stock = new Stock("Apple", "AAPL", 100.00, 3.2, 1000000, 10000);
        stockMarket.addStock(stock);
        User balancedInvestor = new BalancedInvestor(stockMarket, "Cool Person",
                1000000);
        stock.update(97.7, 2.3, 274242, 252343224);
    }
}