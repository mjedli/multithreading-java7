public class Order {
    public enum Type {BUY, SELL}

    private final Type type;
    private final String trader;
    private final int quantity;

    public Order(Type type, String trader, int quantity) {
        this.type = type;
        this.trader = trader;
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public String getTrader() {
        return trader;
    }

    public int getQuantity() {
        return quantity;
    }
}
