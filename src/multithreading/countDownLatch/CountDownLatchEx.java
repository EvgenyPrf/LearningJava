package multithreading.countDownLatch;

import java.util.concurrent.CountDownLatch;
/*Класс ниже описывает пример работы CountDownLatch
Допустим у нас есть магазин, у которого есть несколько людей, ожидающих его открытия.
Чтобы запустить людей в магазин, необходимо выполнить три операции:
1. Чтобы сотрудники пришли на работу (метод marketStuffIsOnPlace);
2. Выложили товары на полки (метод everythingIsReady);
3. Открыли магазин (метод openMarket)
* */
public class CountDownLatchEx {
    static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static void marketStuffIsOnPlace() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Market staff came to work");
        //уменьшаем счетчик заданий на единицу методом countDown()
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
    private static void everythingIsReady() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Everything is ready, so let's open market");
        //уменьшаем счетчик заданий на единицу методом countDown()
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }
    private static void openMarket() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("Market is opened");
        //уменьшаем счетчик заданий на единицу методом countDown()
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }


    public static void main(String[] args) throws InterruptedException {
        new Friend("Zaur", countDownLatch);
        new Friend("Masha", countDownLatch);
        new Friend("Oleg", countDownLatch);
        new Friend("Misha", countDownLatch);
        marketStuffIsOnPlace();
        everythingIsReady();
        openMarket();
    }

}
class Friend extends Thread{
    String name;
    private CountDownLatch countDownLatch;
    public Friend(String name, CountDownLatch countDownLatch){
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();
    }
    public void run(){
        try {
            //если счётчик countDownLatch > 0, наш поток будет заблокирован, пока счетчик countDownLatch не
            //станет равен 0.
            countDownLatch.await();
            System.out.println(name + " приступил к покупкам.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
