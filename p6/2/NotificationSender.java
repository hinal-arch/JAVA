interface Notifier {
    void send(String message);
}

interface Urgent {
}

class UrgentNotifier implements Notifier, Urgent {
    private final Notifier notifier;

    UrgentNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}

public class NotificationSender {
    public static void main(String[] args) {

        Notifier email = message -> 
            System.out.println("Email: " + message);

        Notifier sms = message -> 
            System.out.println("SMS: " + message);

        Notifier urgentEmail = new UrgentNotifier(email);
        Notifier urgentSMS = new UrgentNotifier(sms);

        Notifier[] senders = {
            email,
            sms,
            urgentEmail,
            urgentSMS
        };

        String message = "Exam tomorrow";

        for (Notifier sender : senders) {
            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}