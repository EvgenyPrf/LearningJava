public class InterruptionEx {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("main starts");
    InterruptedThread thread = new InterruptedThread();
    thread.start();
    Thread.sleep(2000);
    thread.interrupt();
    thread.join();
    System.out.println("main ends");
  }

}

class InterruptedThread extends Thread {
  double sqrtSum = 0;

  public void run() {
    for (int i = 1; i <= 1000000000; i++) {
      if(isInterrupted()){
        System.out.println("Поток хотят прервать");
        System.out.println("Мы убедились, что состояние всех объектоа нормальное и решили завершить работу потока");
        /*если на текущем методе вызвали метол interrupted(), то выходим из метода run и завершаем поток*/
        return;
      }
      sqrtSum += Math.sqrt(i);
    }
    System.out.println(sqrtSum);
  }
}