---///AtomicInteger///---
- это класс, который предоставляет возможность работать с целочисленным значением int, используя атомарные операции.
Методы:
incrementAndGet() - атомарно увеличивает переменную counter типа AtomicInteger и возвращает значение.

getAndIncrement() - возвращает значение, потом атомарно увеличивает переменную counter типа AtomicInteger.

addAndGet() - увеличивает  атомарно переменную counter типа AtomicInteger на аргумент, переданный в параметрах и возвращает значение.

getAndAdd() - возвращает значение и увеличивает  атомарно переменную counter типа AtomicInteger на аргумент, переданный в параметрах.

decrementAndGet() - атомарно уменьшает значение на единицу и возвращает результат.

getAndDecrement() - возвращает результат, а потом атомарно уменьшает значение на единицу.
