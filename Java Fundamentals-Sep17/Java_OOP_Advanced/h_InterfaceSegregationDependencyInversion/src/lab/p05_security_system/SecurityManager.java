package lab.p05_security_system;

import java.io.IOException;
import java.util.Scanner;

public class SecurityManager {

    private KeyCardUI keyCardCheck;
    private PinCodeUI pinCodeCheck;

    public SecurityManager(KeyCardUI keyCardCheck, PinCodeUI pinCodeCheck) {
        this.keyCardCheck = keyCardCheck;
        this.pinCodeCheck = pinCodeCheck;
    }

    public void check() {
        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                System.out.println(keyCardCheck.requestKeyCard());
                break;
            case 2:
                System.out.println(pinCodeCheck.requestPinCode());
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        ScannerUI scannerUI = new ScannerUI();
        KeyCardUI keyCardCheck = new KeyCardCheck(scannerUI);
        PinCodeUI pinCodeCheck = new PinCodeCheck(scannerUI);
        SecurityManager manager = new SecurityManager(keyCardCheck, pinCodeCheck);
        manager.check();
    }
}
