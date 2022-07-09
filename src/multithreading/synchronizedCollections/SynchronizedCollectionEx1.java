package multithreading.synchronizedCollections;

import java.util.*;

public class SynchronizedCollectionEx1{
  public static void main(String[] args) throws InterruptedException {
    ArrayList<Integer> source = new ArrayList<>();
    for(int i = 0; i < 5; i++){
      source.add(i);
    }
    //ArrayList<Integer> target = new ArrayList<>();
    //пример создания синхронизированного ArrayList();
    List<Integer> syncList =
      Collections.synchronizedList(new ArrayList<>());
    Runnable runnable = () -> {syncList.addAll(source);};
    Thread thread = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread.start();
    thread2.start();
    thread.join();
    thread2.join();
    System.out.println(syncList);
  }
}