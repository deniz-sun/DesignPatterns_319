public class BalancedInvestor extends User{
    public BalancedInvestor(StockMarket stockMarket, String name, double investment_budget) {
        super(stockMarket, name, investment_budget);
    }

    @Override
    public int shouldBuyStock(Stock stock) {
        if (stock.getPercentChange() < 0 && (investment_budget > 20* stock.getPrice())){
            double investment = investment_budget * 0.08;
            int amountToBuy = (int) Math.floor(investment / stock.getPrice());
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
            int amountToSell = investment_portfolio.getOrDefault(stock.getName(), 0);
            if (amountToSell > 0) {
                //?????????????????????????
                return Math.min(amountToSell, (int) (maxAmount / stock.getPrice()));
            }
        }
            return 0;
    }
}
