public class CloseState implements StockMarketState {
   //no transactions can be made when the stock market is closed
    StockMarket stockMarket;
    CloseState(StockMarket stockMarket){
        this.stockMarket = stockMarket;
    }
    @Override
    public double calculateTransactionFee(double price, double volume) {
        return 0.0;
    }
}
