interface Handler {
    void setNext(Handler handler);
    void handleRequest(String request);
}

// класс для обработчиков
abstract class AbstractHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Ни один обработчик не смог обработать запрос: " + request);
        }
    }
}

// обработчики:
class AuthenticationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("auth")) {
            System.out.println("AuthenticationHandler: Обрабатываю запрос аутентификации");
            // Логика обработки аутентификации
        } else {
            super.handleRequest(request);
        }
    }
}

class AuthorizationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("admin")) {
            System.out.println("AuthorizationHandler: Проверяю права администратора");
            // Логика проверки прав
        } else {
            super.handleRequest(request);
        }
    }
}

class LoggingHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.contains("log")) {
            System.out.println("LoggingHandler: Логирую запрос");
            // логирование
        } else {
            super.handleRequest(request);
        }
    }
}

// Клиентский код
public class ChainOfResponsibilities {
    public static void main(String[] args) {
        // цепочка обработчиков
        Handler authHandler = new AuthenticationHandler();
        Handler authzHandler = new AuthorizationHandler();
        Handler loggingHandler = new LoggingHandler();

        // порядок в цепочке
        authHandler.setNext(authzHandler);
        authzHandler.setNext(loggingHandler);

        // запросы
        System.out.println("Отправляем запрос на аутентификацию:");
        authHandler.handleRequest("Запрос на auth пользователя");

        System.out.println("\nОтправляем запрос администратора:");
        authHandler.handleRequest("Запрос от admin");

        System.out.println("\nОтправляем запрос на логирование:");
        authHandler.handleRequest("Запрос на log действий");

        System.out.println("\nОтправляем неизвестный запрос:");
        authHandler.handleRequest("Обычный запрос");
    }
}