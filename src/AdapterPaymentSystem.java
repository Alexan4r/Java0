// новая система оплаты
interface ModernPayment {
    void pay(double amount, String currency, String transactionId);
}

// старая система оплаты
class LegacyPayment {
    void makePayment(double amountInDollars) {
        System.out.println("Оплата через LegacyPayment: $" + amountInDollars);
    }
}

// адаптер для старой системы
class LegacyPaymentAdapter implements ModernPayment {
    private LegacyPayment legacyPayment;

    public LegacyPaymentAdapter(LegacyPayment legacyPayment) {
        this.legacyPayment = legacyPayment;
    }

    @Override
    public void pay(double amount, String currency, String transactionId) {
        // конвертация валюту в доллары
        double amountInDollars = convertToDollars(amount, currency);
        legacyPayment.makePayment(amountInDollars);
        System.out.println("ID транзакции: " + transactionId);
    }

    private double convertToDollars(double amount, String currency) {
        // логика конвертации
        return switch (currency.toUpperCase()) {
            case "EUR" -> amount * 1.1;
            case "GBP" -> amount * 1.3;
            default -> amount;
        };
    }
}

// клиентский код
public class AdapterPaymentSystem {
    public static void main(String[] args) {
        // работа с новой системой
        ModernPayment modernPayment = new ModernPayment() {
            @Override
            public void pay(double amount, String currency, String transactionId) {
                System.out.printf("Оплата %.2f %s (ID: %s)%n", amount, currency, transactionId);
            }
        };
        modernPayment.pay(100, "EUR", "MOD-123");

        // работа со старой системой через адаптер
        LegacyPayment legacyPayment = new LegacyPayment();
        ModernPayment adaptedPayment = new LegacyPaymentAdapter(legacyPayment);
        adaptedPayment.pay(100, "GBP", "LEG-456");
    }
}