public class AggressiveInvestor extends User {
    private static final int MAX_TRANSACTIONS_PER_DAY = 10;
    public AggressiveInvestor(StockMarket stockMarket, String name, double investmentBudget) {
        super(stockMarket, name, investmentBudget);
        transactionCount = 0;
    }
    @Override
    public int shouldBuyStock(Stock stock) {
        if (stock.getPercentChange() < 0){
            double investment = investment_budget * 0.1;
            int amountToBuy = (int) Math.floor(investment / stock.getPrice());
            transactionCount++;
            System.out.println(name + " calculated that they should buy " + amountToBuy + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");
            return amountToBuy;
        }
        return 0;
    }
    @Override
    public int shouldSellStock(Stock stock) {
        if (stock.getPercentChange() > 0 && investment_portfolio.containsKey(stock.getName())) {
            double maxAmount = investment_budget * 0.12;
            int amountToSell = (int) Math.floor(maxAmount / stock.getPrice());
            if (amountToSell > 0) {
                transactionCount++;
                System.out.println(name + " calculated that they should sell " + amountToSell + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");
                return amountToSell;
            }
        }
        else if (stock.getPercentChange() < -2.00 && investment_portfolio.containsKey(stock.getName())) {
            int amountToSell = (int) stock.getVolume();
            if (amountToSell > 0) {
                transactionCount++;
                System.out.println(name + " calculated that they should sell " + amountToSell + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");
                return amountToSell;
            }
        }

        return 0;
    }
    public boolean canMakeTransaction(){
        return transactionCount < MAX_TRANSACTIONS_PER_DAY;
    }
    public int getMaxTransactionsPerDay(){
        return MAX_TRANSACTIONS_PER_DAY;
    }
    public String getUserType(){
        return "Aggressive Investor";
    }
}
