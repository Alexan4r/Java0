// интерфейс напитка
interface Coffee {
    String getDescription();
    double getCost();
}

// компонент простая чашка кофе
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Простой кофе";
    }

    @Override
    public double getCost() {
        return 1.0;
    }
}

// декоратор для добавок
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// декораторы-добавки
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", молоко";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", сахар";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", взбитые сливки";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.7;
    }
}

class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", карамель";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.6;
    }
}

// демо
public class DecoratorCoffeeHouse {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        Coffee coffeeWithMilkAndSugar = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(coffeeWithMilkAndSugar.getDescription() + " $" + coffeeWithMilkAndSugar.getCost());

        Coffee fancyCoffee = new WhippedCreamDecorator(
                new CaramelDecorator(
                        new MilkDecorator(
                                new SimpleCoffee())));
        System.out.println(fancyCoffee.getDescription() + " $" + fancyCoffee.getCost());
    }
}