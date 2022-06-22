package multithreading.synchronizedBlocks;

public class Example2 {
    volatile static int counter = 0;

    /*
    В методе ниже синхронизация происходит на классе Example2,
    так как метод статичный.
    Когда метод статичный, синхронизация не может происходить по this.
     */
    public static synchronized void increment() {
        counter++;
    }

    /*
    В таком случае, если мы хотим использовать блок synchronized, то
    синхронизация по классу будет иметь следующий вид:
     */
    public static void increment2() {
        synchronized (Example2.class) {
            counter++;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                Example2.increment();
            }
        };
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter);

    }
}
