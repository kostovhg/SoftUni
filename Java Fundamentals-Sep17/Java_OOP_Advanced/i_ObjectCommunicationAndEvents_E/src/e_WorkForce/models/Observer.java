package e_WorkForce.models;

import e_WorkForce.contracts.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Observer will watch for event (Job necessary hours <= 0)
 * Each observed object (Job) will contain a observer;
 */
public class Observer {

    /**
     * List of jobs that going to be collected when their event triggers
     */
    private List<Observable> jobsToRemove;

    public Observer(){
        this.jobsToRemove = new ArrayList<>();
    }

    /**
     * Method that will be called on event triggering
     * @param message - String object to be printed on the console
     */
    public void notifyObserver(String message) {
        System.out.println(message);
    }

    /**
     * Method for adding jobs with triggered events
     * @param job - Job to be added to collection with triggered event
     */
    public void addJobToBeRemoved(Observable job) {
        this.jobsToRemove.add(job);
    }

    /**
     * @return a list of jobs with triggered event
     * that the {@link JobList#update()}
     * will accept as parameter (and delete them)
     */
    public List<Observable> getJobsToRemove() {
        List<Observable> jobsToReturn = new ArrayList<>(this.jobsToRemove);
        this.jobsToRemove.clear();

        return jobsToReturn;
    }
}
