package multithreading.waitAndNotifyMethods;

/*
рассмотрим пример методов wait() и notify() на примере прилавка в магазине.
У нас есть прилавок с хлебом. Есть продавец (Producer) и потребитель (Consumer).
На прилавке лежит 5 буханок хлеба. 
Потребитель может брать хлеб, пока его количество не < 1. 
Если хлеба стало меньше одного, то потребитель ждёт (вызываем метод wait()).
Как только хлеб появился, продавец уведомляет потребителя методом notify(), потребитель может дальше брать хлеб.
Если количество хлеба = 5, продавец ждёт (метод wait()). Но как только хлеба стало < 5, потребитель уведомляет продавца (метод notify()), о том, что можно снова положить хлеб.
*/
public class Example1 {
  public static void main(String[] args) {
    Market market = new Market();
    Thread produsersThread = new Thread(new Producer(market));
    Thread consumersThread = new Thread(new Consumer(market));
    produsersThread.start();
    consumersThread.start();

  }
}

class Market {
  private int breadCount = 0;

  // метод, который позволяет взять хлеб
  //метод в данном случае будет синхронизирован по объекту Marker market;
  //это значит, что метод getBread() и putBread() может использовать только один поток
  // в один момент времени. Какой из потоков первый войдёт в метод, помеченный synchronized, тот
  //из них и первый захватит монитор.
  
  public synchronized void getBread() {
    while (breadCount < 1) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    breadCount--;
    System.out.println("Потребитель купил 1 хлеб");
    System.out.println("Количество хлеба на витрине = " + breadCount);
    // когда мы берем один хлеб, мы уведомляем продавца о том, что можно еще
    // добавить хлеб
    notify();
  }

  // метод, который позволяет положить хлеб
    //метод в данном случае будет синхронизирован по объекту Marker market;
  public synchronized void putBread() {
    while (breadCount >= 5) {
      // если хлеба на витрине 5, продавец ждёт
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    breadCount++;
    System.out.println("Производитель добавил на витрину 1 хлеб");
    System.out.println("Количество хлеба на витрине = " + breadCount);
    notify();
  }
}

// создадим классы продавца и потребителя
class Producer implements Runnable {
  Market market;

  public Producer(Market market) {
    this.market = market;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      market.putBread();
    }
  }
}

class Consumer implements Runnable {
  Market market;

  public Consumer(Market market) {
    this.market = market;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      market.getBread();
    }
  }
}
