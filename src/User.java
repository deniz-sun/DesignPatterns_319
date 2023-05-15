import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class User {
    protected String name;
    protected StockMarket stockMarket;
    protected double investment_budget;
    protected Map<String, Integer> investment_portfolio;

    public User(StockMarket stockMarket, String name, double investment_budget){
        this.name = name;
        this.stockMarket = stockMarket;
        this.investment_budget = investment_budget;
        this.investment_portfolio = new HashMap<>();
    }
    public abstract int shouldBuyStock(Stock stock);

    public abstract int shouldSellStock(Stock stock);
    public void updateStocks(LinkedList<Stock> stocks){
        for (Stock stock: stocks){
            investment_portfolio.put(stock.getName(), 0);
        }
    }
    public void updateStockMarket(StockMarket stockMarket){
        this.stockMarket = stockMarket;
    }

}
