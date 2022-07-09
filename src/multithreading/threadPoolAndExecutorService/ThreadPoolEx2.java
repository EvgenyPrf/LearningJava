package multithreading.threadPoolAndExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolEx2 {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
//        for (int i = 0; i < 10; i++) {
//            scheduledExecutorService.execute(new RunnableIml2());
//        }
        /*метод schedule выполнит наши задания через определённый период времени
         * */
//        scheduledExecutorService.schedule(new RunnableIml2(), 3, TimeUnit.SECONDS);

//планиурет задачу для перидическогоо выполнения: в примере ниже задача начнет выполняться через три секунды и продолжит свое
        //выполнение с перидичностью 1 секунда. Одна секунда в данном случае проходит с момента начала первого задания
        //до момента начала второго задания
//        scheduledExecutorService.scheduleAtFixedRate(new RunnableIml2(), 3, 1, TimeUnit.SECONDS);

        //scheduleWithFixedDelay такой же метод, как и scheduleAtFixedRate, но разница в том, что одна секунда пройдет
        //с момента окончания первого задания до момента начала второго задания.
        scheduledExecutorService.scheduleWithFixedDelay(new RunnableIml2(), 3, 1, TimeUnit.SECONDS);
        Thread.sleep(20000);
        scheduledExecutorService.shutdown();
//кешированный тред пул. Метод newCachedThreadPool создает тред пул, который будет создавать новые потоки по надобности.
        /* Thread1 <- task1
        Thread2 <- task2
         если Thread1 свбодился, а в execotorService поступает новый таск, то поток может быть переопределён.
         Он возьмет на себя выполнение данного таска, и новый поток не будет создан.
         Thread1 <- task3
         Если поток освободился, но в течение 60-ти сек он не получает новый таск, то он будет уничтожен.
        * */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new RunnableIml2());
        }
        executorService.shutdown();
    }
}

class RunnableIml2 implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begins work");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + " ends work");

    }
}
