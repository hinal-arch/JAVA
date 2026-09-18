interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    @Override
    public void on() {
        System.out.println("Fan ON");
    }

    @Override
    public void off() {
        System.out.println("Fan OFF");
    }
}

class Light implements Switchable {
    @Override
    public void on() {
        System.out.println("Light ON");
    }

    @Override
    public void off() {
        System.out.println("Light OFF");
    }
}

interface SwitchRule {
    boolean canSwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        for (Switchable device : devices) {
            device.toggle();
            device.off();
        }

        SwitchRule rule1 = (Switchable device, int hour) -> hour >= 6 && hour <= 22;

        SwitchRule rule2 = (device, hour) -> hour >= 8 && hour <= 20;

        int hour = 10;

        for (Switchable device : devices) {
            System.out.println(rule1.canSwitchOn(device, hour));
            System.out.println(rule2.canSwitchOn(device, hour));
        }
    }
}