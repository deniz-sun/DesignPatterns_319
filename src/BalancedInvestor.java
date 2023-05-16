public class BalancedInvestor extends User{
    private static final int MAX_TRANSACTIONS_PER_DAY = 7;
    public BalancedInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget);
        transactionCount = 0;
    }

    @Override
    public int shouldBuyStock(Stock stock) {
        if (stock.getPercentChange() < 0 && (investment_budget > 20* stock.getPrice())){
            double investment = investment_budget * 0.08;
            int amountToBuy = (int) Math.floor(investment / stock.getPrice());
            transactionCount++;
            System.out.println(name + " calculated that they should buy " + amountToBuy + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");

            return amountToBuy;

        }
        return 0;
    }

    /*
     * if change > 0 && budget < 1000 * price
     *  sell. sell as much as possible, but no more than 8% of budget
     */
    @Override
    public int shouldSellStock(Stock stock) {
        if (stock.getPercentChange() > 0 &&  (investment_budget < 1000* stock.getPrice()) && investment_portfolio.containsKey(stock.getName())) {
            double maxAmount = investment_budget * 0.08;
            int amountToSell = (int) Math.floor(maxAmount / stock.getPrice());
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
        return "Balanced Investor";
    }
}


