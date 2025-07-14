class Computer {
    // обязательные параметры
    private final String HDD;
    private final String RAM;

    // необязательные параметры
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    private Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // геттеры
    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    // вложенный класс Builder
    public static class ComputerBuilder {
        // Обязательные параметры
        private final String HDD;
        private final String RAM;

        // необязательные параметры (инициализированы значениями по умолчанию)
        private boolean isGraphicsCardEnabled = false;
        private boolean isBluetoothEnabled = false;

        public ComputerBuilder(String HDD, String RAM) {
            this.HDD = HDD;
            this.RAM = RAM;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// использование
public class PatternBuilder {
    public static void main(String[] args) {
        Computer computer = new Computer.ComputerBuilder("500GB", "16GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(false)
                .build();

        System.out.println("HDD: " + computer.getHDD());
        System.out.println("RAM: " + computer.getRAM());
        System.out.println("Bluetooth: " + computer.isBluetoothEnabled());
        System.out.println("Graphics Card: " + computer.isGraphicsCardEnabled());
    }
}