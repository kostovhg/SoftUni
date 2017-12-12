package lab.p02_services.notificationServices;

import lab.p02_services.NotificationService;

public class SmsNotificationService implements NotificationService {

    @Override
    public void sendNotification() {
        System.out.println("Sms send...");
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
