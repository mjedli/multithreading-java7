import java.util.Random;

public class Trader implements Runnable {

    private final String name;
    private final OrderBook orderBook;
    private final Random random = new Random();

    public Trader(String name, OrderBook orderBook) {
        this.name = name;
        this.orderBook = orderBook;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Order.Type type = random.nextBoolean() ? Order.Type.BUY : Order.Type.SELL;
            int qty = random.nextInt(100) + 1;

            Order order = new Order(type, name, qty);
            orderBook.submitOrder(order);

            try {
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
