package multithreading.concurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

public class ConcurrentHashMapEx {
  public static void main(String [] args) throws InterruptedException {
    ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    map.put(1, "Zaur");
    map.put(2, "Oleg");
    map.put(3, "Sergey");
    map.put(4, "Ivan");
    map.put(5, "Igor");
    System.out.println(map);

    Runnable runnable = () -> {
      Iterator<Integer> iterator = map.keySet().iterator();
      while (iterator.hasNext()) {
        try{
        Thread.sleep(100);}
        catch(InterruptedException e){
          e.printStackTrace();
        }
        Integer i = iterator.next();
        System.out.println(i + " : " + map.get(i));
}
    };
    Runnable runnable2 = () -> {
        try{
        Thread.sleep(100);}
        catch(InterruptedException e){
          e.printStackTrace();
        }
      map.put(6, "Elena");
    };
    Thread thread = new Thread(runnable);
    Thread thread2 = new Thread(runnable2);
    thread.start();
    thread2.start();
    thread.join();
    thread2.join();
    System.out.println(map);
  }
}