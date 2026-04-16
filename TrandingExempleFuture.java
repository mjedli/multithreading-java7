import java.util.*;
import java.util.concurrent.*;

public class TrandingExempleFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        OrderBook orderBook = new OrderBook();

        // Exemple avec ExectorService
        // 1. Créer un pool de threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 2. Définir une tâche Callable (retourne un résultat)

        Collection<Callable<String>> tasks = new ArrayList<Callable<String>>();
        tasks.add(new TraderCallable("Tread-A", orderBook));
        tasks.add(new TraderCallable("Tread-B", orderBook));
        tasks.add(new TraderCallable("Tread-C", orderBook));
        tasks.add(new TraderCallable("Tread-D", orderBook));
        tasks.add(new TraderCallable("Tread-E", orderBook));

        // 3. Soumettre la tâche et récupérer un Future

        List<Future<String>> futures = executor.invokeAll(tasks);

        try {

            for(Future<String> future : futures) {
                // 4. future.get() bloque jusqu'à ce que le résultat soit prêt
                System.out.println("Résultat du calcul : " + future.get());
            }

            orderBook.printSummary();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 5. Toujours fermer l'executor
            executor.shutdown();
        }

    }
}
