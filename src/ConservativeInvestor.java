public class ConservativeInvestor extends User{
    public ConservativeInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget);
    }

    @Override
    public int shouldBuyStock(Stock stock) {
        if (stock.getPercentChange() < 0 && (investment_budget > 25* stock.getPrice())){
            double investment = investment_budget * 0.05;
            int amountToBuy = (int) Math.floor(investment / stock.getPrice());
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
        if (stock.getPercentChange() > 0 && (investment_budget > 75* stock.getPrice()) && investment_portfolio.containsKey(stock.getName())){
            double amountToSell = investment_portfolio.getOrDefault(stock.getName(), 0);
        }
        return 0;
    }
}
