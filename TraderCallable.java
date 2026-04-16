import java.util.Random;
import java.util.concurrent.Callable;

public class TraderCallable implements Callable<String> {
    Random random = new Random();

    private OrderBook orderBook;

    private String name;

    public TraderCallable (String name, OrderBook orderBook)  {
        this.name = name;
        this.orderBook = orderBook;
    }
    @Override
    public String call() throws Exception {

        int totalVolume = 0;

        for (int i = 0; i < 5; i++) {

            Order.Type type = random.nextBoolean() ? Order.Type.BUY : Order.Type.SELL;
            int qty = random.nextInt(100) + 1;

            totalVolume += qty;

            Order order = new Order(type, qty);
            orderBook.submitOrder(order);

            try {
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return Integer.toString(totalVolume);
    }
}
