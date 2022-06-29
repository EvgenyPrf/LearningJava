package multithreading.callableAndFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SumNumbers {
    /*в данном классе, при помощи тред пулов и Callable посчитаем сумму всех чисел от  1 до 1_000_000_000
    * */
    private static long value = 1_000_000_000L;
    private static long sum = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //создаем пул из 10 потоков
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //создаем список, в который будем сохранять результат вычисления каждого потока
        List<Future<Long>> futureResults = new ArrayList<>();

        long valueDivideBy10 = value/10;
        for (int i = 0; i < 10; i++) {
            long from = valueDivideBy10*i+1;
            long to = valueDivideBy10*(i+1);
            PartialSum task = new PartialSum(from, to);
            Future<Long> future = executorService.submit(task);
            futureResults.add(future);
        }
        for (Future<Long> result:
             futureResults) {
            sum += result.get();
        }
        executorService.shutdown();
        System.out.println("Total sum: " + sum);
    }
}
class PartialSum implements Callable<Long>{
    long from;
    long to;
    long localSum;
    public PartialSum(long from, long to){
        this.from = from;
        this.to = to;
    }
    @Override
    public Long call() {
        for (long i = from; i <= to; i++) {
            localSum += i;
        }
        System.out.println(Thread.currentThread().getName() + " Sum from " + from + " to " + to + " = " + localSum);
        return localSum;
    }
}