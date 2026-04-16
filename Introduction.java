import java.util.concurrent.*;

public class Introduction {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Exemple avec thread
        MyThread t = new MyThread();
        t.start(); // lance le thread


        // Exemple avec Runnable
        Thread th = new Thread(new MyRunnable());
        th.start();


        // Exemple avec ExectorService
        // 1. Créer un pool de threads
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 2. Définir une tâche Callable (retourne un résultat)
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i <= 5; i++) {
                    sum += i;
                    Thread.sleep(300); // Simule un travail
                }
                return sum;
            }
        };

        // 3. Soumettre la tâche et récupérer un Future
        Future<Integer> future = executor.submit(task);

        try {
            // 4. future.get() bloque jusqu'à ce que le résultat soit prêt
            System.out.println("Résultat du calcul : " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 5. Toujours fermer l'executor
            executor.shutdown();
        }

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable thread running");
    }
}
