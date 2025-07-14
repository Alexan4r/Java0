import java.util.Date;

//
interface Database {
    void connect();
    void query(String sql);
}

// реализация БД
class RealDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Установлено соединение с базой данных");
    }

    @Override
    public void query(String sql) {
        System.out.println("Выполнен запрос: " + sql);
    }
}

// прокси-класс добавляющий логирование
class LoggingProxy implements Database {
    private Database realDatabase;

    public LoggingProxy(Database realDatabase) {
        this.realDatabase = realDatabase;
    }

    @Override
    public void connect() {
        log("Попытка подключения к базе данных");
        realDatabase.connect();
        log("Подключение успешно установлено");
    }

    @Override
    public void query(String sql) {
        log("Попытка выполнить запрос: " + sql);
        long start = System.currentTimeMillis();

        realDatabase.query(sql);

        long duration = System.currentTimeMillis() - start;
        log("Запрос выполнен за " + duration + " мс");
    }

    private void log(String message) {
        System.out.println(new Date() + ": " + message);
    }
}

// использование
public class LoginProxy {
    public static void main(String[] args) {
        Database realDb = new RealDatabase();
        Database proxyDb = new LoggingProxy(realDb);

        proxyDb.connect();
        proxyDb.query("SELECT * FROM users");
        proxyDb.query("UPDATE accounts SET balance = balance + 100");
    }
}