---///возможные ситуации в многопоточном программировании///---
Deadlock - ситуация, когда 2 или более потоков залочены навсегда, ожидают друг друга и ничего не делают.

Livelock - ситуация, когда 2 или более потоков залочены навсегда, ожидают друг друга, проделывают какую-то работу,
но без какого-либо прогресса.
Например тред 1 добавляет какую-то информацию в ресурс, а тред 2 её удаляет. Два потока работают, делают какие-то действия, но это ни к чему не приводит.

Lock starvation - ситуация, когда менее приоритетные потоки ждут долгое время или всё время для того, чтобы могли запуститься.


