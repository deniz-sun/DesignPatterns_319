import java.util.LinkedList;

public class StockMarket {

    private String name;
    private String symbol;
    private LinkedList<Stock> stocks;
    private boolean isOpen;
    private StockMarketState state;

    public StockMarket(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.stocks = new LinkedList<>();
        this.isOpen = false;
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

    @Override
    public String toString() {
        return "--- Stock Market Information ---" +
                "\nName = " + name + "(" + symbol + ")" +
                "\nStocks = " + stocks;
    }
    public boolean open(){
        if(!isOpen){
            isOpen = true;
            System.out.println("Stock market "+ name + " is now open.");
            return true;
        }
        else{
            System.out.println("Stock market "+ name + " is already open.");
            return false;
        }
    }
    public boolean close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Stock market " + name + " is now closed.");
            return true;
        }
        else {
            System.out.println("Stock market " + name + " is already closed.");
            return false;
        }
    }
    public boolean buyStock(Stock stock, int amount, User user){
        double stockCost;
        if (isOpen && stocks.contains(stock)){
            stockCost = stock.getPrice() * amount;
            if (user.investment_budget >= stockCost){
                user.investment_budget -= stockCost;

                System.out.println(user.name + " has made a transaction to buy " + amount + " shares of "
                                    + stock.getName() + "(" + stock.getSymbol() + ") while the Stock Market is open.");
                System.out.println("Original cost: " + stockCost + " TRY.");


                user.shouldBuyStock(stock);
                user.updateStocks(stocks);
                System.out.println("Successfully purchased the stock.");
                return true;
            }
            else{
                System.out.println("Budget is not enough. Cannot purchase the stock.");
                return false;
            }
        }
        else{
            System.out.println("Cannot find the requested stock.");
            return false;
        }
    }
    public boolean sellStock(Stock stock, int amount, User user){
        double stockCost;
        if(stocks.contains(stock)){
            if (stock.getVolume() >= amount){
                stockCost = stock.getPrice() * amount;
                user.investment_budget += stockCost;
                stock.setVolume(stock.getVolume() - amount);
                return true;
            }
            else{
                System.out.println("Stock amount is not enough. Cannot sell.");
                return false;
            }
        }
        else{
            System.out.println("Cannot find the requested stock.");
            return false;
        }
    }

    public boolean addStock(Stock stock){
        if (isOpen) {
            return stocks.add(stock);
        }
        return false;
    }
    public boolean removeStock(Stock stock){
        if (isOpen) {
            return stocks.remove(stock);
        }
        return false;
    }

}
