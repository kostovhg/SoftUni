package c_LastDigitName;

public class Number{
    private final String[] DIGITS_NAMES = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
    };
    private int num;

    public Number(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLastDigitName(){
        return DIGITS_NAMES[this.num % 10];
    }
}
