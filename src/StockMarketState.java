public interface StockMarketState{

    double calculateTransactionFee(double price, double volume);
    double getTransactionFee();

}
