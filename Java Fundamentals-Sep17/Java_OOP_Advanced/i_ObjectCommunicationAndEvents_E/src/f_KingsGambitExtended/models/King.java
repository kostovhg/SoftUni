package f_KingsGambitExtended.models;

import f_KingsGambitExtended.contracts.Attackable;
import f_KingsGambitExtended.contracts.KillableUnits;
import f_KingsGambitExtended.contracts.Observer;
import f_KingsGambitExtended.contracts.Subject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class King extends Unit implements Subject {

    /**
     * a list with observers
     */
    private List<Observer> observers;

    public King(String name) {
        super(name);
        this.observers = new ArrayList<>();
    }

    /**
     * @param observer implementing interface Observer to be added
     */
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * @param observer to be removed
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * send notification to all observers
     */
    @Override
    public void notifyObservers() {
        System.out.println(String.format("King %s is under attack!", super.getName()));

        for (Observer observer : observers) {
            observer.notifyCurrent();
        }
    }
}
