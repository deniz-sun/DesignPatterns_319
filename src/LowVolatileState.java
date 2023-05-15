public class LowVolatileState implements StockMarketState {
    //number of transactions > 10
    //if the daily cost of transactions is less than 500,000 TRY, the stock market is low volatile
    //transaction fee is 0.5% of the price * volume
    private static final double TRANSACTION_FEE = 0.005;

    @Override
    public double calculateTransactionFee(double price, double volume) {
        return price * volume * TRANSACTION_FEE;
    }


}
