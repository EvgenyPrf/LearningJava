package multithreading.semaphore;

import java.util.concurrent.Semaphore;
/*в данном классе рассматривается работа семафора на примере телефонной будки.
У нас есть две будки, значит звонить одновременно могут только два человека (работают два потока).
* */
public class SemaphoreEx {
    public static void main(String[] args) {
        //создаём семафор, который разрешает только двум потокам работать одновременно
        Semaphore callBox = new Semaphore(2);
        new Person("Zaur", callBox);
        new Person("Oleg", callBox);
        new Person("Elena", callBox);
        new Person("Victor", callBox);
        new Person("Marina", callBox);

    }
}
class Person extends Thread {
    String name;
    private Semaphore callBox;

    public Person(String name, Semaphore callBox) {
        this.name = name;
        this.callBox = callBox;
        this.start();
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " ждёт..");
            /*Метод acquire заблокирует поток, пока ресурс не будет доступен.
            После доступности, мы получаем разрешение на использование ресурса
            */
            callBox.acquire();
            System.out.println(name + " пользуется телефоном.");
            sleep(2000);
            System.out.println(name + " завершил звонок.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /*Метод release говорит о том, что мы освобождаем
            разрешение семафора и увеличиваем counter семафора на единицу
            * */
            callBox.release();
        }
    }
}
