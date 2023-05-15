public class AggressiveInvestor extends User {
    private static final int MAX_TRANSACTIONS_PER_DAY = 10;
    private int transactionCount;
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
            return amountToBuy;
        }
        return 0;
    }
    @Override
    public int shouldSellStock(Stock stock) {
        if (stock.getPercentChange() > 0 && investment_portfolio.containsKey(stock.getName())) {
            double maxAmount = investment_budget * 0.12;
            int amountToSell = investment_portfolio.getOrDefault(stock.getName(), 0);
            if (amountToSell > 0) {
                transactionCount++;
                return Math.min(amountToSell, (int) (maxAmount / stock.getPrice()));
            }
        }
        else if (stock.getPercentChange() < -2.00) {
            int amountToSell = investment_portfolio.getOrDefault(stock.getName(), 0);
            if (amountToSell > 0) {
                transactionCount++;
                return amountToSell;
            }
        }
        return 0;
    }
    public boolean canMakeTransaction(){
        return transactionCount < MAX_TRANSACTIONS_PER_DAY;
    }
}
