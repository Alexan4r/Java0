interface Strategy {
    int execute(int a, int b);
}

// стратегии:
class AddStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

class SubtractStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

class MultiplyStrategy implements Strategy {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

// контекст использующий стратегию
class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// использование
public class PatternStrategy {
    public static void main(String[] args) {
        Context context = new Context();

        // стратегия сложения
        context.setStrategy(new AddStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        // стратегия вычитания
        context.setStrategy(new SubtractStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        // стратегия умножения
        context.setStrategy(new MultiplyStrategy());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}