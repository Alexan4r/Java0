class A {
    synchronized void methodA(B b) {                                // блокирует монитор объекта A
        System.out.println("Thread 1: Holding lock A...");
        try {
            Thread.sleep(100);                                // имитация работы увеличивающая шанс deadlock
        } catch (InterruptedException e) {}
        System.out.println("Thread 1: Waiting for lock B...");
        b.last();                                                   // вызов синхронизированного метода last() объекта B
    }

    synchronized void last() {                                      // метод last блокирует монитор объекта A
        System.out.println("Inside A's last method");
    }
}

class B {
    synchronized void methodB(A a) {                                // метод methodB блокирует монитор объекта B
        System.out.println("Thread 2: Holding lock B...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        System.out.println("Thread 2: Waiting for lock A...");
        a.last();                                                   // вызов синхронизированного метода last() объекта A
    }

    synchronized void last() {                                      // метод last блокирует монитор объекта B
        System.out.println("Inside B's last method");
    }
}

public class DeadLock {                                             // класс демонстрирующий deadlock
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();

        new Thread(() -> a.methodA(b)).start();               // поток 1 захватывает lock A и пытается захватить lock B
        new Thread(() -> b.methodB(a)).start();               // поток 2 захватывает lock B и пытается захватить lock A
    }
}