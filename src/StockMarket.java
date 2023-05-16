import java.util.Iterator;
import java.util.LinkedList;

public class StockMarket {

    private String name;
    private String symbol;
    private LinkedList<Stock> stocks;
    private boolean isOpen;
    StockMarketState state;
    StockMarketState openState;
    StockMarketState closeState;
    StockMarketState highVolatileState;
    StockMarketState lowVolatileState;

    public StockMarket(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.stocks = new LinkedList<>();
        this.isOpen = false;
        openState = new OpenState(this);
        closeState = new CloseState(this);
        highVolatileState = new HighVolatileState(this);
        lowVolatileState = new LowVolatileState(this);

        state = closeState;
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
       StringBuilder result = new StringBuilder();
       result.append("--- Stock Market Information ---" + "\nName = ").append(name).append(" (").append(symbol).append(")").append("\nStocks: \n");
       if (stocks.isEmpty())
           result.append("No stocks available.");
       for (Stock stock : stocks) {
           result.append(stock.toString()).append("\n");
       }
       return result.toString();
    }
    public boolean open(){
        state = openState;
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
        state = closeState;
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
        if (isOpen && stocks.contains(stock) && user.canMakeTransaction()){
            ///////////////////////DOESNT WORK
            double totalCost = stock.getPrice() * amount + state.calculateTransactionFee(stock.getPrice(), amount);
            if (user.investment_budget >= totalCost){
                user.investment_budget -= totalCost;
                user.investment_portfolio.put(stock.getName(), 0);
                int amountToBuy = (int) (user.investment_budget / totalCost);

                //amount and amountobuy ??????????????
                System.out.println(user.name + " has made a transaction to buy " + amount  + " shares of "
                                    + stock.getName() + "(" + stock.getSymbol() + ") while the Stock Market is open.");
                System.out.println("Original cost: " + stock.getPrice() * amount + " TRY.");
                System.out.println("Fee: " + (100 * state.getTransactionFee() )+ " %");
                System.out.printf("%s%.3f%s", "Total cost: ", totalCost, " TRY.\n");
                System.out.printf("%s%s%.3f%s", user.name, " has ", user.investment_budget, " TRY left.\n");
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
        else if (!isOpen){
            System.out.println(user.name + " has made a transaction to buy " + amount  + " shares of "
                    + stock.getName() + "(" + stock.getSymbol() + ") while the Stock Market is closed.");
            System.out.println("Unable to process the transaction.");
            return false;
        }
        else if(!user.canMakeTransaction()){
            System.out.println(user.name + " tried to make their " + user.transactionCount + "th transaction of the day however as an " + user.getUserType() + " they are only allowed up to " + user.getMaxTransactionsPerDay());
            return false;
        }
        else{
            System.out.println("Cannot find the requested stock.");
            return false;
        }
    }
    public boolean sellStock(Stock stock, int amount, User user){
        double totalCost = stock.getPrice() * amount - state.calculateTransactionFee(stock.getPrice(), amount);

        if(stocks.contains(stock) && isOpen && user.canMakeTransaction()){
            if (stock.getVolume() >= amount){
                user.investment_budget += totalCost;
                stock.setVolume(stock.getVolume() - amount);
                System.out.println(user.name + " has made a transaction to sell " + amount  + " shares of "
                        + stock.getName() + "(" + stock.getSymbol() + ") while the Stock Market is open.");
                System.out.println("Original cost: " + stock.getPrice() * amount + " TRY.");
                System.out.println("Fee: " + (100 * state.getTransactionFee() )+ " %");
                System.out.printf("%s%.3f%s", "Total cost: ", totalCost, " TRY.\n");
                System.out.printf("%s%s%.3f%s", user.name, " has ", user.investment_budget, " TRY left.\n");

                return true;
            }
            else{
                System.out.println("Stock amount is not enough. Cannot sell.");
                return false;
            }
        }
        else if (!isOpen){
            System.out.println(user.name + " has made a transaction to sell " + amount  + " shares of "
                    + stock.getName() + "(" + stock.getSymbol() + ") while the Stock Market is closed.");
            System.out.println("Unable to process the transaction.");
            return false;
        }
        else if(!user.canMakeTransaction()){
            System.out.println(user.name +   " tried to make their " + user.transactionCount + "th transaction of the day however as an " + user.getUserType() + " they are only allowed up to " + user.getMaxTransactionsPerDay());
            return false;
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
