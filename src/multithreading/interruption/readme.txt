---///interruption threads///---
У нас есть возможность послать сигнал треду о том, что мы хотим его прервать.
У нас также есть возможность в самом треде проверить, хотят ли его прервать. О том, как поступить, если данная проверка показала, что поток хотят прервать, решает сам программист.
Методы для прерывания треда: interrupt(), isInterrupted().