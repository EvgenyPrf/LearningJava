package multithreading.callableAndFuture;

import java.util.concurrent.*;

public class CallableFactorial {
    static int factorialResult;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Factorial2 factorial2 = new Factorial2(5);
        //метод submit, это аналог метода  execute, но он принимает в качестве аргумента не Runnable, а Callable
        //и возвращает результат нашего таска. Результат хранится в бъекте типа Future
        Future<Integer> future = executorService.submit(factorial2);
        try {
            factorialResult = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        } finally {
            executorService.shutdown();
        }
        System.out.println(factorialResult);
    }

}
//Интерфейс Callable - это аналог интерфейса Runnable, с разницей в том, что Callable может выбрасывать исключение,
// а метод call(аналог метода run), имеет returnType.
class Factorial2 implements Callable<Integer> {
    int f;
    public Factorial2(int f) {
        this.f = f;
    }
    @Override
    public Integer call() throws Exception {
        if (f <= 0) {
            throw new Exception("Vi vveli nevernoe chislo");
        }
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        return result;
    }
}