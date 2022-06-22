package multithreading.synchronizedBlocks;

public class Example1 {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class Counter {
    volatile static int count = 0;
}

class MyRunnable implements Runnable {
    private void doWork2(){
        System.out.println("hello");
    }

    private void doWork1() {
        /*используем блок synchronized на объекте this
        благодаря блоку synchronized, мы можем синхронизировать не весь метод, а его часть.
        Если же нам нужно синхронизировать весь метод,
        то указываем ключевое слово synchronized при объявлении метода.
         */
        doWork2();
        synchronized (this) {
            Counter.count++;
            System.out.println(Counter.count);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            doWork1();
        }
    }
}
