package lab.p02_services;

import java.util.*;

public class OnlineStoreOrder {

    private Set<NotificationService> notificationServicesList;

    public OnlineStoreOrder(){
        this.notificationServicesList = new LinkedHashSet<>();
    }

    public OnlineStoreOrder(Set<NotificationService> services) {
        this.notificationServicesList = new LinkedHashSet<>(services);
    }

    public void addNotificationServices(NotificationService service) {
        this.notificationServicesList.add(service);
    }

    public void process() {
        for (NotificationService service : this.notificationServicesList) {
            if (service.isActive()) {
                service.sendNotification();
            }
        }
    }
}
