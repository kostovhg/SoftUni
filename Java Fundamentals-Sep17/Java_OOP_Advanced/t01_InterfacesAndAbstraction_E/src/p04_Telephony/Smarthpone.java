package p04_Telephony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Smarthpone implements Callable, Browseble {

    private List<String> numbersToCall;
    private List<String> sitesToVisit;

    Smarthpone() {
        this.numbersToCall = new ArrayList<>();
        this.sitesToVisit = new ArrayList<>();
    }

    void addNumberToCall(String... numbers){
        this.numbersToCall.addAll(Arrays.asList(numbers));

    }

    void addSiteToVisit(String... sites){
        this.sitesToVisit.addAll(Arrays.asList(sites));
    }

    @Override
    public void browse() {
        for (String site : this.sitesToVisit) {
            if(site.matches("[^0-9]*")){
                System.out.println(String.format("Browsing: %s!", site));
            } else {
                System.out.println("Invalid URL!");
            }
        }
    }

    @Override
    public void call() {
        for (String number : this.numbersToCall) {
            if(number.matches("[0-9]+")){
                System.out.println(String.format("Calling... %s", number));
            } else {
                System.out.println("Invalid number!");
            }
        }
    }
}
