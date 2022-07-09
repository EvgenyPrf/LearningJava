package multithreading.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerEx {
/* Вспоминаем пример, при котором у нас возникало состояние data race, при котором
два потока меняют одну и ту же переменную без синхронизации. Их нужно было синхронизировать,
так как операция инкремент не атомарная. Ранее эту проблему мы решали с помощью synchronized (этот процесс не быстрый,
так как проходит сначала блокировка монитора, потом его разблокировка и так далее),
но в java есть класс AtomicInteger, в котором все операции происходят атомарно.
*/
//static int counter = 0;

  static AtomicInteger counter = new AtomicInteger();
  
   public static void increment(){
    // counter++;
     // counter.incrementAndGet();
     counter.addAndGet(5);
   }
  
  public static void main(String []args) throws InterruptedException {
    Thread thread1 = new Thread(new MyRunnableImpl());
    Thread thread2 = new Thread(new MyRunnableImpl());
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println(counter);
    
  }
}
class MyRunnableImpl implements Runnable{
  public void run(){
    for(int i = 0; i < 100; i++){
      AtomicIntegerEx.increment();
    }
  }
}