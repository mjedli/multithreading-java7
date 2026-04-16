public class OrderBook {

    private int buyVolume = 0;
    private int sellVolume = 0;

    public synchronized void submitOrder(Order order) {
        if (order.getType() == Order.Type.BUY) {
            buyVolume += order.getQuantity();
            System.out.println(order.getTrader() + " BUY " + order.getQuantity());
        } else {
            sellVolume += order.getQuantity();
            System.out.println(order.getTrader() + " SELL " + order.getQuantity());
        }
    }

    public synchronized void printSummary() {
        System.out.println("=== MARKET SUMMARY ===");
        System.out.println("Total BUY volume : " + buyVolume);
        System.out.println("Total SELL volume: " + sellVolume);

    }

    public synchronized String printSummaryFuture() {
        StringBuffer sb = new StringBuffer();
        sb.append("=== MARKET SUMMARY ===");
        sb.append("Total BUY volume : " + buyVolume);
        sb.append("Total SELL volume: " + sellVolume);
        return sb.toString();
    }
}
