---///Понятие "монитор" и synchronized блоки///---
Монитор - это сущность/механизм, благодаря которому достигается корректная работа при синхронизации.
В java у каждого класса и объекта есть привязанный к нему монитор.
В один момент времени только один тред может занять монитор.
У метода нет монитора, для его синхронизации используется монитор объекта или класса.
Ниже пример шаблона synchronized блока:
static final Object lock = new Object();
public void abc(){
//method body
synchronized(lock){
//block body
}
//method body
}
