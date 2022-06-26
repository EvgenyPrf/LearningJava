package multithreading.callableAndFuture;

import java.util.concurrent.*;

public class CallableFactorial {
    static int factorialResult;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Factorial2 factorial2 = new Factorial2(4);
        //метод submit, это аналог метода  execute, но он принимает в качестве аргумента не Runnable, а Callable
        //и возвращает результат нашего таска. Результат хранится в бъекте типа Future
        Future<Integer> future = executorService.submit(factorial2);

        try {
            //метод isDone() пзвляет узнать, выполнен ли наш task
            System.out.println(future.isDone());
            //на строке ниже поток main будет заблокирован и будет ожидать выполнение нашего задания.
            //после тогоо, как задание выполнится, main прдолжит работу
            factorialResult = future.get();
            System.out.println(future.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println(e.getCause());
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
            Thread.sleep(100);
        }
        return result;
    }
}