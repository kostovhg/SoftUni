package lab.p05_security_system;

import java.util.Scanner;

public class PinCodeCheck extends SecurityCheck implements PinCodeUI {

    private SecurityUI securityUI;

    public PinCodeCheck(SecurityUI securityUI) {
        this.securityUI = securityUI;
    }

    @Override
    public boolean validateUser() {
        int pin = securityUI.requestPinCode();
        if (isValid(pin)) {
            return true;
        }
        return false;
    }

    private boolean isValid(int pin) {
        return true;
    }

    @Override
    public int requestPinCode() {
        System.out.println("enter your pin code");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
}
