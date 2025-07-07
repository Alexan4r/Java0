public class Multithreading {
    private static final Object lock = new Object();
    private static boolean isFirst = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {                         // создание первого потока
            while (true) {                                          // создание цикла
                synchronized (lock) {                               // синхронизация следующего блока кода с монитором
                    while (!isFirst) {                              // ожидание очереди первого потока
                        try {
                            lock.wait();                            // выход из монитора и ожидание уведомления
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();     // в случае прерывания восстанавливаем статус прерывания
                        }
                    }
                    System.out.println("1");         // печатаем "1", когда наступает очередь
                    isFirst = false;                 // смена флага для выполнения следующего потока
                    lock.notify();                   // уведомляем другой поток, что он может продолжить работу
                }
            }
        });

        Thread thread2 = new Thread(() -> {     // создание второго потока
            while (true) {
                synchronized (lock) {
                    while (isFirst) {           // ожидание второго потока, пока isFirst не станет false
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("2");    // печатаем "2", когда наступает очередь
                    isFirst = true;             // смена флага для первого потока
                    lock.notify();              // уведомление первого потока
                }
            }
        });

        // запуск обоих потоков:
        thread1.start();
        thread2.start();
    }
}
