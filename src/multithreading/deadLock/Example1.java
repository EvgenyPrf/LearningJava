package multithreading.deadLock;

public class Example1 {
/*
Ниже представлен пример deadlock'а. Поток 1 сначала захватывает монитор lock1, а потом пытается захватить монитор lock2.
Параллельно поток 2 сначала пытается захватить монитор lock2, а потом пытается захватить монитор lock1.
В данном случае велика вероятность возникновения deadlock'a, так как когда поток 1 будет пытаться захватить lock2, он уже будет захвачен потоком 2, и наоборот: поток 2 будет пытаться захватить lock 1, котоырй уже будет захвачен потоком 1.
Программа, попавшая в состоянии deadlock, никогда не завершится сама.
В примере ниже, чтобы предотвратить deadlock, необходимо, чтобы захват монитора объектов lock1 и lock2 был в одинаковом порядке.
  */
  public static final Object lock1 = new Object();
  public static final Object lock2 = new Object();

  public static void main(String[] args) {
    Thread1 thread1 = new Thread1();
    Thread2 thread2 = new Thread2();
    thread1.start();
    thread2.start();
  }
}
class Thread1 extends Thread{
  public void run(){
    System.out.println("Thread1: попытка захватить монитор объекта lock1");
    synchronized(Example1.lock1){
      System.out.println("Thread1: монитор объекта lock1 захвачен");
      System.out.println("Thread1: попытка захватить монитор объекта lock2");
      synchronized(Example1.lock2){
        System.out.println("Thread1:мониторы объектов lock1 и lock2 захвачены");
      }
    }
  }
}
class Thread2 extends Thread{
  public void run(){
    System.out.println("Thread2: попытка захватить монитор объекта lock2");
    synchronized(Example1.lock2){
      System.out.println("Thread2: монитор объекта lock2 захвачен");
      System.out.println("Thread2: попытка захватить монитор объекта lock1");
      synchronized(Example1.lock1){
        System.out.println("Thread2:мониторы объектов lock1 и lock2 захвачены");
      }
    }
  }
}