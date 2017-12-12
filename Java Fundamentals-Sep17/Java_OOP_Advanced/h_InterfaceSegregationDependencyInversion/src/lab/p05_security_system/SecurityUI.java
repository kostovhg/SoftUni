package lab.p05_security_system;

public interface SecurityUI extends PinCodeUI, KeyCardUI{

    String requestKeyCard();

    int requestPinCode();
}
