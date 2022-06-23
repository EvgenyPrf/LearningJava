---///Lock и ReentrantLock///---
Lock  - это интерфейс, который имплементируется классом ReentrantLock.
Также как ключевое слово synchronized, Lock нужен для достижения синхронизаци между потоками.

Методы: lock(), unlock(), tryLock() - возвращает boolean.