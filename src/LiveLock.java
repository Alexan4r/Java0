class Philosopher implements Runnable {     // класс представляющий философа
    private final Object leftChopstick;     // левый прибор (вилка)
    private final Object rightChopstick;    // правый прибор (нож)
    private final String name;              // имя философа

    public Philosopher(String name, Object leftChopstick, Object rightChopstick) {
        this.name = name;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        while (true) {              // бесконечный цикл жизни философа
            think();                // 1) философ размышляет
            pickUpChopsticks();     // 2) пытается взять приборы
            eat();                  // 3. если получилось взять оба прибора - ест
            putDownChopsticks();    // 4. кладет приборы обратно
        }
    }

    private void think() {
        System.out.println(name + " is thinking.");
    }

    private void eat() {
        System.out.println(name + " is eating.");
    }

    private void pickUpChopsticks() {           // метод для взятия приборов (может привести к livelock)
        synchronized (leftChopstick) {          // 1) берёт левый прибор
            System.out.println(name + " picked up left chopstick.");
            synchronized (rightChopstick) {     // 2) пытается захватить правый прибор
                System.out.println(name + " picked up right chopstick.");
            }
        }
    }

    private void putDownChopsticks() {          // метод возврата приборов
        synchronized (rightChopstick) {
            System.out.println(name + " put down right chopstick.");
            synchronized (leftChopstick) {
                System.out.println(name + " put down left chopstick.");
            }
        }
    }
}

public class LiveLock {
    public static void main(String[] args) {
        // создаем два прибора:
        Object chopstick1 = new Object();
        Object chopstick2 = new Object();

        /* создание двух философов с циклической зависимостью приборов
         * философ 1: left=1, right=2
         * философ 2: left=2, right=1 */
        Thread philosopher1 = new Thread(new Philosopher("Philosopher 1", chopstick1, chopstick2));
        Thread philosopher2 = new Thread(new Philosopher("Philosopher 2", chopstick2, chopstick1));

        philosopher1.start();
        philosopher2.start();
    }
}