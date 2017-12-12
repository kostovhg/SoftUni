package lab.p02_services;

import lab.p02_services.notificationServices.EmailNotificationService;
import lab.p02_services.notificationServices.SmsNotificationService;

public class Main {
    public static void main(String[] args) {

        NotificationService emailService = new EmailNotificationService(true);
        NotificationService smsService = new SmsNotificationService();

        OnlineStoreOrder storeOrder = new OnlineStoreOrder();
        storeOrder.addNotificationServices(emailService);
        storeOrder.addNotificationServices(smsService);
        storeOrder.addNotificationServices(emailService);
        storeOrder.process();
    }
}
