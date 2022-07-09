package multithreading.synchronizedBlocks;

public class Example3 {
    static final Example3 lock = new Example3();
/*
В данном примере мы имитируем входящий звонок, который может поступить либо
через мобильную сеть, либо через скайп, либо через вотсапп.
Наша задача сделать так, чтобы одновременно был запущен только один поток:
если нам позвонили по мобильной связи, то звонок со скайпа и с вотсаппа ставится на удержание.
 */

    /*
    в методах ниже мы не можем использовать синхронизацию в сигнатуре на нестатическом методе,
    так как методы не статичные, то каждый поток будет синхронизироваться на разных экземплярах
    класса Example3 (каждый тред занимает монитор разного объекта).
    Чтобы это исправить, мы создаём общий статический объект lock, чей монитор будет использоваться для синхронизации.
     */
    void mobileCall()  {
        //синхронизация на объекте lock
        synchronized (lock) {
            System.out.println("Mobile call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Mobile call ends");
        }
    }

    void skypeCall()  {
        //синхронизация на объекте lock
        synchronized (lock) {
            System.out.println("Skype call starts");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Skype call ends");
        }
    }

    void whatsappCall()  {
        //синхронизация на объекте lock
        synchronized (lock) {
            System.out.println("Whatsapp call starts");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Whatsapp call ends");
        }
    }

    public static void main(String[] args) {
        Runnable mobile = () -> {
            new Example3().mobileCall();
        };
        Runnable skype = () -> {
            new Example3().skypeCall();
        };
        Runnable whatsapp = () -> {
            new Example3().whatsappCall();
        };

        Thread thread1 = new Thread(mobile);
        Thread thread2 = new Thread(skype);
        Thread thread3 = new Thread(whatsapp);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
