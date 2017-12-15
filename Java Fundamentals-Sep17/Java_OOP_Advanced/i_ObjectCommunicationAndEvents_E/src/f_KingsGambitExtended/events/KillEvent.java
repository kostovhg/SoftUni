package f_KingsGambitExtended.events;

import f_KingsGambitExtended.contracts.Observer;
import f_KingsGambitExtended.contracts.Subject;

public class KillEvent {

    public void executeEvent(Observer observer, Subject subject) {
        observer.takeHit();

        if(observer.getHealth() <= 0) {
            subject.removeObserver(observer);
        }
    }
}
