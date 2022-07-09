package multithreading.threadPoolAndExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEx1 {
    public static void main(String[] args) throws InterruptedException {
    /* пример создания пула потоков c помощью фабричного метода
  ExecutorService executorServise = Executors.newFixedThreadPool(5);
еще один способ создания тред пула (в котором содержится только 1 поток)
все задания будут выполняться одним потоком поочередно
*/
        ExecutorService executorServise = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorServise.execute(new RunnableIml());
        }
    /*чтобы остановить executorService, вызываем метод shutdown(), иначе программа не завершит работу
и будет ждать новые задания
      */
        executorServise.shutdown();
    /*метод awaitTerminations заставляет ожидать поток, в котором он вызывается (в данном случае поток main).
Поток main будет ждать, пока executorService не закончит свою работу или пока не пройдёт 5 секунд.
*/
        //executorServise.awaitTermination(5, TimeUnit.SECONDS);
        // System.out.println("Main ends");
    }
}

class RunnableIml implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begins work");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ends work");

    }
}