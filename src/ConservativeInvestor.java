public class ConservativeInvestor extends User{
    private int transactionCount;
    public ConservativeInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget);
        transactionCount = 0;
    }

    @Override
    public int shouldBuyStock(Stock stock) {
        if (stock.getPercentChange() < 0 && (investment_budget > 25* stock.getPrice())){
            double investment = investment_budget * 0.05;
            int amountToBuy = (int) Math.floor(investment / stock.getPrice());
            transactionCount++;
            System.out.println(name + " calculated that they should buy " + amountToBuy + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");

            return amountToBuy;

        }
        return 0;
    }

    /**
     * A
     * ConservativeInvestor should sell a Stock if the Stock has a positive change and their budget is
     * more than 75 times the Stock price. If so, they should sell as many of the current Stock as
     * possible.
 */

    @Override
    public int shouldSellStock(Stock stock) {
        if (stock.getPercentChange() > 0 && (investment_budget > 75 * stock.getPrice()) && investment_portfolio.containsKey(stock.getName())){
            int amountToSell = (int) Math.floor(investment_budget / stock.getPrice());
            if (amountToSell > 0) {
                transactionCount++;
                System.out.println(name + " calculated that they should sell " + amountToSell + " shares of " + stock.getName() + " (" + stock.getSymbol() + ").");
                return amountToSell;
            }
        }
        return 0;
    }
}

