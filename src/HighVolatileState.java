public class HighVolatileState implements StockMarketState{
    //if the daily cost of transactions exceeds 1,000,000 TRY, the stock market is highly volatile
    //transaction fee is 3% of the price * volume
    private static final double TRANSACTION_FEE = 0.03;

    @Override
    public double calculateTransactionFee(double price, double volume) {
        return price * volume * TRANSACTION_FEE;
    }
}
