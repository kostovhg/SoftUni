package e_WorkForce.models;

import e_WorkForce.contracts.Employee;
import e_WorkForce.contracts.Observable;

/**
 * Class Job
 */
public class Job implements Observable {

    private String name;
    private int hoursOfWorkRequired;
    private Employee employee;
    /**
     * Observer object will watch for events and when they trigger
     * its going to add concrete object of current type Job to its collection
     */
    private Observer observer;

    public Job(String name, int hoursOfWorkRequired, Employee employee, Observer observer) {
        this.name = name;
        this.hoursOfWorkRequired = hoursOfWorkRequired;
        this.employee = employee;
        this.observer = observer;
    }

    /**
     * Update the remaining necessary hours
     * This method is overridden from {@link Observable} interface
     * its change the state of current object. Here is event checking procedure happens.
     * That's why for every observable object this method is mandatory.
     */
    @Override
    public void updateState() {
        this.hoursOfWorkRequired -= employee.getWorkingHoursPerWeek();

        /*
          This is the event!!
          If the necessary hours are zero or less, the event is triggered
         */
        if(this.hoursOfWorkRequired <= 0) {
            /*
              When the event is triggered, the current observer should be notified,
              with passing to notify method a String message, that he will proceed.
             */
            this.observer.notifyObserver(String.format("Job %s done!", this.name));
            /*
              Also current observer should add concrete object of current class
              to its collection of object with triggered events.
             */
            this.observer.addJobToBeRemoved(this);
        }
    }

    /**
     * Notify observer for current status
     */
    public void status() {
        this.observer.notifyObserver(String.format("Job: %s Hours Remaining: %d", this.name, this. hoursOfWorkRequired));
    }
}
