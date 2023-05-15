public class OpenState implements StockMarketState {
    //transaction fee is 1.5% of the price * volume
    private static final double TRANSACTION_FEE = 0.015;

    @Override
    public double calculateTransactionFee(double price, double volume) {
        return price * volume * TRANSACTION_FEE;
    }
}
