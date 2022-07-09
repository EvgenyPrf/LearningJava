package multithreading.synchronizedCollections;

import java.util.*;

public class SynchronizedCollectionEx2 {
  public static void main(String[] args) throws InterruptedException{
    ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
      arrayList.add(i);
    }
    List<Integer> syncList = Collections.synchronizedList(arrayList);
    Runnable runnable = () -> {
      //делаем синхронизацию по syncList, чтобы метод remove() второго потока ждал, пока не будут перебраны //все элементы
      synchronized(syncList){      
        Iterator<Integer> iterator = syncList.iterator();
      while(iterator.hasNext()){
        System.out.println(iterator.next());
      }}

    };
        Runnable runnable2 = () -> syncList.remove(10);

    Thread thread = new Thread(runnable);
    Thread thread2 = new Thread(runnable2);
    thread.start();
    thread2.start();
    thread.join();
    thread2.join();
    System.err.println(syncList);
}
}