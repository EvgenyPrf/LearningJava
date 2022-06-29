package multithreading.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerEx {
  /*Рассмотрим реализацию exchange между потоками на примере игры камень/ножницы/бумага.
Обмен информацией между потоками происходит тогда, когда оба потока передали информацию в exchanger
Информация должна быть одного типа данных.
*/
  public static void main(String [] args){
    Exchanger<Action> exchanger = new Exchanger<>();
    List<Action> friend1 = new ArrayList<>();
    friend1.add(Action.SCISSORS);
    friend1.add(Action.PAPER);
    friend1.add(Action.SCISSORS);
    List<Action> friend2 = new ArrayList<>();
    friend2.add(Action.ROCK);
    friend2.add(Action.ROCK);
    friend2.add(Action.PAPER);
    new BestFriend("Vanya", friend1, exchanger);
    new BestFriend("Petya", friend2, exchanger);


  }
}

enum Action{
  ROCK, PAPER, SCISSORS
}
class BestFriend extends Thread{
  private String name;
  private List<Action> myActions;
  private Exchanger<Action> exchanger;

  public BestFriend(String name, List<Action> myActions, Exchanger<Action> exchanger) {
    this.name = name;
    this.myActions = myActions;
    this.exchanger = exchanger;
    this.start();
  }
  private void whoWins(Action myAction, Action friendAction){
    if((myAction == Action.ROCK && friendAction == Action.SCISSORS)
      || (myAction == Action.SCISSORS && friendAction == Action.PAPER)
    || (myAction == Action.PAPER && friendAction == Action.ROCK)){
      System.out.println(name + " WINS!!!");
    }
  }
  public void run(){
    //ответ одного из потоков
    Action reply;
    for (Action action:
         myActions) {
      try {
        /*получаем ответ и сохраняем в reply, вызвав метод exchange и передав в него наш ответ
        поток будет заблокирован и не получит ответ, пока второй поток у себя не вызовет метод exchange и не передаст в него своей ответ
        */
        reply = exchanger.exchange(action);
        whoWins(action, reply);
        sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}