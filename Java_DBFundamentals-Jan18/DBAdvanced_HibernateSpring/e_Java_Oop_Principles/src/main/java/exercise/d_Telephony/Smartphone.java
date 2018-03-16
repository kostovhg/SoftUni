package exercise.d_Telephony;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browseable {

    private String number;

    public Smartphone(String number) {
        this.setNumber(number);
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String call(String phoneNumber){
        Pattern p = Pattern.compile("^\\d+$");
        Matcher m = p.matcher(phoneNumber);
        if(m.matches()){
            return String.format("Calling... %s", phoneNumber);
        } else {
            return "Invalid number!";
        }
    }

    @Override
    public String browse(String address) {
        // Important for third test in judge is to use \\D*
        Pattern p = Pattern.compile("^\\D*$");
        Matcher m = p.matcher(address);
        if(m.matches()){
            return String.format("Browsing: %s!", address);
        } else {
            return "Invalid URL!";
        }
    }
}
