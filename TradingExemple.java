import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradingExemple {

    public static void main(String[] args) {

        OrderBook orderBook = new OrderBook();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new Trader("Trader-A", orderBook));
        executor.submit(new Trader("Trader-B", orderBook));
        executor.submit(new Trader("Trader-C", orderBook));
        executor.submit(new Trader("Trader-D", orderBook));

        executor.shutdown();

        while (!executor.isTerminated()) {
            // attendre la fin
        }

        orderBook.printSummary();
    }
}


