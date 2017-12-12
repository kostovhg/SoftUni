package lab.p05_security_system;

public class KeyCardCheck extends SecurityCheck implements KeyCardUI {

    private SecurityUI securityUI;

    public KeyCardCheck(SecurityUI securityUI) {
        this.securityUI = securityUI;
    }

    @Override
    public boolean validateUser() {
        String code = securityUI.requestKeyCard();
        if (isValid(code)) {
            return true;
        }

        return false;
    }

    private boolean isValid(String code) {
        return true;
    }

    @Override
    public String requestKeyCard() {
        return "slide your key card";
    }
}
